package de.dhbw.securepp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.zip.Adler32;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * (De-)Encrypts a file.
 * 
 * THIS APP DELIBERATELY CONTAINS A VERY SIGNIFICANT SECURITY ISSUE WHICH
 * RENDERS THE ENCRYPTION TOTALLY USELESS.
 * 
 * This app is only to be used for teaching purposes (e.g. Reverse Engineering
 * of Java)!
 */
public class Main {

	public static final int KEY_SIZE = 256;

	private static void showHelp(Options options) {
		HelpFormatter helper = new HelpFormatter();
		helper.printHelp("Usage:", options);
	}

	public static byte[] deriveKey(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory kf = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA512");
		KeySpec specs = new PBEKeySpec(password.toCharArray(), salt,
				1024 * 1024, KEY_SIZE);
		return kf.generateSecret(specs).getEncoded();
	}

	public static long getChecksum(byte[] data) {
		var adler32 = new Adler32();
		adler32.update(data);
		var checksum = adler32.getValue();
		return checksum;
	}

	public static String byteArrayToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (byte b : bytes) {
			sb.append(String.format("0x%02X ", b));
		}
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {

		Options options = new Options();
		options.addOption(Option.builder("d").longOpt("decrypt")
				.desc("Performs decryption instead of encryption.").build());

		var pOption = Option.builder("p").longOpt("password")
				.desc("The password.").hasArg().type(String.class).required()
				.build();
		options.addOption(pOption);

		var inOption = Option.builder("in").desc("The file to process.")
				.hasArg().type(File.class).required().build();
		options.addOption(inOption);

		var outOption = Option.builder("out").desc("The output.").hasArg()
				.type(File.class).required().build();
		options.addOption(outOption);

		options.addOption(
				Option.builder("h").longOpt("help").desc("Get Help.").build());

		CommandLine cmd = null;
		try {
			final var parser = new DefaultParser();
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			showHelp(options);
			System.exit(1);
		}

		if (cmd.hasOption("h")) {
			showHelp(options);
		}

		try {
			var fin = (File) cmd.getParsedOptionValue(inOption);
			if (!fin.exists() || !fin.canRead()) {
				System.err.println("File cannot be read: " + fin);
				System.exit(1);
			}
			var fout = (File) cmd.getParsedOptionValue(outOption);

			if (cmd.hasOption("d")) {

				try (var in = new FileInputStream(fin);
						var out = new FileOutputStream(fout)) {

					// 1. read salt
					final var salt = new byte[KEY_SIZE / 8];
					var readBytes = in.read(salt);
					if (readBytes != salt.length) {
						// there are better methods, but this one is at least
						// reporting problems...
						throw new IOException("failed reading salt");
					}
					final var kek = new SecretKeySpec(
							deriveKey(cmd.getOptionValue(pOption), salt),
							"AES");
					System.out.println("KEK:               "
							+ byteArrayToString(kek.getEncoded()));

					// 2. read encrypted checksum of dek
					Cipher kc = Cipher.getInstance("AES/ECB/NoPadding");
					kc.init(Cipher.DECRYPT_MODE, kek);

					final var encrytpedPaddedChecksum = new byte[16];
					readBytes = in.read(encrytpedPaddedChecksum);
					if (readBytes != encrytpedPaddedChecksum.length) {
						// there are better methods, but this one is at least
						// reporting problems...
						throw new IOException("failed reading padded checksum");
					}
					final var expectedChecksum = kc
							.update(encrytpedPaddedChecksum);
//					System.out.println("Expected Checksum: "
//							+ byteArrayToString(expectedChecksum));

					// 3. read encrypted and encoded dek
					var encodedAndEncryptedDEK = new byte[44];
					readBytes = in.read(encodedAndEncryptedDEK);
					if (readBytes != 44) {
						throw new IOException("failed reading encoded dek");
					}
//					System.out.println("ENCODED DEK:       "
//							+ byteArrayToString(encodedAndEncryptedDEK));
					var rawDEK = kc.doFinal(
							Base64.getDecoder().decode(encodedAndEncryptedDEK));
					if (!Arrays.equals(
							BigInteger.valueOf(getChecksum(rawDEK))
									.toByteArray(),
							0, 4, expectedChecksum, 0, 4)) {
						throw new SecurityException("invalid password");
					}
//					System.out.println(
//							"DEK:               " + byteArrayToString(rawDEK));

					// 4. read encrypted data (and decrypt it)
					Cipher dc = Cipher.getInstance("AES/CTR/NoPadding");
					var dek = new SecretKeySpec(rawDEK, "AES");
					var rawIV = new byte[128/8];
					readBytes = in.read(rawIV);
					if (readBytes != 128/8) {
						throw new IOException("failed reading iv");
					}
					var iv = new IvParameterSpec(rawIV);
//					System.out.println("NONCE:             "
//							+ byteArrayToString(iv.getIV()));
					dc.init(Cipher.DECRYPT_MODE, dek, iv);
					out.write(dc.doFinal(in.readAllBytes()));
				}

			} else {
				/* A security issue:
				var seed = 0xc16881a958369d7cl; // a security issue... (=> the
												// salt will always be the same;
												// this opens up the possibility
												// of a rainbow attack)
				Random r = new Random(seed);
				*/
				Random r = new Random(); // less of a security issue
				var salt = new byte[KEY_SIZE / 8];
				r.nextBytes(salt);
				final var kek = new SecretKeySpec(
						deriveKey(cmd.getOptionValue(pOption), salt), "AES");
				System.out.println("KEK:               "
						+ byteArrayToString(kek.getEncoded()));

				/*	Though this program has many problems, the following 
				 	 would make it somehow secure...
				   
					KeyGenerator kg = KeyGenerator.getInstance("AES");
					kg.init(KEY_SIZE);
					final var dek = kg.generateKey();
				*/
				// The following makes the program absolutely rubbish...
				final var initialDEK = new byte[KEY_SIZE/8];
				new Random(0xc16881a958369d7cl).nextBytes(initialDEK);
				final var dek = new SecretKeySpec(initialDEK,	"AES");
				
				final var rawDEK = dek.getEncoded();
//				System.out.println(
//						"DEK:               " + byteArrayToString(rawDEK));

				try (var in = new FileInputStream(fin);
						var out = new FileOutputStream(fout)) {

					Cipher kc = Cipher.getInstance("AES/ECB/NoPadding");
					kc.init(Cipher.ENCRYPT_MODE, kek);

					// 1. write salt
					out.write(salt);

					// 2. write encrypted checksum of DEK
					var checksum = getChecksum(rawDEK);
					var paddedChecksum = Arrays.copyOf(
							BigInteger.valueOf(checksum).toByteArray(), 16);
					out.write(kc.update(paddedChecksum));
//					System.out.println("Checksum:          " + checksum);
//					System.out.println("Padded Checksum:   "
//							+ byteArrayToString(paddedChecksum));

					// 3. write encrypted and encoded dek
					// to add some confusion we encode the dek using BASE64
					final var encodedDEK = Base64.getEncoder()
							.encode(kc.doFinal(rawDEK));
					out.write(encodedDEK);
//					System.out.println("ENCODED DEK:       "
//							+ byteArrayToString(encodedDEK));

					// 5. write nonce/IV
					var iv = new IvParameterSpec(ByteBuffer.allocate(128 / 8)
							.putLong(System.currentTimeMillis()).array());
//					System.out.println("NONCE:             "
//							+ byteArrayToString(iv.getIV()));
					out.write(iv.getIV());
					// 4. write encrypted data
					Cipher dc = Cipher.getInstance("AES/CTR/NoPadding");
					dc.init(Cipher.ENCRYPT_MODE, dek, iv);
					out.write(dc.doFinal(in.readAllBytes()));
				}
			}
		} catch (Exception e) {
			// We are lost!
			System.err.println("Encryption failed: " + e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

}

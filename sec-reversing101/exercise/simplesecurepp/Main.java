package de.dhbw.simplesecurepp;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.Adler32;

/**
 * THIS APP SERVES TEACHING PURPOSES ONLY (IN PARTICULAR REVERSE ENGINEERING). 
 * 
 * IT DELIBERATELY HAS SIGNIFICANT SECURITY ISSUES!
 */
public class Main {

	private final static byte[] CC = "simplesecurepp"
			.getBytes(Charset.forName("ascii"));

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println(
					"SimpleSecure++ - simple data encryption and decryption.");
			System.err.println(
					"En/decrypts the data read from stdin and writes the result to stdout.");
			System.err.println(" 1 - the password ");
			System.err.println(" 2 - mode: encrypt or decrypt");
			System.err.println("Example usage:");
			System.err.println(
					"	java de.dhbw.simplesecurepp.Main \"Password\" encrypt");
			System.err.println("DISCLAIMER:");
			System.err.println(
					"	SimpleSecure++ IS FOR TEACHING PURPOSES ONLY. IT HAS SIGNIFICANT SECURITY ISSUES!");
			System.exit(1);
		}
		var adler32 = new Adler32(); // as the name suggests= Adler32 only uses 32 bits...
		adler32.update(args[0].getBytes(Charset.forName("utf8")));
		final var secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(BigInteger.valueOf(adler32.getValue()).toByteArray());

		switch (args[1]) {
			case "encrypt":
				for (int i = 0; i < CC.length; i++) {
					CC[i] ^= secureRandom.nextInt(); // this is ugly like hell...
				}
				System.out.write(CC);
				break;
			case "decrypt":
				var header = System.in.readNBytes(CC.length);
				for (int i = 0; i < CC.length; i++) {
					header[i] ^= secureRandom.nextInt();
				}
				if (!Arrays.equals(CC, header)) {
					System.err.println("invalid password");
					System.exit(1);
				}
				break;
			default:
				System.err.println("unknown modus: " + args[1]);
				System.exit(1);
		}

		final var buffer = new byte[1024];
		var bytesRead = 0;
		while ((bytesRead = System.in.read(buffer)) != -1) {
			var randomBytes = new byte[bytesRead];
			secureRandom.nextBytes(randomBytes);
			for (int i = 0; i < bytesRead; i++) {
				buffer[i] ^= randomBytes[i];
			}
			System.out.write(buffer, 0, bytesRead);
		}
	}
}

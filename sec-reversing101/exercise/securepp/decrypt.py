#!/usr/local/bin/python3

# Security issue that we exploit: the DEK is independent from the password.
# We just need the nonce from the file and the constant "DEK" to decrypt the
# file.

# Format:
# [32] Salt (for KEK)
# [44] Encrypted and Encoded DEK
# [16] Checksum
# [16] Nonce for CTR
# [...] Encrypted Data

from Crypto.Cipher import AES
from Crypto.Util import Counter
from binascii import hexlify

dek = bytes([ 0x43, 0xE7, 0x14, 0x67, 0xF9, 0x86, 0xDE, 0xEA, 0xAA, 0x4E, 0x5F, 0x88, 0xDE, 0x89, 0x15, 0xD7, 0x91, 0x00, 0x3D, 0x32, 0x0A, 0xE1, 0x2D, 0x19, 0x25, 0x20, 0x5B, 0x92, 0xA9, 0xB1, 0x84, 0xED ])

with open("demo/Poem.enc","rb") as f:
    f.seek(0x5c)
    nonce = f.read(16)[:8]
    encryptedData = f.read()
    aes = AES.new(dek,AES.MODE_CTR,nonce=nonce)
    print(aes.decrypt(encryptedData))

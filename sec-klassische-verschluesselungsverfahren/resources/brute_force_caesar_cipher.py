#!/usr/bin/python3

"""
Generates all possible decryptions of some ciphertext encrypted 
using a Caesar cipher and an unknown k.
"""

from functools import partial

C = "PHHW PH DIWHU WKH WRJD SDUWB"


def decrypt(k : int, c: str) -> str:
    if c == " ":
        return c
    else:
        # ord(A) =  has the ASCII/UTF-8 value 65
        p = (((ord(c) - 65) - k) % 26) + 65
        return chr(p)


for k in range(1, 26):
    d = partial(decrypt, k)
    p = str(k) + " " + "".join(list(map(d, C)))
    print(p)

#!/usr/bin/python3
from functools import partial

p = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"

def encrypt(k,p : int) -> str:
    if (p == " "):
        return p
    else:
        # A has the value 65 
        c = (((ord(p)-65) + k) % 26) + 65
        return chr(c)

e = partial(encrypt,3)
C = "".join(list(map(e,p)))
print(C)

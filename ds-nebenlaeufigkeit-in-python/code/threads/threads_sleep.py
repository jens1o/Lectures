#!/usr/bin/env python3
import time
from threading \
    import Thread, current_thread

"""
This demonstrates how to create a new (OS) threads in Python. 

Recall that (at least in CPython <= 3.12) thread are not executed in parallel.
"""

def sleep():
    time.sleep(10) # puts the current thread to sleep and allows another thread to continue processing

if __name__ == '__main__':
    t1 = Thread(target=sleep)
    t2 = Thread(target=sleep)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print("Done.")

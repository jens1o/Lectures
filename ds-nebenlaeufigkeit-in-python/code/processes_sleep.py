#!/usr/bin/env python3
import time
from multiprocessing import Process, current_process

"""
This demonstrates how to create a new process in Python. In this case real OS processes are created.
"""

def sleep():
    time.sleep(10)

print(current_process().name)

if __name__ == '__main__':
    p1 = Process(target=sleep)
    p2 = Process(target=sleep)
    p1.start()
    p2.start()
    p1.join()
    p2.join()

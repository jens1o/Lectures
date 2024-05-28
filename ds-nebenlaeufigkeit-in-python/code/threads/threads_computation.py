#!/usr/bin/env python3
import time
from threading \
    import Thread, current_thread, Lock

"""
This demonstrates how to create a new (OS) threads in Python. 

Recall that (at least in CPython <= 3.12) thread are not executed in parallel.
"""

printMutex = Lock()

def ts_print(msg):
    with printMutex:
        print(msg)

def computation():
    ts_print(current_thread().name)
    j = 1
    for i in range(100*1000*1000):
        j += (i/j)
    ts_print("Done:"+str(j))

if __name__ == '__main__':
    t1 = Thread(target=computation)
    t2 = Thread(target=computation)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
    print("Done.")

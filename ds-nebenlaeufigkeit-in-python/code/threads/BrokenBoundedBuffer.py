#!/usr/bin/env python3

"""
THIS CODES DEMONSTRATES A BROKEN BOUNDED BUFFER IMPLEMENTATION.

USING THIS BOUNDED BUFFER IN A MULTI-THREADED ENVIRONMENT WILL LEAD TO DEADLOCKS.
"""

from threading import Thread, Lock, Condition

printMutex = Lock()

def ts_print(msg):
    with printMutex:
        print(msg)

class BoundedBuffer:

    def __init__(self, capacity):
        self.capacity = capacity
        self.buffer = []
        self.lock = Lock()
        self.not_used = Condition(self.lock)

    def put(self, item):
        with self.not_used:
            while len(self.buffer) == self.capacity:
                self.not_used.wait()
            self.buffer.append(item)
            self.not_used.notify(1) # THIS IS NOT CORRECT!

    def get(self):
        with self.not_used:
            while len(self.buffer) == 0:
                self.not_used.wait()
            item = self.buffer.pop(0)
            self.not_used.notify(1) # THIS IS NOT CORRECT!
            return item


if __name__ == '__main__':
    buffer = BoundedBuffer(1)

    def producer():
        for i in range(1000):
            buffer.put(i)
            ts_print(f"Produced {i}")

    def consumer():
        for i in range(1000):
            ts_print(f"Consumed {buffer.get()}")

    """
    Even the following example sometimes leads to a deadlock.   
    p1 = Thread(target=lambda: buffer.put(1))
    p2 = Thread(target=lambda: buffer.put(2))
    c1 = Thread(target=lambda: buffer.get())
    c2 = Thread(target=lambda: buffer.get())
    """

    p1 = Thread(target=producer)
    p2 = Thread(target=producer)
    c1 = Thread(target=consumer)
    c2 = Thread(target=consumer)
    c1.start() ; c2.start() ; p1.start() ; p2.start()
    c1.join()
    c2.join()
    p1.join()
    p2.join()
    print("Done.")
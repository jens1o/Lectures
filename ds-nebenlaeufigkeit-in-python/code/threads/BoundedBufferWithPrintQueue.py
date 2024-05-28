#!/usr/bin/env python3

import threading
from  queue import Queue

print_queue = Queue()

def ts_print(msg): 
    print_queue.put(msg)

def print_handler():
    while True:
        msg = print_queue.get()
        print(msg) # there will ever be only one thread
        print_queue.task_done()


class BoundedBuffer:

    def __init__(self, capacity):
        self.capacity = capacity
        self.buffer = []
        self.lock = threading.Lock()
        self.not_empty = threading.Condition(self.lock)
        self.not_full = threading.Condition(self.lock)

    def put(self, item):
        with self.not_full:
            while len(self.buffer) == self.capacity:
                self.not_full.wait()
            self.buffer.append(item)
            self.not_empty.notify()

    def get(self):
        with self.not_empty:
            while len(self.buffer) == 0:
                self.not_empty.wait()
            item = self.buffer.pop(0)
            self.not_full.notify()
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

    threading.Thread(target=print_handler,daemon=True).\
        start()

    p1 = threading.Thread(target=producer)
    p2 = threading.Thread(target=producer)
    c1 = threading.Thread(target=consumer)
    c2 = threading.Thread(target=consumer)
    c1.start() ; c2.start() ; p1.start() ; p2.start()
    c1.join()
    c2.join()
    p1.join()
    p2.join()
    ts_print("Done.")
    print_queue.join()
    print("Done Done...")
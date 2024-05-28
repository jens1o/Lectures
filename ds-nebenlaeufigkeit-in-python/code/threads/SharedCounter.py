#!/usr/bin/env python3

from threading import Thread,Lock

printMutex = Lock()

def ts_print(msg):
    with printMutex:
        print(msg)

class SharedCounter:

    def __init__(self):
        self._value = 0
        self.lock = Lock()

    def increment(self):
        self.lock.acquire()
        try:
            self._value += 1
        finally:
            self.lock.release()

    def decrement(self):
        with self.lock:
            self._value -= 1

    def value(self):
        return self._value
    
if __name__ == '__main__':
    counter = SharedCounter()
    Thread(target=counter.increment).start()
    ts_print(counter.value())
    Thread(target=counter.decrement).start()
    ts_print(counter.value())
    Thread(target=counter.increment).start()
    ts_print(counter.value())
    Thread(target=counter.decrement).start()
    ts_print(counter.value())

    

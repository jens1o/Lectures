#!/usr/bin/env python3

from threading import Thread,Lock

class SharedCoordinate:

    def __init__(self, x = 0, y = 0):
        self.x = x
        self.y = y
        self.lock = Lock()

    def update(self, x, y):
        self.lock.acquire()
        try:
            self.x = x
            self.y = y
        finally:
            self.lock.release()

    def value(self):
        with self.lock:
            return (self.x, self.y)
    

if __name__ == '__main__':
    coord = SharedCoordinate(100,200)
    print(coord.value())    

    

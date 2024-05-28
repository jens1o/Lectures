#!/usr/bin/env python3

"""
Implementierung eines Zwischenspeichers mit einer frei wählbaren Verzögerung.

Aufgabe A:
- verwenden Sie explizit ``Thread``s (d.h. ``Thread``-Objekte oder einen ``ThreadPoolExsecutor``).
- Achten Sie darauf, dass die submit Methode nicht blockieren darf.

 
Aufgabe C:
- Unter welchen Bedingungen ist welche Implementierung besser geeignet?

"""

from threading import Thread, Lock
from queue import Queue
import time

print_lock = Lock()

def ts_print(*args, **kwargs):
    with print_lock:
        print(*args, **kwargs)

class DelayedBuffer:  # NOT Thread Safe

    def __init__(self):
        self.fn_queue = Queue()

    def submit(self, delay, fn, *args, **kwargs):

        def delayed_fn():
            try:
                (fn, args, kwargs) = self.fn_queue.get()
                time.sleep(delay)
                fn(*args, **kwargs)
            except Exception as e:
                ts_print(f"Error in {fn}: {e}")
            finally:
                self.fn_queue.task_done()
            
        self.fn_queue.put((fn, args, kwargs))
        Thread(target=delayed_fn).start()

    def join(self):
        self.fn_queue.join()


if __name__ == "__main__":
    buffer = DelayedBuffer()
    buffer.submit(10 / 1000, ts_print, "Hello ", **{"end": ""})
    buffer.submit(100 / 1000, ts_print, "World!")
    buffer.submit(50 / 1000, ts_print, "of the ", **{"end": ""})
    buffer.submit(20 / 1000, ts_print, "from ", **{"end": ""})
    buffer.submit(30 / 1000, ts_print, "the other side ", **{"end": ""})
    buffer.join()
    print("Done.")

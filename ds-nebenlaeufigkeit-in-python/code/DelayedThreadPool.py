#!/usr/bin/env python3

"""
Implementierung eines Zwischenspeichers mit einer frei wählbaren Verzögerung.

Aufgabe A:
- verwenden Sie explizit ``Thread``s (d.h. ``Thread``-Objekte oder einen ``ThreadPoolExsecutor``).
- Achten Sie darauf, dass die submit Methode nicht blockieren darf.

 
Aufgabe C:
- Unter welchen Bedingungen ist welche Implementierung besser geeignet?

"""

from threading import Lock
import time
import concurrent.futures

print_lock = Lock()

def ts_print(*args, **kwargs):
    with print_lock:
        print(*args, **kwargs)

class DelayedBuffer:  # NOT Thread Safe

    def __init__(self):
        self.thread_pool = concurrent.futures.ThreadPoolExecutor()

    def submit(self, delay, fn, *args, **kwargs):

        def delayed_fn(fn, *args, **kwargs):
            time.sleep(delay)
            try:
                fn(*args, **kwargs)
            except Exception as e:
                ts_print(f"Error in {fn}: {e}")
            
        self.thread_pool.submit(delayed_fn, fn, *args, **kwargs)

    def join(self):
        self.thread_pool.shutdown(wait=True)


if __name__ == "__main__":
    buffer = DelayedBuffer()
    buffer.submit(10 / 1000, ts_print, "Hello ", **{"end": ""})
    buffer.submit(100 / 1000, ts_print, "World!")
    buffer.submit(50 / 1000, ts_print, "of the ", **{"end": ""})
    buffer.submit(20 / 1000, ts_print, "from ", **{"end": ""})
    buffer.submit(30 / 1000, ts_print, "the other side ", **{"end": ""})
    buffer.join()
    print("Done.")

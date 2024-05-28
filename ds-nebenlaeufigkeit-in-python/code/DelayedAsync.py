#!/usr/bin/env python3

"""
Implementierung eines Zwischenspeichers mit einer frei wählbaren Verzögerung.

Aufgabe A:
- verwenden Sie explizit ``Thread``s (d.h. ``Thread``-Objekte oder einen ``ThreadPoolExsecutor``).
- Achten Sie darauf, dass die submit Methode nicht blockieren darf.

 
Aufgabe C:
- Unter welchen Bedingungen ist welche Implementierung besser geeignet?

"""

from asyncio import Queue
import asyncio


def ts_print(*args, **kwargs):
    print(
        *args, **kwargs
    )  # no lock needed, because we are using coroutines and not threads


class DelayedBuffer:

    def __init__(self):
        self.fn_queue = Queue()

    def submit(self, delay, fn, *args, **kwargs):

        async def delayed_fn():
            try:
                (fn, args, kwargs) = await self.fn_queue.get()
                await asyncio.sleep(delay)
                fn(*args, **kwargs)
            except Exception as e:
                ts_print(f"Error in {fn}: {e}")
            finally:
                self.fn_queue.task_done()

        self.fn_queue.put_nowait((fn, args, kwargs))
        asyncio.create_task(delayed_fn())

    async def join(self):
        await self.fn_queue.join()


async def main():
    buffer = DelayedBuffer()
    buffer.submit(100 / 1000, ts_print, "Hello ", **{"end": "", "flush": True})
    buffer.submit(1000 / 1000, ts_print, "World!")
    buffer.submit(500 / 1000, ts_print, "of the ", **{"end": "", "flush": True})
    buffer.submit(200 / 1000, ts_print, "from ", **{"end": "", "flush": True})
    buffer.submit(300 / 1000, ts_print, "the other side ", **{"end": "", "flush": True})
    await buffer.join()
    print("Done.")


if __name__ == "__main__":
    asyncio.run(main())

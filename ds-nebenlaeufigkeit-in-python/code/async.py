#!/usr/bin/env python3
import time
import asyncio

"""
This demonstrates the usage of coroutines in Python. All coroutines are executed 
in the same thread. The event loop is responsible for scheduling the coroutines.

Coroutines require the usage of respective libraries. In this case, the asyncio 
library is used.
"""

async def busy_sleep(id):
    print(f"Task {id} started") 
    await asyncio.sleep(10)
    print(f"Task {id} completed") 


async def main():
    t1 = asyncio.create_task(busy_sleep(1))
    t2 = asyncio.create_task(busy_sleep(2))

    print("Both initialized.")
    await t1
    await t2
    print("Done.")


if __name__ == '__main__':
    asyncio.run(main())
#!/usr/bin/env python3

from random import randint
from multiprocessing import current_process, Process, JoinableQueue as MPQueue
from threading import Thread
from queue import Queue as TQueue
import time


def print_queue_handler(print_queue):
    while True:
        msg = print_queue.get()
        print(msg)
        print_queue.task_done()


def read_from_ip_queue(ip_queue, print_queue):
    while True:
        msg = ip_queue.get()
        print_queue.put(msg)
        ip_queue.task_done()


def f(c_to_p_ip_queue):
    time.sleep(randint(1, 3)) # just some fuzzing
    c_to_p_ip_queue.put("I'm alive: " + current_process().name)
    time.sleep(randint(1, 3)) # just some fuzzing
    c_to_p_ip_queue.put("Hell World from " + current_process().name)


if __name__ == "__main__":
    print_queue = TQueue()
    c_to_p_ip_queue = MPQueue()
    p1 = Process(target=f, args=(c_to_p_ip_queue,))
    p1.start()
    p2 = Process(target=f, args=(c_to_p_ip_queue,))
    p2.start()
    Thread(
        target=read_from_ip_queue,
        args=(
            c_to_p_ip_queue,
            print_queue,
        ),
        daemon=True,
    ).start()
    Thread(target=print_queue_handler, args=(print_queue,), daemon=True).start()
    c_to_p_ip_queue.join()
    print_queue.join()
    p2.join()
    p1.join()

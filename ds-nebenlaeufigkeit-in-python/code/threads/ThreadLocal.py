#!/usr/bin/env python3

import time
import threading

stop = False # shared global variable

local_data = threading.local()

def f(v):
    setattr(local_data, "value", 0)
    while(not stop):
        print(local_data.value)
        local_data.value += v
        time.sleep(1)

t1 = threading.Thread(target=f, args=(1,))
t2 = threading.Thread(target=f, args=(-1,))
t1.start()
t2.start()
time.sleep(3);
print("Attributes of local_data: " + str(local_data.__dict__.keys()))
stop = True
print("Stop set to True. Waiting for threads to finish.")
t1.join()
t2.join()

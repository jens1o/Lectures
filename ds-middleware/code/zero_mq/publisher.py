#!/usr/bin/env python3
      
import signal
import time
import zmq


signal.signal(signal.SIGINT, signal.SIG_DFL)

context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind('tcp://*:5555')

while True:
    for i in range(5):
        status = b'status '+str(i).encode()
        print(f'Sending: {status}')
        socket.send(status)
        message = b'All is well'
        print(f'Sending: {message}')
        socket.send(message)
        time.sleep(1)





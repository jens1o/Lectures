#!/usr/bin/env python3
import socket
import queue
import threading
import concurrent.futures


HOST = "localhost"
PORT = 5678

print_queue = queue.Queue()

def print_queue_handler():
    while True:
        try:
            msg = print_queue.get()
            print(msg, end="")
        finally:
            print_queue.task_done()

def ts_print(msg):
    print_queue.put(msg)

def handle_connection(conn, addr):
    with conn:
        ts_print(f"Connection from {addr}.\n")
        with conn.makefile(mode="r", encoding="utf-8") as f:
            while True:
                data = f.readline()
                if not data:
                    ts_print(f"Connection closed ${addr}.\n")
                    return
                ts_print(f"Log[${addr}]: {data}")


def echo_server():
    with    socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server,\
            concurrent.futures.ThreadPoolExecutor() as tp:
        server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server.bind((HOST, PORT))
        server.listen(1)

        while True:
            conn, addr = server.accept()
            # handle_connection(conn, addr)
            tp.submit(handle_connection, conn, addr)


if __name__ == "__main__":
    threading.Thread(target=print_queue_handler, daemon=True).start()
    echo_server()
    print_queue.join()

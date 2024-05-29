#!/usr/bin/env python3
import socket
import queue
import threading
import concurrent.futures

HOST = "localhost"
PORT = 5678

PRINT_QUEUE = queue.Queue()


def print_queue_handler():
    while True:
        try:
            msg = PRINT_QUEUE.get()
            print(msg, end="")
        finally:
            PRINT_QUEUE.task_done()


def ts_print(msg):
    PRINT_QUEUE.put(msg)


def handle_connection(conn, host, port):
    addr = f"{host}:{port}"
    with conn:
        ts_print(f"Connection from {addr}.\n")
        with conn.makefile(mode="rw", encoding="utf-8") as f:
            while True:
                data = f.readline()
                if not data:
                    ts_print(f"Connection closed {addr}.\n")
                    return
                f.write("ACK\n")
                f.flush()
                ts_print(f"Log[{addr}]: {data}")


def run_server():
    with (
        socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server,
        concurrent.futures.ThreadPoolExecutor() as tp,
    ):
        server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server.bind((HOST, PORT))
        server.listen(1)

        while True:
            conn, (host, port) = server.accept()
            # Single-Threaded Solution: handle_connection(conn, addr)
            tp.submit(handle_connection, conn, host, port)


if __name__ == "__main__":
    threading.Thread(target=print_queue_handler, daemon=True).start()
    run_server()
    PRINT_QUEUE.join()

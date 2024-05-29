#!/usr/bin/env python3
import socket
import queue
import threading
import time

HOST = "localhost"
PORT = 5678

log_queue = queue.Queue()


def log_queue_handler():
    s = None
    f = None

    def establish_connection():
        nonlocal s, f
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((HOST, PORT))
        f = s.makefile(mode="rw", encoding="utf-8")
        return s

    establish_connection()

    def send(data):
        f.write(data)
        f.flush()
        if f.readline() != "ACK\n":
            raise Exception("no ACK received")

    while True:
        data = log_queue.get()
        try:
            print(f"Trying to send: {data}", end="")
            send(data)
            print(f"Succeeded sending: {data}", end="")
            log_queue.task_done()
        except Exception as e:
            print(f"Failed ({e}) sending {data}", end="")
            try:
                s.close()
                s = None
                f = None
            except Exception as e:
                print(f"Failed to close connection ({e}) {data}", end="")
                pass
            while True:
                try:
                    time.sleep(5)
                    establish_connection()
                    send(data)
                    log_queue.task_done()
                    break
                except Exception as e:
                    print(f"Failed ({e}) resending {data}", end="")
                    pass


def main():

    threading.Thread(target=log_queue_handler, daemon=True).start()

    while True:
        try:
            the_line = input() + "\n"
            log_queue.put(the_line)
        except (EOFError, KeyboardInterrupt):
            # We want to exit the program after
            # the user presses CTRL-D, but we
            # first want to wait for the queue
            # to be empty!
            log_queue.join()
            break
        except Exception as e:
            print(f"Error: {e}")
            break


if __name__ == "__main__":
    main()

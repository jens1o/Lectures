#!/usr/bin/env python3
import socket

HOST = "localhost"
PORT = 5678

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))  # Connect to localhost on port 7

    while True:
        try: 
            the_line = input()+"\n"
            s.sendall(the_line.encode())
        except (EOFError, KeyboardInterrupt):
            break
        except Exception as e:
            print(f"Error: {e}")
            break

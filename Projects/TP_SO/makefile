CC = gcc
CFLAGS = -Wall -pedantic

all: env frontend backend

env: env.c env.h
	$(CC) $(CFLAGS) -c env.c

frontend: frontend.c
	$(CC) $(CFLAGS) frontend.c env.o  -o frontend

backend: backend.c users_lib.o env.o
	$(CC) $(CFLAGS) backend.c users_lib.o env.o -o backend -pthread

clean:
	$(RM) frontend backend env.o
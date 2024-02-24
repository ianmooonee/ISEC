cmake_minimum_required(VERSION 3.23)
project(ficha C)

set(CMAKE_C_STANDARD 99)

add_executable(ficha main.c port.c ing.c)

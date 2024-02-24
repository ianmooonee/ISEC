cmake_minimum_required(VERSION 3.20)
project(exemplo_aluno)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(exemplo_aluno
        aluno.cpp
        aluno.h
        data.cpp
        data.h
        main.cpp)

//
// Created by ianmoone on 02/12/2021.
//

#ifndef F5_EX1_MSG_H
#define F5_EX1_MSG_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include <fstream>
using namespace std;

class Msg {
public:
    Msg(){
        cout << "Construtor por omissao " <<endl;
    }

    Msg(const char * p) {
        cout << "Ola " << p << "\n";
    }

    ~Msg() {
        cout << "Adeus\n";
    }
};


#endif //F5_EX1_MSG_H

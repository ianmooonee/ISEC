//
// Created by Ana Oliveira Alves on 03/11/2021.
//
#include <iostream>
#include "data.h"
#include <sstream>
#include <iomanip>

using namespace  std;

data::data(int d, int m, int a) {
    if(d > 0 && d <= 31 )
        this->d = d;
    else
        this->d = 1;
    if(m > 0 && m <= 12 )
        this->m = m;
    else
        this->m = 1;
    if(a > 0 )
        this->a = a;
    else
        this->a = 2021;
    cout << "A construir data" << getAsString();

}



std::string data::getAsString() const{
    ostringstream buffer;
    buffer << setfill('0') << setw(2) << d << '/' << m << '/' << a;
    return buffer.str();
}

data::~data() {
    cout << "A destruir data" << getAsString();
}




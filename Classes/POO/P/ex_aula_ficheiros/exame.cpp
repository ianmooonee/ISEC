//
// Created by Ana Oliveira Alves on 05/11/2021.
//
#include "Exame.h"
#include <iostream>
#include <sstream>
using namespace std;



Exame::Exame(std::string titulo, int d, int m, int a) : dia(d, m, a){
    this->titulo = titulo;
    cout << "A construir exame";
}

string Exame::getAsString() const {
    ostringstream os;
    os << "Exame:" << titulo << " em:" << dia.getAsString();
    return os.str();
}

void Exame::setTitulo(const string &titulo) {
    this->titulo = titulo;
}
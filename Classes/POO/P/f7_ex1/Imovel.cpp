//
// Created by Ana Oliveira Alves on 16/12/2021.
//

#include "imovel.h"
#include <sstream>
#include <iostream>

using namespace std;
int Imovel::contador=1;

Imovel::Imovel(const string &palavra, float preco, float area, int andar) {
    ostringstream os;
    os << palavra << contador++;
    id = os.str();
    this->preco = preco;
    this->area = area;
    this->andar = andar;
    cout << "Imovel(ctor)\n";
}

string Imovel::getCodigo() const {
    return id;
}

float Imovel::getPreco() const {
    return preco;
}

int Imovel::getAndar() const {
    return andar;
}

string Imovel::getAsString() const {
    ostringstream os;
    os << "Imovel: valor=" << preco << " area=" << area
       << "andar=" << andar;
    return os.str();
}

Imovel::~Imovel() {
    cout << "~Imovel()\n";
}
//Basta fazer na base o operador <<
ostream &operator<<(ostream &o, const Imovel &i) {
    o << i.getAsString();
    return o;
}

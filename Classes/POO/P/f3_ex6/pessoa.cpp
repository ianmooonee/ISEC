//
// Created by ianmoone on 09/11/2021.
//

#include "pessoa.h"

Pessoa::Pessoa(const string &nome, const int &cc, const int &nif):nome(nome), cc(cc), nif(nif){}

string Pessoa::getAsString() const{
    ostringstream buffer;
    buffer<<this->nome<<" CC: "<<this->cc<<" NIF: "<<this->nif<<endl;
    return buffer.str();
}

const string &Pessoa::getNome() const {
    return nome;
}

void Pessoa::setNome(const string &nome) {
    Pessoa::nome = nome;
}

int Pessoa::getCc() const {
    return cc;
}

int Pessoa::getNif() const {
    return nif;
}

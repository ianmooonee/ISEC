//
// Created by ianmoone on 12/11/2021.
//

#include "Pessoa.h"

Pessoa::Pessoa(const unsigned long &cc, const unsigned long &nif, const string &nome):nome(nome), cc(cc), nif(nif){}

string Pessoa::getAsString() const{
    ostringstream buffer;
    buffer<<this->nome<<" CC: "<<this->cc<<" NIF: "<<this->nif<<endl;
    return buffer.str();
}

const string &Pessoa::getNome() const{
    return nome;
}

void Pessoa::setNome(const string &nome) {
    this->nome = nome;
}

unsigned long Pessoa::getCc() const{
    return cc;
}

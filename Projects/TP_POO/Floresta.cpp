//
// Created by ianmoone on 21/11/2021.
//

#include "Floresta.h"

int Floresta::contador=1;

string Floresta::getAsString() const{
    ostringstream buffer;
    buffer<<this->getNome()<<" x: "<<this->getCordx()<<" y: "<<this->getCordy()<<" Arvores: "<<this->narvores;

    if(!this->getEdificios().empty()) {
        buffer << "   Edificio: ";

        for (int j = 0; j < this->getEdificios().size(); j++) {
            buffer << this->getEdificios().at(j)->getAsString();
        }
    }

    if(!this->getTrabalhadores().empty()) {
        buffer << "   Trabalhadores: ";

        for (int i = 0; i < this->getTrabalhadores().size(); i++) {
            buffer << this->getTrabalhadores().at(i)->getAsString() << " ";
        }
    }

    return buffer.str();
}

Floresta::Floresta(int cordx, int cordy):Zona(cordx, cordy){
    setNome("Floresta" + to_string(Floresta::contador++));
    narvores = 20 + (rand() % 20);
}
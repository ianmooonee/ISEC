//
// Created by ianmoone on 09/12/2021.
//

#include "Peixe.h"
#include "Aquario.h"
#include <sstream>

int Peixe::contador=500;

Peixe::Peixe(const string &nome, const string &cor):nome(nome), cor(cor), peso(10), id(contador++), vivo(true), dias_dieta(0), ondeEstou(
        nullptr){}

void Peixe::serAlimentado(int gramas) {
    int flag = (rand() % 2 == 0) ? 1 : 0;

    if (dias_dieta == 0 && vivo) {
        peso += gramas;

        if (peso > 50) {
            if (flag) {
                peso = 40;
                Peixe *novo = new Peixe(nome, cor);
                ondeEstou->adiciona(novo);
            } else {
                dias_dieta = 4;
            }
        }
    }
    else
        dias_dieta--;

    if(dias_dieta==0){
        vivo=false;
    }
}



string Peixe::getAsString() const {
    ostringstream buffer;
    buffer<<this->nome<<" "<<this->id<<" "<<this->cor<<" "<<this->vivo<<" "<<this->dias_dieta<<" "<<this->ondeEstou<<" "<<endl;
    return buffer.str();
}

Aquario *Peixe::getOndeEstou() {
    return this->ondeEstou;
}

void Peixe::setOndeEstou(Aquario *a) {
    this->ondeEstou=a;
}

int Peixe::getId() const {
    return this->id;
}

int Peixe::getPeso() const {
    return this->getPeso();
}

void Peixe::setPeso(int p) {
    this->peso=p;
}

bool Peixe::isVivo() const {
    return this->vivo;
}

void Peixe::setVivo(bool vivo) {
    this->vivo=vivo;
}

int Peixe::getDiasDieta() const {
    return dias_dieta;
}

void Peixe::setDiasDieta(int diasDieta) {
    dias_dieta = diasDieta;
}

const string &Peixe::getNome() const {
    return nome;
}

void Peixe::setNome(const string &nome) {
    Peixe::nome = nome;
}

const string &Peixe::getCor() const {
    return cor;
}

void Peixe::setCor(const string &cor) {
    Peixe::cor = cor;
}

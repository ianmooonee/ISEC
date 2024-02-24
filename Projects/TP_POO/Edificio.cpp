//
// Created by ianmoone on 10/01/2022.
//

#include "Edificio.h"

string Edificio::getAsString() const{
    ostringstream buffer;
    buffer<<this->nome;
    return buffer.str();
}

Edificio::Edificio(int producao) : producao(producao) {

}

const string &Edificio::getNome() const {
    return nome;
}

void Edificio::setNome(const string &nome) {
    Edificio::nome = nome;
}

int Edificio::getProducao() const {
    return producao;
}

void Edificio::setProducao(int producao) {
    Edificio::producao = producao;
}

int Edificio::getCusto() const {
    return custo;
}

void Edificio::setCusto(int custo) {
    Edificio::custo = custo;
}

int Edificio::getEstado() const {
    return estado;
}

void Edificio::setEstado(int estado) {
    Edificio::estado = estado;
}

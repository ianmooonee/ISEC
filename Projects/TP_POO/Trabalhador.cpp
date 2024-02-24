//
// Created by ianmoone on 10/01/2022.
//

#include "Trabalhador.h"

int Trabalhador::contador=1;

int Trabalhador::getContador() {
    return contador++;
}

string Trabalhador::getAsString() const{
    ostringstream buffer;
    buffer<<this->nome;
    return buffer.str();
}

const string &Trabalhador::getNome() const {
    return nome;
}

void Trabalhador::setNomeTrab(const string &nomee) {
    nome = nomee;
}

int Trabalhador::getDia_criacao() const {
    return dia_criacao;
}

void Trabalhador::setProducao(int dia_criacao) {
    this->dia_criacao = dia_criacao;
}

Trabalhador::Trabalhador(int dia_criacao) : dia_criacao(dia_criacao) {
}

int Trabalhador::getCusto() const {
    return custo;
}

void Trabalhador::setCusto(int custo) {
    this->custo = custo;
}

void Trabalhador::setMovido(int movido) {
    this->movido = movido;
}

int Trabalhador::getMovido() const {
    return movido;
}

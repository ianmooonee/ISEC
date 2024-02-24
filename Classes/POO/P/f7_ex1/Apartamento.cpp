//
// Created by Ana Oliveira Alves on 16/12/2021.
//

#include "apartamento.h"
#include <iostream>
#include <sstream>
using namespace std;

Apartamento::Apartamento(float area, int andar, int n_assoa):
//Qual o preço da renda de um apartamento?
//É tarefa de qual classe fazer esse cálculo?-> cada derivada faz o seu cálculo
        Imovel("apartamento", 10 * area, area, andar){
    this->n_assoalhadas = n_assoa;
    cout << "Apartamento(ctor)\n";
}

string Apartamento::getAsString() {
    ostringstream os;
    //para não chamar a própria versão desta classe
    os << Imovel::getAsString();
    os << "T" << n_assoalhadas << endl;
    return os.str();
}

Apartamento::~Apartamento() {
    cout << "~Apartamento()\n";
}
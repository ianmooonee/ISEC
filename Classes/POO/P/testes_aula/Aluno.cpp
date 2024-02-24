//
// Created by ianmoone on 29/10/2021.
//

#include "Aluno.h"
#define N 10

int Aluno::contador=0;

Aluno::Aluno(string nome){
    this->nome=nome;
    bool presencas[N];
    this->naluno=contador++;
    cout<<"A construir aluno..."<<endl;
};

Aluno::~Aluno(){ //destrutor, o this é opcional
    cout<<"Destruindo aluno "<<this->getAsString()<<endl;
};

int Aluno::getContador(){
    return contador; //apenas retorna o valor do contador, ou seja, a quantidade de objetos criados
};

string Aluno::getAsString(){
    ostringstream buffer;
    buffer<<"Nome: "<<nome<<" Numero de aluno: "<<naluno<<endl;
    return buffer.str();
};
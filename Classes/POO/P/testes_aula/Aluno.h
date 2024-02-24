//
// Created by ianmoone on 29/10/2021.
//

#ifndef TESTES_AULA_ALUNO_H
#define TESTES_AULA_ALUNO_H
#include <iostream>
#include <string>
#include <sstream>
using namespace std;
#define N 10

class Aluno {
    string nome;
    static int contador;
    bool presencas[N];
    int naluno;
public:
    Aluno(string nome="Antonio");
    ~Aluno();

    static int getContador();
    string getAsString();
};


#endif //TESTES_AULA_ALUNO_H

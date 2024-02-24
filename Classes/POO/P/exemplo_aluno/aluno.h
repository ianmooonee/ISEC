//
// Created by Ana Oliveira Alves on 29/10/2021.
//

#ifndef DUVIDAS_ALUNO_H
#define DUVIDAS_ALUNO_H
#define N 10
#include <string>
#include <initializer_list>
#include <vector>
#include "data.h"
class Aluno{
    std::string nome;
    bool presencas[N];
    int naluno;
    static int contador;
    std::vector<bool> presencas_vector; //para se comparar com o array fixo mais ali acima
                                        //inicialmente está vazio
    data matricula;
public:
    Aluno(const std::string & nome="Anonimo",
          std::initializer_list<bool> pre=
                  std::initializer_list<bool>());

    ~Aluno();

    static int getContador();
    std::string getAsString();
    bool eliminaPresencaoNoDadoDia(int dia);
    void eliminaFaltas();
};

void teste(Aluno &a);
#endif //DUVIDAS_ALUNO_H

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
#include "Exame.h"
class Aluno{
    std::string nome;
public:
    const std::string &getNome() const;

private:
    bool presencas[N];
    int naluno;
    static int contador;
    std::vector<bool> presencas_vector; //para se comparar com o array fixo mais ali acima
    //inicialmente está vazio
    data matricula; //Composição
    //Exame *exame_poo; //Agregação
    Exame & exame_poo; //Outra sintaxe para a agregação
    unsigned long nif;
public:
    unsigned long getNif() const;

public:
    Aluno(Exame * e, const std::string & nome="Anonimo",
          std::initializer_list<bool> pre=
          std::initializer_list<bool>());
    Aluno(std::string n, Exame *e );
    Aluno(Exame *e, const std::string & nome, unsigned long nif);
    ~Aluno();

    static int getContador();
    std::string getAsString();
    bool eliminaPresencaoNoDadoDia(int dia);
    void eliminaFaltas();
};

void teste(Aluno &a);
#endif //DUVIDAS_ALUNO_H

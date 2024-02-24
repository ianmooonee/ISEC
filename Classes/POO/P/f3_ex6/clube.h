//
// Created by ianmoone on 09/11/2021.
//

#ifndef F3_EX6_CLUBE_H
#define F3_EX6_CLUBE_H
#include "pessoa.h"

class Clube {
    vector<Pessoa> pessoas;
    string nome;
    string atividade;
public:
    Clube(const string &nome, const string &atividade);

    string getAsString() const;
    void acrescentaPessoa(const Pessoa &p);
    bool verificaSocio(const Pessoa &p);
    void removerSocio(const Pessoa &p);
};


#endif //F3_EX6_CLUBE_H

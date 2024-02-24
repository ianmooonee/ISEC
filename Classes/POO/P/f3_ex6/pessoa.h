//
// Created by ianmoone on 09/11/2021.
//

#ifndef F3_EX6_PESSOA_H
#define F3_EX6_PESSOA_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
using namespace std;

class Pessoa {
    string nome;
    int cc;
    int nif;
public:
    Pessoa(const string &nome, const int &cc, const int &nif);
    string getAsString() const;

    const string &getNome() const;

    void setNome(const string &nome);

    int getCc() const;

    int getNif() const;
};


#endif //F3_EX6_PESSOA_H

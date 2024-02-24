//
// Created by ianmoone on 12/11/2021.
//

#ifndef F3_EX7_PESSOA_H
#define F3_EX7_PESSOA_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include <fstream>
using namespace std;

class Pessoa {
    unsigned long nif;
    unsigned long cc;
    string nome;
    string morada;
public:
    Pessoa(const unsigned long &cc, const unsigned long &nif, const string &nome);
    string getAsString() const;

    const string &getNome() const;

    void setNome(const string &nome);

    unsigned long getCc() const;
};


#endif //F3_EX7_PESSOA_H

//
// Created by ianmoone on 12/11/2021.
//

#ifndef F3_EX7_BANCO_H
#define F3_EX7_BANCO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include <fstream>
#include "Conta.h"
using namespace std;

class Banco {
    string nome;
    vector<Conta> contas;
public:
    Banco(const string &nome);
    void novaConta(Pessoa &p);
    string getAsString() const;
    void depositarBanco(int cc_deposito, int quantia);
    int getSaldoTotal() const;
    void eliminarConta(int cc_eliminar);
};


#endif //F3_EX7_BANCO_H

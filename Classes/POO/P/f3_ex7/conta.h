//
// Created by ianmoone on 12/11/2021.
//

#ifndef F3_EX7_CONTA_H
#define F3_EX7_CONTA_H
#include "Pessoa.h"

class Conta {
    Pessoa *titular;
    int saldo;
public:
    Conta(Pessoa &titular, int saldo=0);
    string getAsString() const;

    const Pessoa *getTitular() const;

    void setSaldo(int saldo);

    int getSaldo() const;
};


#endif //F3_EX7_CONTA_H

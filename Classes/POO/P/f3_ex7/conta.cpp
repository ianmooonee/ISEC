//
// Created by ianmoone on 12/11/2021.
//

#include "Conta.h"

Conta::Conta(Pessoa &titular, int saldo) : titular(&titular), saldo(saldo){}

string Conta::getAsString()const{
        ostringstream os;

        os << "Titular: " << titular->getNome() << " CC: " << titular->getCc() << " Saldo:" << this->saldo << endl;

        return os.str();
}

const Pessoa *Conta::getTitular() const{
    return titular;
}

void Conta::setSaldo(int saldo) {
    Conta::saldo = saldo;
}

int Conta::getSaldo() const{
    return saldo;
}

//
// Created by ianmoone on 12/11/2021.
//
#include "Banco.h"

Banco::Banco(const string &nome) : nome(nome) {}

void Banco::novaConta(Pessoa &p){
    Conta c(p);
    this->contas.push_back(c);
}

string Banco::getAsString() const{
    ostringstream os;

    os << "Banco :" << nome << endl;
    for(int i=0;  i< contas.size(); i++)
        os << this->contas.at(i).getAsString();
    return os.str();
}

void Banco::depositarBanco(int cc_deposito, int quantia){
    for(int i=0; i<this->contas.size(); i++){
        if(this->contas.at(i).getTitular()->getCc()==cc_deposito){
            this->contas.at(i).setSaldo(this->contas.at(i).getSaldo()+quantia);
        }
    }
}

int Banco::getSaldoTotal() const{
    int total=0;

    for(int i=0; i<this->contas.size(); i++){
        total+=this->contas.at(i).getSaldo();
    }

    return total;
}

void Banco::eliminarConta(int cc_eliminar){
    for(int i=0;i<this->contas.size(); i++){
        if(this->contas.at(i).getTitular()->getCc()==cc_eliminar){
            this->contas.erase(this->contas.begin()+i);
            break; //devia sair porque o ciclo já não funciona
        }
    }
}
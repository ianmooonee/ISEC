//
// Created by ianmoone on 17/12/2021.
//

#include "Peixe.h"
#include "Aquario.h"
#include <string>
using namespace  std;

int Peixe::contador=500;

Peixe::Peixe(const string &n, const string &c):nome(n),
                                               cor(c){
    peso=10;
    id=contador++;
    ondeEstou=nullptr;
    dias_dieta=0;
    vivo=true;
}

Aquario *Peixe::getOndeEstou() {
    return ondeEstou;
}

void Peixe::serAlimentado(int gramas) {
    int fator = rand() % 100;
    peso += gramas;
    if(peso > 50){
        if(fator < 50){
            peso = 40;
            Peixe *novo = new Peixe(nome, cor);
            ondeEstou->adiciona(novo);
        }else{

        }

    }


}

Peixe *Peixe::clone() const {
    return new Peixe(nome, cor);
}
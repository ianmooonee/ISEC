//
// Created by ianmoone on 10/01/2022.
//

#include "Fundicao.h"

int Fundicao::contador=1;

Fundicao::Fundicao(int producao) : Edificio(producao) {
    setNome("Fundicao" + to_string(Fundicao::contador++));
    setCusto(10);
    setProducao(10);
}
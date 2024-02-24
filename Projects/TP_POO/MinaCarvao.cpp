//
// Created by ianmoone on 10/01/2022.
//

#include "MinaCarvao.h"
int MinaCarvao::contador=1;

MinaCarvao::MinaCarvao(int producao) : Edificio(producao) {
    setNome("MinaCarvao" + to_string(MinaCarvao::contador++));
    setCusto(10);
    setProducao(10);
}
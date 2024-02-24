//
// Created by ianmoone on 21/11/2021.
//

#include "MinaFerro.h"

int MinaFerro::contador=1;

MinaFerro::MinaFerro(int producao) : Edificio(producao) {
    setNome("MinaFerro" + to_string(MinaFerro::contador++));
    setCusto(10);
    setProducao(10);
}
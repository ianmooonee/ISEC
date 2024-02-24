//
// Created by ianmoone on 16/01/2022.
//

#include "Serracao.h"
int Serracao::contador=1;

Serracao::Serracao(int producao) : Edificio(producao) {
    setNome("Serracao" + to_string(Serracao::contador++));
    setCusto(20);
    setProducao(10);
}
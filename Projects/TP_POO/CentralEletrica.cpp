//
// Created by ianmoone on 10/01/2022.
//

#include "CentralEletrica.h"

int CentralEletrica::contador=1;

CentralEletrica::CentralEletrica(int producao) : Edificio(producao) {
    setNome("CentralEletrica" + to_string(CentralEletrica::contador++));
    setCusto(15);
    setProducao(10);
}
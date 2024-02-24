//
// Created by ianmoone on 10/01/2022.
//

#include "Bateria.h"

int Bateria::contador=1;

Bateria::Bateria(int producao) : Edificio(producao){
    setNome("Bateria" + to_string(Bateria::contador++));
    setCusto(10);
    setProducao(10);

}
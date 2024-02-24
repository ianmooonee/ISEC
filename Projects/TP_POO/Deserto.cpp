//
// Created by ianmoone on 16/11/2021.
//

#include "Deserto.h"

int Deserto::contador=1;

Deserto::Deserto(int cordx, int cordy):Zona(cordx, cordy){
    setNome("Deserto" + to_string(Deserto::contador++));
}


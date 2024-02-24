//
// Created by ianmoone on 21/11/2021.
//

#include "Pantano.h"

int Pantano::contador=1;

Pantano::Pantano(int cordx, int cordy):Zona(cordx, cordy){
    setNome("Pantano" + to_string(Pantano::contador++));
}
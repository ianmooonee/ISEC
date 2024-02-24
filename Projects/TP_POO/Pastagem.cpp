//
// Created by ianmoone on 19/11/2021.
//

#include "Pastagem.h"

int Pastagem::contador=1;

Pastagem::Pastagem(int cordx, int cordy):Zona(cordx, cordy){
    setNome("Pastagem" + to_string(Pastagem::contador++));
}


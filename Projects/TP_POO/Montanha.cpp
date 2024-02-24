//
// Created by ianmoone on 21/11/2021.
//

#include "Montanha.h"

int Montanha::contador=1;

Montanha::Montanha(int cordx, int cordy) : Zona(cordx, cordy) {
    setNome("Montanha" + to_string(Montanha::contador++));
}
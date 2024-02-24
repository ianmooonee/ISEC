//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_BATERIA_H
#define POO_TP_M1_BATERIA_H


#include "utils.h"
#include "Edificio.h"


class Bateria : public Edificio{
    static int contador;
public:
    Bateria(int producao);
    Bateria* clone() { return new Bateria(*this);};
};


#endif //POO_TP_M1_BATERIA_H

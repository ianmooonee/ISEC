//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_CENTRALELETRICA_H
#define POO_TP_M1_CENTRALELETRICA_H

#include "utils.h"
#include "Edificio.h"


class CentralEletrica : public Edificio{
    static int contador;
public:
    CentralEletrica(int producao);
    CentralEletrica* clone(){ return new CentralEletrica(*this);};
};


#endif //POO_TP_M1_CENTRALELETRICA_H

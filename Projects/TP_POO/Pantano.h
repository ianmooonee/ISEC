//
// Created by ianmoone on 21/11/2021.
//

#ifndef POO_TP_M1_PANTANO_H
#define POO_TP_M1_PANTANO_H
#include "utils.h"
#include "Mineiro.h"
#include "Zona.h"

class Pantano : public Zona{
    static int contador;
public:
    Pantano(int cordx, int cordy);
    Pantano* clone() { return new Pantano(*this);};
};


#endif //POO_TP_M1_PANTANO_H

//
// Created by ianmoone on 21/11/2021.
//

#ifndef POO_TP_M1_MONTANHA_H
#define POO_TP_M1_MONTANHA_H
#include "utils.h"
#include "Mineiro.h"
#include "Zona.h"

class Montanha : public Zona{
    static int contador;
public:
    Montanha(int cordx, int cordy);
    Montanha* clone() { return new Montanha(*this);};
};


#endif //POO_TP_M1_MONTANHA_H

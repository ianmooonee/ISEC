//
// Created by ianmoone on 21/11/2021.
//

#ifndef POO_TP_M1_MINAFERRO_H
#define POO_TP_M1_MINAFERRO_H
#include "utils.h"
#include "Edificio.h"


class MinaFerro : public Edificio{
    static int contador;
public:
    MinaFerro(int producao);
    MinaFerro* clone() { return new MinaFerro(*this);};
};


#endif //POO_TP_M1_MINAFERRO_H

//
// Created by ianmoone on 16/11/2021.
//

#ifndef POO_TP_M1_DESERTO_H
#define POO_TP_M1_DESERTO_H
#include "utils.h"
#include "Zona.h"
#include "Mineiro.h"

class Deserto : public Zona {
    static int contador;
public:
    Deserto(int cordx, int cordy);
    Deserto* clone() override { return new Deserto(*this);};
};


#endif //POO_TP_M1_DESERTO_H

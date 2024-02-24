//
// Created by ianmoone on 19/11/2021.
//

#ifndef POO_TP_M1_PASTAGEM_H
#define POO_TP_M1_PASTAGEM_H
#include "utils.h"
#include "Mineiro.h"
#include "MinaFerro.h"
#include "Zona.h"

class Pastagem : public Zona {
    static int contador;
public:
    Pastagem(int cordx, int cordy);
    Pastagem* clone() { return new Pastagem(*this);};
};


#endif //POO_TP_M1_PASTAGEM_H

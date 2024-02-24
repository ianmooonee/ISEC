//
// Created by ianmoone on 21/11/2021.
//

#ifndef POO_TP_M1_FLORESTA_H
#define POO_TP_M1_FLORESTA_H
#include "utils.h"
#include "Mineiro.h"
#include "Zona.h"

class Floresta :public Zona {
    static int contador;
    int narvores;
public:
    Floresta(int cordx, int cordy);
    string getAsString() const override;
    Floresta* clone() { return new Floresta(*this);};
};


#endif //POO_TP_M1_FLORESTA_H

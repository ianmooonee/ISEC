//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_MINACARVAO_H
#define POO_TP_M1_MINACARVAO_H

#include "utils.h"
#include "Edificio.h"


class MinaCarvao : public Edificio{
    static int contador;
public:
    MinaCarvao(int producao);
    MinaCarvao* clone() { return new MinaCarvao(*this);};
};

#endif //POO_TP_M1_MINACARVAO_H

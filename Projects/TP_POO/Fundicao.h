//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_FUNDICAO_H
#define POO_TP_M1_FUNDICAO_H


#include "utils.h"
#include "Edificio.h"


class Fundicao : public Edificio{
    static int contador;
public:
    Fundicao(int producao);
    Fundicao* clone() { return new Fundicao(*this);};
};


#endif //POO_TP_M1_FUNDICAO_H

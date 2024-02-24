//
// Created by ianmoone on 16/01/2022.
//

#ifndef POO_TP_M1_SERRACAO_H
#define POO_TP_M1_SERRACAO_H
#include "utils.h"
#include "Edificio.h"


class Serracao : public Edificio{
    static int contador;
public:
    Serracao(int producao);
    Serracao* clone() { return new Serracao(*this);};
};


#endif //POO_TP_M1_SERRACAO_H

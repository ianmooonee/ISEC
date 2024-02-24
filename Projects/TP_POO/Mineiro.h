//
// Created by ianmoone on 18/11/2021.
//

#ifndef POO_TP_M1_MINEIRO_H
#define POO_TP_M1_MINEIRO_H
#include "utils.h"
#include "Trabalhador.h"


class Mineiro : public Trabalhador{
public:
    Mineiro(int dia_criacao);
    Mineiro* clone() { return new Mineiro(*this);};
};


#endif //POO_TP_M1_MINEIRO_H

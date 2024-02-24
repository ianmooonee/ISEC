//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_OPERARIO_H
#define POO_TP_M1_OPERARIO_H
#include "utils.h"
#include "Trabalhador.h"

class Operario : public Trabalhador{

public:
    Operario(int dia_criacao);
    Operario* clone() { return new Operario(*this);};
};


#endif //POO_TP_M1_OPERARIO_H

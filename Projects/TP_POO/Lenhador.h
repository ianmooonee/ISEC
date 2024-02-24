//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_LENHADOR_H
#define POO_TP_M1_LENHADOR_H
#include "utils.h"
#include "Trabalhador.h"

class Lenhador : public Trabalhador{

public:
    Lenhador(int dia_criacao);
    Lenhador* clone() { return new Lenhador(*this);};
};


#endif //POO_TP_M1_LENHADOR_H

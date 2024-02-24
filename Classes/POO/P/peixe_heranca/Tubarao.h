//
// Created by ianmoone on 17/12/2021.
//

#ifndef PEIXE_HERANCA_TUBARAO_H
#define PEIXE_HERANCA_TUBARAO_H

#include "Peixe.h"

class Tubarao : public Peixe{
public:
    Tubarao();
    void serAlimentado(int gramas);
    std::string getAsString() const;
    Tubarao *clone() const override; //override avisa se uma redefinição não é igual à versão da base
};


#endif //PEIXE_HERANCA_TUBARAO_H

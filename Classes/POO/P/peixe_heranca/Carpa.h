//
// Created by ianmoone on 17/12/2021.
//

#ifndef PEIXE_HERANCA_CARPA_H
#define PEIXE_HERANCA_CARPA_H

#include "Peixe.h"

class Carpa : public Peixe{
public:
    Carpa();
    void serAlimentado(int gramas);
    std::string getAsString() const;
};

#endif //PEIXE_HERANCA_CARPA_H

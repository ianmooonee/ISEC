//
// Created by Ana Oliveira Alves on 05/11/2021.
//

#ifndef DUVIDAS_EXAME_H
#define DUVIDAS_EXAME_H


#include "Exame.h"
#include <string>
#include "data.h"
class Exame{
    std::string titulo;
public:
    void setTitulo(const std::string &titulo);

private:
    data dia;
public:
    Exame(std::string titulo, int d, int m, int a);
    std::string getAsString() const;
};


#endif //DUVIDAS_EXAME_H
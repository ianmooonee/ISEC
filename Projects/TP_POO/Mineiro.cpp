//
// Created by ianmoone on 18/11/2021.
//

#include "Mineiro.h"

Mineiro::Mineiro(int dia_criacao) : Trabalhador(dia_criacao){
    ostringstream os;
    os<<"Mineiro" + to_string(dia_criacao) + "." + to_string(Trabalhador::getContador());

    setNomeTrab(os.str());
    setCusto(10);
}
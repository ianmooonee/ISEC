//
// Created by ianmoone on 10/01/2022.
//

#include "Operario.h"

Operario::Operario(int dia_criacao) : Trabalhador(dia_criacao) {
    ostringstream os;
    os<<"Operador" + to_string(dia_criacao) + "." + to_string(Trabalhador::getContador());

    setNomeTrab(os.str());
    setCusto(15);
}

//
// Created by ianmoone on 10/01/2022.
//

#include "Lenhador.h"

Lenhador::Lenhador(int dia_criacao) : Trabalhador(dia_criacao) {
    ostringstream os;
    os<<"Lenhador" + to_string(dia_criacao) + "." + to_string(Trabalhador::getContador());

    setNomeTrab(os.str());
    setCusto(20);
}

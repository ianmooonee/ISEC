//
// Created by ianmoone on 17/11/2021.
//

#ifndef POO_TP_M1_CONTROLADOR_H
#define POO_TP_M1_CONTROLADOR_H
#include "Ilha.h"
#define TAMCOM 17

class Controlador {
    string Comandos[TAMCOM] = {
            "exec",
            "cons",
            "liga",
            "des",
            "move",
            "vende",
            "cont",
            "list",
            "next",
            "save",
            "load",
            "apaga",
            "config",
            "debcash",
            "debed",
            "debkill",
            "terminar"
    };
public:

    Controlador(Ilha& i);
    void comandos(Ilha& i);
    void cons(Ilha &i, const string &tipoedi, const int &cordx, const int &cordy);
    void cont(Ilha &i, const string &tipoTrab);
    void list(Ilha &i, istringstream &ss);
    void execFich(Ilha &i, const string &nomeFich);

};


#endif //POO_TP_M1_CONTROLADOR_H

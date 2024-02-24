//
// Created by ianmoone on 05/11/2021.
//

#ifndef F3_EX5_AVISO_H
#define F3_EX5_AVISO_H
#include "prego.h"


class Aviso {
    string texto;
    Prego &preg;
public:


    Aviso(Prego &preg, const string &texto);

    string getAsString() const;
};


#endif //F3_EX5_AVISO_H

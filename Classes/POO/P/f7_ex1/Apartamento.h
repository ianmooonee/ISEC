#ifndef APARTAMENTO_H
#define APARTAMENTO_H
#include "imovel.h"

class Apartamento :public Imovel {
    int n_assoalhadas;
public:
    Apartamento(float area, int andar, int n_assoa);
    virtual string getAsString()/*const*/;
    ~Apartamento();
};

#endif

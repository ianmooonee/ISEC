#ifndef LOJA_H
#define LOJA_H
#include "imovel.h"

class Loja :public Imovel {
    static int conta;
public:
    Loja(float area);
    virtual string getAsString()/*const*/;
    ~Loja();
};

#endif

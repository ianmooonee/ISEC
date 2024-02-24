//
// Created by ianmoone on 04/11/2021.
//

#ifndef F3_EX3_RETANGULO_H
#define F3_EX3_RETANGULO_H

#include "ponto.h"

class Retangulo {
    int alt;
    int larg;
    Ponto cse;
public:
    Retangulo(Ponto &p, int alt, int larg);
    Retangulo(int x, int y, int alt, int larg);
    string getAsString() const;
    int getAlt() const;
    float area() const;
    void setAlt(int nalt);
    void setCanto(Ponto p);
    int getCseX();
    int getCseY();
    bool intersecao(vector<Retangulo> vec);
};


#endif //F3_EX3_RETANGULO_H

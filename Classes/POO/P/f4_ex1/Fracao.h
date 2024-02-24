//
// Created by ianmoone on 18/11/2021.
//

#ifndef F4_EX1_FRACAO_H
#define F4_EX1_FRACAO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include <fstream>
using namespace std;

class Fracao {
private:
    int numerador;
    int denominador;
public:
    Fracao(int numerador=0, int denominador=1);

    int getNumerador() const;
    void setNumerador(int numerador);
    int getDenominador() const;
    bool setDenominador(int denominador);
    string getAsString() const;
    //Fracao operator*(Fracao f);
    Fracao &operator*=(const Fracao &f1);

    bool operator<(const Fracao &rhs) const;

    bool operator>(const Fracao &rhs) const;

    bool operator<=(const Fracao &rhs) const;

    bool operator>=(const Fracao &rhs) const;

    bool operator==(const Fracao &rhs) const;

    bool operator!=(const Fracao &rhs) const;

    operator string() const;
};

Fracao operator*(Fracao f1, Fracao f2);
ostream &operator<<(ostream &os, const Fracao &fracao);
istream &operator>>(istream &is, Fracao &fracao);
Fracao &operator++(Fracao &f1);
Fracao operator++(Fracao &f1, int);



#endif //F4_EX1_FRACAO_H

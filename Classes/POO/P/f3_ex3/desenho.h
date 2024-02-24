//
// Created by ianmoone on 04/11/2021.
//

#ifndef F3_EX3_DESENHO_H
#define F3_EX3_DESENHO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include "retangulo.h"
using namespace std;

class Desenho {
    string nome;
    vector<Retangulo> recs;
public:
    Desenho(const string &nome);

    string getAsString() const;
    //void acrescenta(Retangulo r);
    void acrescentav2(Retangulo r);
    void setNome(const string &nome1);
    vector<Retangulo> getRetangulosCoincidentes();
    float somaArea();
};


#endif //F3_EX3_DESENHO_H

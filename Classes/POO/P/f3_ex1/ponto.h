//
// Created by ianmoone on 29/10/2021.
//

#ifndef F3_EX1_PONTO_H
#define F3_EX1_PONTO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <math.h>
using namespace std;

class Ponto {
    int xcord;
    int ycord;
public:
    Ponto(int x=0, int y=0);
    string getAsString() const;
    int getCordX() const;
    int getCordY() const;
    void setCordX(int x);
    void setCordY(int y);
    float dist(Ponto &p1);

};

float dist(Ponto &p1, Ponto &p2);

#endif //F3_EX1_PONTO_H

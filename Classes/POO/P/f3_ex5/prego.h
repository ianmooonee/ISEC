//
// Created by ianmoone on 05/11/2021.
//

#ifndef F3_EX5_PREGO_H
#define F3_EX5_PREGO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
using namespace std;

class Prego{
    int x,y;
public:
    Prego(int a, int b);
    ~Prego();

    void mudaDeSitio(int a, int b);
    int getX() const ;
    void setX(int x);
    int getY() const;
    void setY(int y);
};


#endif //F3_EX5_PREGO_H

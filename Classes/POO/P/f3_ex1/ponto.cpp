//
// Created by ianmoone on 29/10/2021.
//

#include "ponto.h"

Ponto::Ponto(int x, int y) {
    this->xcord=x;
    this->ycord=y;
}

 string Ponto::getAsString() const{
    ostringstream buffer;
    buffer<<"Ponto: "<<this->xcord<<" "<<this->ycord;
    return buffer.str();
}

int Ponto::getCordX() const{
    return this->xcord;
}

int Ponto::getCordY() const{
    return this->ycord;
}

void Ponto::setCordX(int x){
    this->xcord=x;
}

void Ponto::setCordY(int y){
    this->ycord=y;
}

/*float dist(Ponto p1, Ponto p2){
    return sqrt(((p2.xcord-p1.xcord)^2)+((p2.ycord-p1.ycord)));
}*/

float Ponto::dist(Ponto &p1){
    return sqrt((pow((this->getCordX()-p1.getCordX()),2))+(pow((this->getCordY()-p1.getCordY()),2)));
}

float dist(Ponto &p1, Ponto &p2){
    return sqrt((pow((p2.getCordX()-p1.getCordX()),2))+(pow((p2.getCordY()-p1.getCordY()),2)));
}
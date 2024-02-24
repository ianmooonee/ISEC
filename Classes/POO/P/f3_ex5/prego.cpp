//
// Created by ianmoone on 05/11/2021.
//

#include "prego.h"
Prego::Prego(int a, int b) {
    x = a; y = b;
    //cout << "Construindo prego em " << x << "," << y << "\n";
}

Prego::~Prego() {
    //cout << "Desnstruindo prego em " << x << "," << y << "\n";
}

void Prego::mudaDeSitio(int a, int b) {
    x = a; y = b;
}

int Prego::getX() const {
    return x;
}

void Prego::setX(int x) {
    this->x = x;
}

int Prego::getY() const {
    return y;
}

void Prego::setY(int y) {
    this->y = y;
}
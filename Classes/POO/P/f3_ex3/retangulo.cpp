//
// Created by ianmoone on 04/11/2021.
//

#include "retangulo.h"

Retangulo::Retangulo(Ponto &p, int alt, int larg): cse(p), alt(alt), larg(larg){
};

Retangulo::Retangulo(int x, int y, int alt, int larg): cse(x,y), alt(alt), larg(larg){
};

string Retangulo::getAsString() const{
    ostringstream buffer;
    buffer<<"Retangulo cse: "<<this->cse.getCordX()<<" "<<this->cse.getCordY()<<" Altura: "<<this->alt<<" Largura: "<<this->larg<<" ";
    return buffer.str();
}

int Retangulo::getAlt() const {
    return alt;
}

void Retangulo::setAlt(int nalt) {
    this->alt=nalt;
}

float Retangulo::area() const{
    return this->alt*this->larg;
}

int Retangulo::getCseX(){
    return cse.getCordX();
}

int Retangulo::getCseY(){
    return cse.getCordY();
}

void Retangulo::setCanto(Ponto p){
    this->cse.setCordX(p.getCordX());
    this->cse.setCordY(p.getCordY());
}

bool Retangulo::intersecao(vector<Retangulo> vec){ //apenas verifica se os retângulos têm o mesmo cse
    if(vec.empty()){
        return true;
    }
    for(auto el:vec) {
        if (this->cse.getCordY() == el.cse.getCordY() && this->cse.getCordX() == el.cse.getCordX()) {
            return false;
        }
    }

    return true;
}
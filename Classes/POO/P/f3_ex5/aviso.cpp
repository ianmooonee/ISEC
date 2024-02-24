    //
// Created by ianmoone on 05/11/2021.
//

#include "aviso.h"

Aviso::Aviso(Prego &preg, const string &texto) : preg(preg), texto(texto) {}

string Aviso::getAsString() const{
    ostringstream buffer;
    buffer<<this->texto<<" no prego ("<<this->preg.getX()<<","<<this->preg.getY()<<")"<<endl;
    return buffer.str();
}

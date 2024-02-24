//
// Created by ianmoone on 19/11/2021.
//

#include "Texto.h"

string Texto::getAsString() const{
    ostringstream os;
    os<<this->palavra;
    return os.str();

}
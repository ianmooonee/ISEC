//
// Created by ianmoone on 18/11/2021.
//

#include "Fracao.h"

Fracao::Fracao(int numerador, int denominador) : numerador(numerador){
    if(!setDenominador(denominador)){
        this->denominador=1;
    }
}

int Fracao::getNumerador() const {
    return numerador;
}

void Fracao::setNumerador(int numerador) {
    Fracao::numerador = numerador;
}

int Fracao::getDenominador() const {
    return denominador;
}

bool Fracao::setDenominador(int denominador) {
    if(denominador>0) {
        this->denominador = denominador;
        return true;
    }
    return false;
}

string Fracao::getAsString() const{
    ostringstream buffer;
    buffer<<this->numerador<<"/"<<this->denominador<<endl;
    return buffer.str();
}

/*Fracao Fracao::operator*(Fracao f){
    int novonum=f.numerador*this->numerador;
    int novoden=f.denominador*this->denominador;

    Fracao nova(novonum, novoden);

    return nova;
}*/

Fracao &Fracao::operator*=(const Fracao &f1){ //como o retorn é uma fracao que já existe temos de retirnar a referência
                                             //porque o this existe mesmo depois da função terminar
    *this=*this*f1;
    return *this;
}

bool Fracao::operator<(const Fracao &rhs) const {
    if (numerador < rhs.numerador)
        return true;
    if (rhs.numerador < numerador)
        return false;
    return denominador < rhs.denominador;
}

bool Fracao::operator>(const Fracao &rhs) const {
    return rhs < *this;
}

bool Fracao::operator<=(const Fracao &rhs) const {
    return !(rhs < *this);
}

bool Fracao::operator>=(const Fracao &rhs) const {
    return !(*this < rhs);
}

ostream &operator<<(ostream &os, const Fracao &fracao){
    os<<fracao.getAsString();
    return os;
}

std::istream &operator>>(std::istream &is, Fracao &fracao){
    int n, d;
    is >> n; //Ler cada parte do objeto e modificá-lo
    fracao.setNumerador(n);
    is >> d; //Ler cada parte do objeto e modificá-lo
    fracao.setDenominador(d);
    return is;
}

Fracao operator*(Fracao f1, Fracao f2){
    int novonum=f1.getNumerador()*f2.getNumerador();
    int novoden=f1.getDenominador()*f2.getDenominador();

    Fracao nova(novonum, novoden);

    return nova;
}

bool Fracao::operator==(const Fracao &rhs) const {
    return numerador == rhs.numerador &&
           denominador == rhs.denominador;
}

bool Fracao::operator!=(const Fracao &rhs) const {
    return !(rhs == *this);
}

Fracao &operator++(Fracao &f1){
    f1.setNumerador(f1.getNumerador()+f1.getDenominador());
    return f1;
}

Fracao operator++(Fracao &f1, int){ //não se pode retornar referências de algo que deixa de existir
    Fracao nova(f1.getNumerador(), f1.getDenominador());
    f1.setNumerador(f1.getNumerador()+f1.getDenominador());
    return nova; //valor antigo
}

Fracao::operator string() const {
    cout<<"A converter para string..."<<endl;
    return this->getAsString();
}
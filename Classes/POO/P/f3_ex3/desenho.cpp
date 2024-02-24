//
// Created by ianmoone on 04/11/2021.
//

#include "desenho.h"

Desenho::Desenho(const string &nome){
    this->nome=nome;
}

string Desenho::getAsString() const{
    ostringstream buffer;
    buffer<<this->nome;
    for(auto el:this->recs){
        buffer<<" "<<el.getAsString()<<" ";
    }

    return buffer.str();
}

/*void Desenho::acrescenta(Retangulo r){
        this->recs.push_back(r);
}*/

void Desenho::acrescentav2(Retangulo r){
    if(r.intersecao(recs)){
        this->recs.push_back(r);
    }

}

void Desenho::setNome(const string &nome1) {
    this->nome=nome1;
}

vector<Retangulo> Desenho::getRetangulosCoincidentes(){
    vector<Retangulo> temp;

    for(int i=0;i<recs.size();i++){
        for(int j=i+1;j<recs.size();j++){
            if(recs.at(i).getCseX()==recs.at(j).getCseX() && recs.at(i).getCseY()==recs.at(j).getCseY()){
                temp.push_back(recs.at(i));
                temp.push_back(recs.at(j));
            }
        }
    }

    return temp;
};

float Desenho::somaArea(){
    float soma=0;

    for(auto el:this->recs){
        soma+=el.area();
    }

    return soma;
};
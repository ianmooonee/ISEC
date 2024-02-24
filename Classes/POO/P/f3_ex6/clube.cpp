//
// Created by ianmoone on 09/11/2021.
//

#include "clube.h"
#include "pessoa.h"

Clube::Clube(const string &nome, const string &atividade):nome(nome), atividade(atividade){}

string Clube::getAsString() const{
    ostringstream buffer;
    buffer<<"Clube: "<<this->nome<<" Atividade: "<<this->atividade<<" Pessoas: ";
    for(auto el:this->pessoas){
        buffer<<el.getNome()<< " ";
    }
    cout<<endl;
    return buffer.str();
}

void Clube::acrescentaPessoa(const Pessoa &p){
    this->pessoas.push_back(p);
}

bool Clube::verificaSocio(const Pessoa &p){
    for(auto el:this->pessoas){
        if(p.getCc()==el.getCc()){
            return true;
        }
    }
    return false;
}

void Clube::removerSocio(const Pessoa &p){
    for(int i=0; i<this->pessoas.size(); i++){
        if(p.getCc()==this->pessoas.at(i).getCc()){
            this->pessoas.erase(this->pessoas.begin()+ i);
        }
    }
}
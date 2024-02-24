//
// Created by ianmoone on 09/12/2021.
//

#include "Aquario.h"
#include <sstream>

bool Aquario::adiciona(Peixe *p) {
    if(p->getOndeEstou()!=this) {
        this->peixes.push_back(p);
        p->setOndeEstou(this);
    }
}

void Aquario::alimenta(int gramas) {
    for(auto el:peixes)
        el->serAlimentado(gramas);
    removeMortos();
}

void Aquario::removeMortos() {
    auto ptr=peixes.begin();
    while(ptr != peixes.end())
        if(!(*ptr)->isVivo())
            ptr=peixes.erase(ptr);
        else
            ptr++;
}

const Peixe *Aquario::getPeixe(int numserie) {
    return nullptr;
}

int Aquario::procura(int id) const {
    return 0;
}

bool Aquario::remove(int id) {
    return false;
}

string Aquario::getAsString() const {
    ostringstream buffer;

    for(auto el:peixes)
        buffer<<el->getAsString()<<endl;

    return buffer.str();
}

Aquario::~Aquario() {
    for(auto el:peixes)
        delete el;
}

Peixe *Aquario::DaPeixeAleatorio() {
    if(peixes.size()>0){
        int peixe=rand()% peixes.size();
        return peixes.at(peixe);
    }
    return nullptr;
}

Aquario::Aquario(const Aquario &outro) {
    *this=outro;
}

Aquario &Aquario::operator=(const Aquario &outro) {
    //comparar se não é o próprio
    if(this!=&outro) {
        //libertar toda a memória
        for(auto el:peixes)
            delete el;

        //limpar o vetor
        peixes.clear();

        //copiar tudo do outro
        //caso memoria -> new para este
        for(auto el:outro.peixes)
            peixes.push_back(new Peixe(*el));
    }
    return *this;
}

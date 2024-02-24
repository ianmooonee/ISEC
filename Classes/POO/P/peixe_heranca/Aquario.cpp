//
// Created by Ana Oliveira Alves on 10/12/2021.
//

#include "Aquario.h"

void Aquario::removeMortos() {
    auto ptr=peixes.begin();
    while(ptr != peixes.end())
        if(!(*ptr)->isVivo())
            ptr=peixes.erase(ptr);
        else
            ptr++;
}

bool Aquario::adiciona(Peixe *p) {
    if(p->getOndeEstou() != this){
        peixes.push_back(p);
        p->setOndeEstou(this);
        return true;
    }
    return false;
}

void Aquario::alimenta(int gramas) {
    std::vector<Peixe *> copia=peixes;
    for(auto el: copia) //Só alimenta os que já existiam
        el->serAlimentado(gramas);
    removeMortos();
}

Peixe *Aquario::getPeixeAleatorio() {
    if(peixes.size() > 0) {
        int peixe = rand() % peixes.size();
        return peixes.at(peixe);
    }
    return nullptr;
}

Aquario::~Aquario() {
    for(auto el: peixes)
        delete el;
}

Aquario::Aquario(const Aquario &outro) {
    //Copiar aquario utilizando o operador mais abaixo
    *this = outro;
}

Aquario &Aquario::operator=(const Aquario &outro) {
    //Testar se é o próprio
    if(this != &outro){
        //Apagar os peixes antigos para passar a ter duplicados novos do outro aquario
        for(auto el: peixes)
            delete el;
        peixes.clear();

        //Copiar novos peixes (new) a partir do outro aquario  e acrescentar neste vector
        for(auto el: outro.peixes)
            peixes.push_back(new Peixe(el));
    }
    return *this;
}
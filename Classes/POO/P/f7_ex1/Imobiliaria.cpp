//
// Created by Ana Oliveira Alves on 16/12/2021.
//

#include "imobiliaria.h"

using namespace  std;

bool Imobiliaria::acrescenta(Imovel *novo) {
    if(pesquisa(novo->getCodigo()) == nullptr) { //novo
        carteira.push_back(novo); //Agregação: a imobiliaria conhece
        return true;            //imoveis, não os possui
    }
    return false;
}

string Imobiliaria::getAsString() const {
    return std::string();
}

string Imobiliaria::listagemPorAndar(int andar) const {
    return std::string();
}

const Imovel *Imobiliaria::pesquisa(const string &codigo) const {
    const Imovel * resultado = nullptr;
    for(auto el: carteira)
        if(el->getCodigo() == codigo)
            return el;
    return nullptr;
}

void Imobiliaria::removePorCodigo(const string &codigo) {
    //Só um será removido no máximo
    for(int i=0; i < carteira.size(); i++)
        if(carteira.at(i)->getCodigo() == codigo){
            carteira.erase(carteira.begin()+i);
            break; //só dá para remover uma vez
        }
}

ostream &operator<<(ostream &o, const Imobiliaria &i) {
    o << i.getAsString();
    return o;
}
#ifndef IMOBILIARIA_H
#define IMOBILIARIA_H
#include <vector>
#include <string>
#include "imovel.h"

using namespace std;

class Imobiliaria {
    vector<Imovel *> carteira;
public:
    bool acrescenta(Imovel *novo);
    string getAsString()const;
    //Esta classe � robusta?
    string listagemPorAndar(int andar)const;
    const Imovel * pesquisa(const string & codigo)const;
    void removePorCodigo(const string & codigo);
};

ostream & operator<<(ostream &o, const Imobiliaria &i);

#endif

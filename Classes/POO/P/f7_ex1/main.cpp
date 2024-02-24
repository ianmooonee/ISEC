#include "imobiliaria.h"
#include "imovel.h"
#include "loja.h"
#include "apartamento.h"
#include <iostream>
#include <sstream>
#include <iterator>
int main() {
    //Imóveis no mercado
    Loja * a = new Loja(110);
    Apartamento * b=new Apartamento(200, 3, 4);
    Loja * c = new Loja(80);
    Imobiliaria era;
    era.acrescenta(a);
    era.acrescenta(b);
    era.acrescenta(c);
    cout << era;
    delete c;
    cin.get(); //Nesta alura: aparecer todos os 3 imóveis loja1, apartamento2, loja 3
    //destrutor de cada classe derivada, antes da sua base
    vector<Imovel *> mercado = { a, b, new Apartamento(50,0,1)};
    cout << a->getAsString() << b->getAsString() << "-------------\n";
    Imobiliaria remax;
    remax.acrescenta(mercado[0]);
    remax.acrescenta(mercado[1]);
    remax.acrescenta(mercado[2]);
    remax.acrescenta(a); //Repetido?
    cout << remax;//Loja1, apartamento2, apartamento4
    cout << remax.listagemPorAndar(0);//loja1 apartamento4
    remax.removePorCodigo("apartamento2");
    cout << remax;//loja1 apartamento4
    //Alguns imóveis estão a ser anunciados em mais do que uma imobiliária
    Imobiliaria chaveDourada;
    chaveDourada.acrescenta(mercado[0]);
    chaveDourada.acrescenta(mercado[2]);
    cout << chaveDourada; //loja1 apartamento4
    //Quem deve ser responsável por apagar os imóveis da memória?
    for (auto p : mercado)
        delete p; //destrutor de cada uma dos 3 objetos derivados antes
    // dos destrutores da base em cada caso
}
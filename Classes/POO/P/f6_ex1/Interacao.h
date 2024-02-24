//
// Created by ianmoone on 09/12/2021.
//

#ifndef INTERACCAO_H
#define INTERACCAO_H

#include <vector>
#include <string>
#include<memory>

using namespace std;

#include "Aquario.h"

class Interacao {
    Aquario * aquario;
    static const vector<string> opcoes;

public:
    Interacao(Aquario & aq);
    int lerInt(string msg);
    int escolheOpcao();
    void menu();
    void opcaoAcrescentarPeixe();
    void opcaoPesquisarPeixe();
    void opcaoEliminarPeixe();
    void opcaoAlimentarTodos();

};

#endif /* INTERACCAO_H */

//
// Created by ianmoone on 17/12/2021.
//

#ifndef PEIXE_HERANCA_INTERACAO_H
#define PEIXE_HERANCA_INTERACAO_H


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


#endif //PEIXE_HERANCA_INTERACAO_H

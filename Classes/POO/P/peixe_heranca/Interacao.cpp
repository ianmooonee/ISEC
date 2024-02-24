#include <iostream>
#include <sstream>
#include <limits>
#include <cmath>

using namespace std;

#include "Peixe.h"
#include "Interacao.h"
#include "Tubarao.h"

const vector<string> Interacao::opcoes{
        "Sair", //0
        "Listar", //1
        "Acrescentar peixe", //2
        "Pesquisar peixe", //3
        "Eliminar peixe", //4
        "Alimentar todos"};

Interacao::Interacao(Aquario & aq){
    aquario = &aq;
}

int Interacao::lerInt(string msg) {
    int valor;
    bool leu = false;
    do {
        cout << msg;
        string s;
        cin >> s;
        istringstream iss(s);
        if (iss >> valor) // se correu bem a leitura
            leu = true;
    } while (!leu);
    return valor;
}

int Interacao::escolheOpcao(){
    for ( unsigned int i = 0 ; i < opcoes.size() ; i++)
        cout << endl << i << " - " << opcoes[i];

    int opcao = -1;
    do {
        opcao = lerInt("\n\nopcao > ");
    } while (opcao < 0 || opcao > opcoes.size());
    return opcao;
}


void Interacao::menu(){



    int opcao = 0;
    do {
        opcao = escolheOpcao();
        switch (opcao){
            case 0: cout << "\nSair\n";
                break;
            case 1: cout << "\nListar \n" << aquario->getAsString() << endl;
                break;
            case 2: cout << "\nAcrescentar peixe\n";
                opcaoAcrescentarPeixe();
                break;
            case 3: cout << "\nPesqisar peixe\n";
                opcaoPesquisarPeixe();
                break;
            case 4: cout << "\nEliminar peixe\n";
                opcaoEliminarPeixe();
                break;
            case 5: cout << "\nAlimentar todos\n";
                opcaoAlimentarTodos();
                break;
        }

    }while (opcao != 0);
}


void Interacao::opcaoAcrescentarPeixe(){
    cout << "Tipo, digite comum, tubarao ou carpa: ";
    string tipo;
    cin >> tipo;
    string cor;
    cor = (rand() % 2 == 0) ? "rosa" : "azul";
    Peixe *novo;
    if(tipo == "tubarao")
        novo = new Tubarao;
    else if (tipo == "carpa")
        novo = new Carpa;
    else
        novo = new Peixe(tipo, cor);
    if (aquario->adiciona(novo)) {
        cout << "Acrescentou\n";
    }else {
        cout << "Nao acrescentou\n";
        delete novo;
    }
}

void Interacao::opcaoPesquisarPeixe( ){
    int numSerie = lerInt("Numero de serie:");
    cout << endl << aquario->getPeixe(numSerie);
}

void Interacao::opcaoEliminarPeixe( ){
    int numSerie = lerInt("Numero de serie:");
    if (aquario->remove(numSerie)) {
        cout << "Eliminou\n";
    }else {
        cout << "Nao eliminou\n";
    }
}

void Interacao::opcaoAlimentarTodos(){
    int quantidade = lerInt("Quantidade de alimento:");
    aquario->alimenta(quantidade);
    cout << "Aquario \n" << aquario->getAsString() << endl;
}
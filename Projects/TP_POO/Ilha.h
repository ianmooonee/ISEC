//
// Created by ianmoone on 16/11/2021.
//

#ifndef POO_TP_M1_ILHA_H
#define POO_TP_M1_ILHA_H


#include "Deserto.h"
#include "Pastagem.h"
#include "Floresta.h"
#include "Montanha.h"
#include "Pantano.h"
#include <ctime>

class Ilha {
    int nlinhas;
    int ncolunas;
    int total_zonas;
    int dinheiro;
    int dia=1;
    vector<Zona*> zonas;
public:
    Ilha(int nlinhas, int ncolunas);
    Ilha(const Ilha& il);
    string getAsString();
    bool contratarTrab(const string &tipotrab);
    bool constroiEdi(const string &tipo, const int &cordx, const int &cordy);
    bool constroiEdiDeb(const string &tipo, const int &cordx, const int &cordy);
    void avancaDia();
    void ligaEdi(const int &cordx, const int &cordy);
    void desligaEdi(const int &cordx, const int &cordy);
    bool moverTrabalhador(string &nomeTrab, int &cordx, int &cordy);
    string ZonaAsString(const int &cordx, const int &cordy);
    void setDinheiroDeb(int dinheiro);
    void delTrab(string &nomeTrab);
    void vendeEd(const int &cordx, const int &cordy);
    Ilha& operator=(const Ilha& il);

    ~Ilha();
};


#endif //POO_TP_M1_ILHA_H

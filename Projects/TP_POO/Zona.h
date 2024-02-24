//
// Created by ianmoone on 04/01/2022.
//

#ifndef POO_TP_M1_ZONA_H
#define POO_TP_M1_ZONA_H

#include "utils.h"
#include "Trabalhador.h"
#include "Mineiro.h"
#include "Operario.h"
#include "Lenhador.h"
#include "MinaFerro.h"
#include "MinaCarvao.h"
#include "CentralEletrica.h"
#include "Bateria.h"
#include "Fundicao.h"
#include "Serracao.h"

class Zona {
    string nome;
    int cordx;
    int cordy;
    vector<Trabalhador*> trabalhadores;
    vector<Edificio*> edificios;
public:
    Zona(int cordx, int cordy):cordx(cordx), cordy(cordy){};

    Zona(const Zona& z);

    const string &getNome() const;

    void setNome(const string &nome);

    int getCordx() const;

    int getCordy() const;

    bool addTrab(const string& str, int &dia, int &dinheiro);
    bool addEdificio(const string &tipo, int &dinheiro);

    virtual string getAsString() const;

    const vector<Trabalhador *> &getTrabalhadores() const;

    const vector<Edificio *> &getEdificios() const;

    int getPontosEdificios();

    Trabalhador* procuraTrab(const string &nomeTrab);

    void moverTrabalhadorZona(Trabalhador *novo);

    void removeTrabalhadorZona(const string &nome);
    void resetMovidos();
    bool addEdificioDeb(const string &tipo);

    void ligaEdiZona();

    void desligaEdiZona();

    void delTrab(const string &nome);

    bool vendeEdi();

    Zona& operator=(const Zona& z);

    virtual Zona* clone()=0;

    virtual ~Zona();
};


#endif //POO_TP_M1_ZONA_H

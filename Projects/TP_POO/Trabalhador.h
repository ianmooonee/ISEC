//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_TRABALHADOR_H
#define POO_TP_M1_TRABALHADOR_H
#include "utils.h"


class Trabalhador {
    string nome;
    //int producao;
    int dia_criacao;
    int movido=0;
    int custo;
    static int contador;
public:

    Trabalhador(int dia_criacao);

    virtual string getAsString() const;

    const string &getNome() const;

    void setNomeTrab(const string &nomee);

    int getDia_criacao() const;

    void setProducao(int producaoo);

    static int getContador();

    int getCusto() const;

    void setCusto(int custo);

    void setMovido(int movido);

    int getMovido() const;

    virtual Trabalhador* clone()=0;

    virtual ~Trabalhador()=default;
};


#endif //POO_TP_M1_TRABALHADOR_H

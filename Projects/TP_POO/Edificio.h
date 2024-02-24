//
// Created by ianmoone on 10/01/2022.
//

#ifndef POO_TP_M1_EDIFICIO_H
#define POO_TP_M1_EDIFICIO_H
#include "utils.h"

class Edificio {
    string nome;
    int producao;
    int custo;
    int estado=1;
public:

    Edificio(int producao);

    virtual string getAsString() const;

    const string &getNome() const;

    void setNome(const string &nome);

    int getProducao() const;

    void setProducao(int producao);

    int getCusto() const;

    void setCusto(int custo);

    int getEstado() const;

    void setEstado(int estado);

    virtual Edificio* clone()=0;

    virtual ~Edificio()=default;
};


#endif //POO_TP_M1_EDIFICIO_H

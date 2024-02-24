//
// Created by ianmoone on 17/12/2021.
//

#ifndef PEIXE_HERANCA_PEIXE_H
#define PEIXE_HERANCA_PEIXE_H


#include <string>

class Aquario;

class Peixe{
    std::string nome, cor;
    int peso, id;
    static int contador;
    Aquario * ondeEstou;
    int dias_dieta;
    bool vivo;
public:
    Peixe(const std::string & n, const std::string & c="cinzento", int peso=10);
    virtual void serAlimentado(int gramas);

    virtual std::string getAsString() const;
    Aquario * getOndeEstou();
    void setOndeEstou(Aquario *a);
    int getId()const;
    int getPeso() const;
    void setPeso(int p);
    bool isVivo() const;
    void setVivo(bool vivo);
    virtual Peixe *clone() const;

};


#endif //PEIXE_HERANCA_PEIXE_H

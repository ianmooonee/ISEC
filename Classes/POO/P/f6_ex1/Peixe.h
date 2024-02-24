//
// Created by ianmoone on 09/12/2021.
//

#ifndef F6_EX1_PEIXE_H
#define F6_EX1_PEIXE_H

#include <string>
using namespace std;

class Aquario;

class Peixe{
    string nome, cor;
    int peso, id;
    static int contador;
    Aquario * ondeEstou;
    int dias_dieta;
    bool vivo;
public:
    Peixe(const string & nome, const string & cor="cinzento");
    virtual void serAlimentado(int gramas);

    string getAsString() const;
    Aquario * getOndeEstou();
    void setOndeEstou(Aquario *a);
    int getId()const;
    int getPeso() const;
    void setPeso(int p);
    bool isVivo() const;
    void setVivo(bool vivo);
    int getDiasDieta() const;
    void setDiasDieta(int diasDieta);
    const string &getCor() const;
    void setCor(const string &cor);
    const string &getNome() const;
    void setNome(const string &nome);


};


#endif //F6_EX1_PEIXE_H

//
// Created by ianmoone on 09/12/2021.
//

#ifndef F6_EX1_AQUARIO_H
#define F6_EX1_AQUARIO_H


#include "Peixe.h"
#include <vector>
using namespace std;



class Aquario{
    std::vector<Peixe *> peixes;
public:
    Aquario(){};
    bool adiciona(Peixe * p);
    void alimenta(int gramas);//como consequ�ncia pode acontecer que o aqu�rio tenha menos peixes
    void removeMortos();
    const Peixe * getPeixe(int numserie);
    int procura(int id)const;
    bool remove(int id);
    string getAsString()const;



    //classe robusta
    ~Aquario();
    Aquario(const Aquario &outro);
    Aquario & operator=(const Aquario & outro);

    Peixe *DaPeixeAleatorio();

};


#endif //F6_EX1_AQUARIO_H

//
// Created by ianmoone on 17/12/2021.
//

#ifndef PEIXE_HERANCA_AQUARIO_H
#define PEIXE_HERANCA_AQUARIO_H


#include "Peixe.h"
#include <vector>



class Aquario{
    std::vector<Peixe *> peixes;
public:
    Aquario()=default;
    bool adiciona(Peixe * p);
    void alimenta(int gramas);//como consequ�ncia pode acontecer que o aqu�rio tenha menos peixes
    void removeMortos();
    Peixe * getPeixeAleatorio(); //Quando existir a piranha
    int procura(int id)const;
    bool remove(int id);
    std::string getAsString()const;



    //classe robusta??
    ~Aquario();
    Aquario(const Aquario &outro);
    Aquario & operator=(const Aquario & outro);

};


#endif //PEIXE_HERANCA_AQUARIO_H

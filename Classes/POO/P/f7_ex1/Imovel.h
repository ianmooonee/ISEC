#ifndef IMOVEL_H
#define IMOVEL_H

#include <string>

using namespace std;

class Imovel {
    float preco, area;
    int andar;
    string id; //apartamentoX lojaY
    static int contador;
public:
    Imovel(const string & palavra, float preco, float area, int andar);
    string getCodigo()const;
    float getPreco()const;
    int getAndar()const;
    virtual string getAsString() const; //Como redefinir?
    ~Imovel(); //why virtual?
};
ostream & operator<<(ostream & o, const Imovel & i);

#endif

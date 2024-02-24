#include "prego.h"
#include "aviso.h"

int main() {
    //Aviso a("Aviso numero 1"); //não é possível cirar um aviso sem prego
    Prego p(2,2);
    Aviso a(p, "Aviso letra a");
    Aviso b(p, "Aviso letra b");

    cout<<"Prego no ponto ("<<p.getX()<<","<<p.getY()<<")"<<endl;
    cout<<a.getAsString();
    cout<<b.getAsString();
    p.mudaDeSitio(3,3);
    cout<<"Prego no ponto ("<<p.getX()<<","<<p.getY()<<")"<<endl;
    cout<<a.getAsString();
    cout<<b.getAsString();

    return 0;
}

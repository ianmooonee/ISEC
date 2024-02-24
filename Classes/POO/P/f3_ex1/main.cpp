#include "ponto.h"

int main() {
    //Ponto p; //para funcionar tem de existir valores por omissao no construtor
    Ponto p1(1,2), p2(3,4), p3(5,7),p4;

    //p1.setCordX(8); //funciona
    //p2.setCordY(9); //funciona

    //cout<<p1.getCordX()<<endl; //funciona
    //cout<<p2.getCordX()<<endl; //funciona

    cout<<p1.getAsString()<<endl;
    cout<<p2.getAsString()<<endl;
    cout<<p3.getAsString()<<endl;
    cout<<p4.getAsString()<<endl;

    cout<<dist(p1,p2)<<endl;

    cout<<p2.dist(p1)<<endl;
    cout<<p1.dist(p2)<<endl;

    return 0;
}

#include "ponto.h"
#include "retangulo.h"
#include "desenho.h"

int main() {
    Ponto a(1,2), b(3,4);
    /*const Ponto c(4,4);
    Ponto pontos[3]={Ponto(1,3), Ponto(2,4), Ponto(5,7)};
    Ponto d;

    a.setCordX(0);
    a.setCordY(0);

    cout<<"B: "<<b.getAsString()<<endl;//(3,4)
    cout<<"D: "<<d.getAsString()<<endl;//(0,0)
    for(auto elem:pontos){
        cout<<elem.getAsString()<<endl;
    }*/

    //c.getCordX(0); //dá erro porque a função não é protegida e este ponto só permite a chamada de funções protegidas

    //cout<<"C cord x: "<<c.getCordX()<<endl;
    //cout<<"A dist B: "<<a.dist(b)<<endl;
    //cout<<"A dist c: "<<a.dist(c)<<endl; //tirei a referência da função dist (&p1) porque isso anularia o const e dá erro ao ser chamado no para o ponto c

    Retangulo r1(a, 5, 7); //a, altura, largura
    Retangulo r2(a, 4, 3); //a, altura, largura
    Retangulo r3(0, 0, 3, 2); //ponto x, ponto y, altura, largura
    const Retangulo r4(1, 1, 2, 2);

    cout<<r1.getAsString()<<endl;
    //cout<<"r1.getAlt(): "<<r1.getAlt()<<endl;
    //cout<<r4.getAsString()<<endl;
    //cout<<"Area r1: "<<r1.area()<<endl;
    //r1.setAlt(2);
    //cout<<"Nova area r1: "<<r1.area()<<endl;
    //r1.setCanto(Ponto(4,5));
    //cout<<"Novo canto r1: "<<r1.getAsString()<<endl;

    Desenho d1("Desenho vazio");
    d1.acrescentav2(r1);
    d1.setNome("Obra-prima");
    d1.acrescentav2(r2);
    d1.acrescentav2(r3);
    d1.acrescentav2(r4);
    cout<<d1.getAsString()<<endl;
    vector<Retangulo> rs=d1.getRetangulosCoincidentes();
    for(auto el:rs) {
        cout << "Retangulos com o mesmo CSE: "
             << el.getAsString(); //deverá mostrar 2 retângulos coincidentes no ponto d1
    }
    cout<<"\nArea total: "<<d1.somaArea()<<endl;

    return 0;
}

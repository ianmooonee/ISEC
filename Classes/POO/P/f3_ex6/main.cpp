#include <iostream>
#include "pessoa.h"
#include "clube.h"

int main() {
    Pessoa p1("Andre", 123, 456), p2("Joao", 678, 124), p3("Mane", 3532, 523523), p4("Zeca", 126723, 6232346);
    Clube c1("Leitura", "Ler"), c2("Gaming", "FPS");

    c1.acrescentaPessoa(p1);
    c1.acrescentaPessoa(p4);
    cout<<c1.getAsString()<<endl;
    c2.acrescentaPessoa(p2);
    cout<<c2.getAsString()<<endl;

    /*if(c1.verificaSocio(p1)){
        cout<<p1.getNome()<<" e socio do clube de "<<c1.getAsString()<<endl;
    }
    else{
        cout<<p1.getNome()<<" nao e socio do clube de "<<c1.getAsString()<<endl;
    }

    if(c1.verificaSocio(p2)){
        cout<<p2.getNome()<<" e socio do clube de "<<c1.getAsString()<<endl;
    }
    else{
        cout<<p2.getNome()<<" nao e socio do clube de "<<c1.getAsString()<<endl;
    }*/

    c1.removerSocio(p1);
    c1.acrescentaPessoa(p3);
    p3.setNome("asdasd");
    cout<<c1.getAsString()<<endl;

    return 0;
}

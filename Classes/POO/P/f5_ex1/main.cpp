#include "Msg.h"

void func(Msg **m3, Msg **m4){
    cout << "Dentro da funcao:antes do new\n";
    *m3 = new Msg("C");
    *m4= new Msg("D");
    cout << "Dentro da funcao:depois do new\n";
}

int main() {
    //a) ao inicio não aparece adeus porque não é invocado o destrutor;
    auto *m1= new Msg("A");
    Msg *m2= new Msg("B");

    //c)
    Msg *m3, *m4;
    func(&m3, &m4);

    //d)
    const Msg *array= new Msg[3];


    //b)
    delete m1; //invocação do destrutor
    delete m2; //invocação do destrutor
    delete m3;
    delete m4;
    //e)
    delete []array;

    return 0;
}
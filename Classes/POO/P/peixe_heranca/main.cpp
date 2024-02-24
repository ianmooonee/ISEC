
#include<string>
#include<iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

#include "Aquario.h"
#include "Interacao.h"

int main() {
    srand(static_cast<unsigned>(time(NULL));
    Aquario a;

    Interacao interacao(a);
    interacao.menu();
    //Copiar aquário: É seguro? o novo fica com peixes diversificados? 
    Aquario novo=a;
    return 0;
}
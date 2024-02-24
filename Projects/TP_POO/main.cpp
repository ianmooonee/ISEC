#include <iostream>
#include "utils.h"
#include "Ilha.h"
#include "Controlador.h"

int main() {
    int linhas=0, colunas=0;

    do {
        cout << "Inserir numero de linhas:";
        cin >> linhas;
        if(linhas<3 || linhas>8)
            cout<<"Valor invalido!"<<endl;
    }while(linhas<3 || linhas>8);

    do{
        cout << "Inserir numero de colunas:";
        cin >> colunas;
        if(colunas<3 || colunas>16)
            cout<<"Valor invalido!"<<endl;
    }while(colunas<3 || colunas>16);

    cin.ignore(numeric_limits<streamsize>::max(),'\n');
    Ilha i(linhas, colunas);
    Controlador c(i);
    return 0;
}

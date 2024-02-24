#include <iostream>
#include <string>
#include <sstream>
#include "Aluno.h"
using namespace std;

void teste(Aluno &a) { //se tirarmos a & estamos a copiar o objeto b nesta função, tornando-se lento
    cout << "Dentro da função" << endl;
}

int main() {
    Aluno b;
    Aluno a("Jose");
    //Aluno alunos[5]={"joao"}; //versão que não funciona
    Aluno alunos[5]={{"joao"}}; //versão que funciona

    cout<<a.getAsString()<<endl;

    for(int i=0;i<5;i++){
        cout<<alunos[i].getAsString()<<endl;
    }

    return 0;
}

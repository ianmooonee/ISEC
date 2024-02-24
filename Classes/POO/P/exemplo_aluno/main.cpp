#include <iostream>
#include <string>
#include "aluno.h"
#include "aluno.h" //não deve ser um problema, pois o .h está protegido contra dupla inclusão
using namespace std;



int main() {
    cout << Aluno::getContador(); //mostra 0 objetos criados até ao momento
    Aluno b;
    string nome="joao";
    Aluno a("Jose");
    Aluno alunos[5]={nome};
    const Aluno alunoconst("Aluno Constante");

    cout << Aluno::getContador(); //mostra 7 objetos criados até ao momento
    cout << b.getContador(); //a função além de pertencer à classe por ser estática também
                            //é possível de chamar por um objeto

    cout<<a.getAsString()<<endl;

    for(auto el: alunos){ //também é possível o range-based loop em arrays de tamanho fixo
        cout<<el.getAsString()<<endl;
    }

    Aluno c("Ana", {false, true, false}); //devido ao initializer list
    cout << "Logo no início ===" << c.getAsString();
    c.eliminaPresencaoNoDadoDia(1);
    cout << "Depois do eliminaPresenca ===" << c.getAsString(); //Aluno fica com tres faltas
    cin.get();
    c.eliminaFaltas(); //fica sem qualquer registo
    cout << "Depois do eliminaFaltas ===" << c.getAsString();
    cin.get();


    return 0;
}


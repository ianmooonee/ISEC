#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include "aluno.h"


using namespace std;
int main() {
    int aux;
    vector<int> valores;
    ifstream ficheiro_input("../valores.txt");
    string frase;
    //Testar no windows se o caminho relativo precisa
    //da contra-barra "\\"
    if(ficheiro_input){
        //while(ficheiro_input >> aux) //Porque não
        //Ler uma linha de
        //cada vez e tirar
        //desta os inteiros
        //que conseguir
        while(getline(ficheiro_input, frase)) {
            //Tirar todos inteiros de uma string maior
            istringstream buffer(frase);
            while(buffer >> aux)
                valores.push_back(aux);
        }
        cout << "Li do ficheiro:";
        for(auto el: valores)
            cout << el << " ";
        cout << endl;
    }
    cin.get();
    //Acrescentar novos valores
    valores.push_back(3);
    valores.push_back(4);

    //Gravar para um ficheiro novo
    ofstream ficheiro_output("saida.txt");
    if(ficheiro_output){
        ficheiro_output << "Valores gravados:\n";
        for(auto el: valores)
            ficheiro_output << el << " ";

    }

    //destrutor das classes ifstream e ofstream irão fechar os ficheiros
    //aberto

    //Ler um conjunto de alunos de um ficheiro texto
    vector<Aluno> alunos;
    string nome;
    unsigned long nif;
    Exame poo("Exame Normal", 02, 02, 2021);
    ifstream ficheiro_alunos("alunos.txt");
    while(getline(ficheiro_alunos, frase)) {
        //Tirar todos inteiros de uma string maior
        istringstream buffer(frase);
        if(buffer >> nif &&
           getline(buffer, nome) && nome.length() > 0) { //Ler nome completo
            //até ao fim da linha
            //quantos houver
            Aluno a(&poo, nome, nif);
            alunos.push_back(a);
        }
    }
    for(auto el: alunos) //Ver os alunos lidos
        cout << el.getAsString() << endl;
    return 0;
}
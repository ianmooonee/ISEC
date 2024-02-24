//
// Created by Ana Oliveira Alves on 29/10/2021.
//
#include "aluno.h"
#include <iostream>
#include <sstream>
using namespace std;

int Aluno::contador=0;
Aluno::Aluno( Exame * e, const string & nome,
              initializer_list<bool> pre):matricula(04,11,2021),
                                          exame_poo(*e){
    int i = 0;
    fill(presencas, presencas + N, 0); //preenche este intervalo de memória contígua com um dado valor
    for(auto el: pre) {
        if (i < N)
            presencas[i++] = el;

        presencas_vector.push_back(el); //no vector há sempre espaço para mais um, enquanto houver memória
    }

    this->nome=nome;

    this->naluno=contador++;
    nif = 999999999;
    cout<<"A construir aluno..."<<endl;
}

Aluno::~Aluno(){ //destrutor, o this é opcional
    //cout<<"Destruindo aluno "<<this->getAsString()<<endl;
}

int Aluno::getContador(){
    return contador; //apenas retorna o valor do contador, ou seja, a quantidade de objetos criados
}

string Aluno::getAsString(){
    ostringstream buffer;
    buffer<<"Nome: "<<nome<<" Numero de aluno: "<<naluno<<endl;
    //Presencas
    /*buffer << "Array:{";
    for(bool el: presencas)
        buffer << boolalpha << el << " ";*/
    buffer << " Vector Presenças:{";
    for(bool el: presencas_vector)
        buffer << boolalpha << el << " ";
    buffer << "} Matricula:" << matricula.getAsString();
    buffer << " Dia do exame:" << exame_poo.getAsString(); //quando
    //for referência, cuidado ao chamar função por esta
    buffer << " NIF:" << nif;
    return buffer.str();
}

bool Aluno::eliminaPresencaoNoDadoDia(int dia) { //Porque afinal faltou
    if(dia < N && dia < presencas_vector.size()){
        presencas[dia] = false;
        presencas_vector.at(dia) = false;
        return true;
    }

    return false;
}

void Aluno::eliminaFaltas() {
    //só faz sentido no vector, eliminar todas as presencas que sejam false
    //porque no array o espaço fica na mesma reservado, o que se punha lá no lugar?
    //que veio afinal?
    auto ptr=presencas_vector.begin();
    while(ptr != presencas_vector.end())
        if(*ptr == false)
            ptr = presencas_vector.erase(ptr); //elimina do vector, e no retorno fica a apontar
            //para a próxima posição a seguir ao eliminado
        else
            ptr++; //só se não se moveu o vector de lugar na memória dinâmica, é que isto é seguro

}

Aluno::Aluno(std::string n, Exame *e):matricula(5,11,2021),
                                      exame_poo(*e)
{
    this->nome = n;
    //this->exame_poo = e;
    nif = 999999999;
    cout << "A construir Aluno\n";
}

Aluno::Aluno(Exame *e, const string &nome, unsigned long nif): exame_poo(*e), matricula(11,11,2021){
    this-> nome = nome;
    this->nif = nif;
}

unsigned long Aluno::getNif() const {
    return nif;
}

const string &Aluno::getNome() const {
    return nome;
}


void teste(Aluno &a) { //se tirarmos a & estamos a copiar o objeto b nesta função, tornando-se lento
    cout << "Dentro da função" << endl;
}
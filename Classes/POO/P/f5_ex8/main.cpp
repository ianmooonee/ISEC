//
// Created by Ana Oliveira Alves on 02/12/2021.
//
#include <string>

using namespace std;



class Contacto {
    string nome;
    unsigned int tel;                           // Mas pode nem ser sequer preciso
public:                                        // -> Ver primeiro as perguntas.
    Contacto(string n, unsigned int t) : nome(n), tel(t) {}
    string getNome() const { return nome; }
    int getTel() const { return tel; }
    void setNome(string s) { string nome = s; }
    void setTel(unsigned int t) { tel = t; }
};

class Agenda {
    Contacto *lista[30];  // os elementos não usados ficarão a NULL
public:
    Agenda() {
        for (unsigned int i = 0; i < 30; i++)
            lista[i] = NULL;
    }

    void adicionaContacto(string nome, unsigned int tel, int pos) {
        // os algoritmos da agenda foram simplificados para manter o foco nos
        // aspectos relevantes do exercício.
        if (lista[pos] == NULL)   // se a entrada na lista está vazia prossegue
            lista[pos] = new Contacto(nome, tel); // reparar no new

    }

    ~Agenda(){
        for (unsigned int i = 0; i < 30; i++)
            delete lista[i];
    }

    Agenda &operator=(const Agenda &outro){
        if(this!=&outro) {
            //libertar o que tinha antes
            for (unsigned int i = 0; i < 30; i++)
                delete lista[i];

            //reservar nova memoria dinamica

            //copiar os atributos do outro
            for (unsigned int i = 0; i < 30; i++)
                lista[i] = new Contacto(outro.lista[i]->getNome(), outro.lista[i]->getTel());
        }

        return *this;
    }

// funções para procurar, remover, actualizar, listar. Pode faze-las como TPC

};

int main(){
    Contacto novo("Ana", 123123123);
    Contacto copia(novo); //funciona porque não há memória dinâmica

    Agenda a;
    a.adicionaContacto("Ana", 123456789, 0);

    Agenda b(a); //erro de execução - falta construtor por copia

    return 0;
}
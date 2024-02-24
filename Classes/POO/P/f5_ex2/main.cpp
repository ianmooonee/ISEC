//
// Created by Ana Oliveira Alves on 02/12/2021.
//
#include <cstring> //equivalente a string.h
#include <iostream>

using namespace std;
class ABC { //equivalente a classe string, guarda array de caracteres
    char * p;
public:
    ABC(const char * s) {
        p = new char[strlen(s)+1];
        strcpy(p,s);
        cout << "A construir mensagem" << p << endl;
    }
    //b) Falta libertar memória
    ~ABC(){
        cout << "A destruir mensagem " << p << endl;
        delete [] p;
    }
    const char * getConteudo() const {
        return p;
    }
    //d) Construtor por cópia, para poder passar objetos
    //por valor (e não apenas por referência)
    ABC(const ABC & outro){
        //Copiar tudo o que o outro tem para this
        //no caso da memória reservada, reservar uma nova
        //para este objeto
        //p = new char[strlen(outro.p)+1];
        //strcpy(p,outro.p);

        //Invocar operador atribuição entre this e outro
        p= nullptr;
        *this=outro;

        cout << "A construir mensagem por copia " << p << endl;
    }

    ABC &operator=(const ABC &outro){
        if(this!=&outro) { //este if é para prever o y=y, para não apagar algo que vamos utilizar

            //libertar o que tinha antes
            delete[]p; //corre o risco de dar delete de p sem ele ter nada, por isso tem de ser inicializado na linha antes da invocação do operador no construtor

            //reservar nova memoria dinamica
            p = new char[strlen(outro.p) + 1];

            //copiar os atributos do outro
            strcpy(p, outro.p);
        }

        return *this;
    }
};


void func(ABC x) {
    // tanto faz
}

void func() {
    ABC a("aaa");
    ABC b("bbb");
    a = b;
}

void gastaMem() {
    ABC temp("Texto temporário que ocupa espaço");
    // Etc. Tanto faz.
}

int main() {
    /*for (unsigned int i=0; i<100000000; i++) {
        cout << i;
        gastaMem();
    }*/

    ABC y("ola");
    func(y); //Está a passar uma cópia do objeto
    cout << "Depois da funcao"<< endl;
    ABC copia=y; //equivalente a ABC copia(y)
    cout << "o programa ja deve terminado por falta de memoria "<< endl;
    //atribuição e´diferente de construtor por cópia
    y=copia;
    //copia=y;
    //y=copia;
    y=y;

    return 0;
}
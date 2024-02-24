//
// Created by Ana Oliveira Alves on 02/12/2021.
//


class Pessoa {
    // tanto faz o que aqui está
};
class Clube {
    Pessoa * * socios; //array de ponteiros
    int tam;
public:
    Clube(int t) {
        // Isto é para fazer copy & paste
        // para o programa no computador.
        // Mas pode nem ser sequer preciso
        // -> Ver primeiro as perguntas.
// por questões de espaço assume-se
// que há memória e não dá erro, mas
// isto deve ser devidamente validado
//    (  if (socios != NULL) ...  )
        tam = t;
        socios = new Pessoa * [tam];
        for (unsigned int i=0; i<tam; i++)
            socios[i]= nullptr;
    }
    ~Clube() { delete [] socios; }
    void setMembroDoClube(Pessoa * p, int pos) {
        socios[pos] = p;
    }

    Clube &operator=(const Clube &outro){
        if(this!=&outro){
            //libertar o que tinha antes
            delete[]socios; //corre o risco de dar delete de p sem ele ter nada, por isso tem de ser inicializado na linha antes da invocação do operador no construtor

            //reservar nova memoria dinamica
            socios = new Pessoa *[outro.tam];

            //copiar os atributos do outro
            tam=outro.tam;
            for(int i=0; i<tam; i++){
                socios[i]=outro.socios[i];
            }
        }

        return *this;
    }

    Clube(const Clube &outro){
        socios= nullptr;
        *this=outro;
    }
};
// Notar que o obj. Pessoa é visto pelo Clube // mas o clube não toma posse desse objecto // (o clube é uma agregação de Pessoas)
// continua na próxima página
int main() {
    Pessoa a, b;  // de acordo com o que estiver na classe Pessoa
    Clube c(50);
    c.setMembroDoClube(&a, 0);  // Pessoa a passa a pertencer ser membro do clube
    c.setMembroDoClube(&b, 1);  // Idem Pessoa b
    Clube d(20);
    d.setMembroDoClube(&a, 0);  // Pessoa a passa a pertencer ser membro do clube
    d.setMembroDoClube(&b, 1);  // Idem Pessoa b

    Clube novo(c); //ocorre erro de execução - criar construtor por copia
    d=c; //ocorre erro de execução - criar operador atribuição


    return 0;
}
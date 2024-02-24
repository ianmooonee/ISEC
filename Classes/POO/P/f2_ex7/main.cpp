#include <iostream>
#include <string>
#include <sstream>
using namespace std;
//ver alinea h a o

static int contador=1;

class MSG{
    char letra;
    int num;
    //MSG(const MSG  &  z){}//não é acessivel pela main

public:
    MSG(const MSG &z){
        cout<<"Construido por copia."<<endl;
        this->letra=z.letra;
        this->num=-z.num;
    }

    MSG(char c='x'){
        this->letra=c;
        this->num=contador++;
        //this->getAsString();
        cout<<"A construir msg..."<<this->getAsString()<<endl;
    }

    ~MSG(){
        cout<<"Destruindo "<<this->letra<<" num "<<this->num<<endl;
    }

    string getAsString(){
        ostringstream buffer;
        buffer<<"Letra: "<<letra<<" Numero: "<<num;
        return buffer.str();
    }
};

void teste(){
    MSG aux('y');
}

void teste2(MSG obj){
    MSG aux('y');
}

void teste3(MSG &obj){
    MSG aux('y');
}

MSG teste4(){
    MSG aux('y');
    return aux;
}

MSG &teste5(){
    static MSG aux('y');
    return aux;
}

int main() {
    //a) primeiro é criado a e depois b, enquanto que a destruição começa no b e acaba do a
    MSG a('a'), b; //a{'a'} também funciona

    //b) não existe nem construção nem destrução do objeto c, uma vez que não existe construção quando se trata deste tipo de atribuição, apenas uma referênciação para b
    MSG &c=b;

    //c)não existe a utilização do construtor mas d é "construído por cópia", logo tem de ser destruído no final da execução
    MSG d=b;

    //d) há uma atribuiçao de b a a, a assume os valores de b e posteriormente são ambos destruidos
    //a=b;

    //e) é possivel atribuir letras aos objetos criados no array. São criados a e b e depois os membros do array, sendo que o array é o primeiro a ser destruido, seguido de d, b e a
    MSG array[2]={{'f'},{'g'}};

    //f) se retirarmos a inicialização, então deixa de existir um costrutor compatível com a declaração da variavel quando a inicializamos com um valor

    //g) aux é criado em último e é o primeiro a ser destruído. o resto não se altera
    //teste();

    //h)?
    //teste2(b);

    //i) a construção não é efetuada porque este construtor está a tentar ser utilizado pela atribuição, no entanto é privado

    //j) porque já pode utilizar o construtor na atribuição uma vez que ele é público

    //k) não existia qualquer inicialização no construtor por cópia. ao existir os valores já saem corretos

    //l) não existe construção nesta chamada mas sim uma alteração de um dos objetos
    //teste3(b);

    //m)?
    //teste4();

    //n) o objeto construido nesta função, apesar de ser o último a ser construido é também o último a ser apagado
    teste5();


    //cout<<a.getAsString()<<endl;
    //cout<<b.getAsString()<<endl;

    return 0;
}

/*A construir msg...Letra: a Numero: 1
A construir msg...Letra: x Numero: 2
Construido por copia.
A construir msg...Letra: f Numero: 3
A construir msg...Letra: g Numero: 4
A construir msg...Letra: y Numero: 5
Destruindo y num 5
Destruindo g num 4
Destruindo f num 3
Destruindo x num -2
Destruindo x num 2
Destruindo a num 1*/

#include "Fracao.h"

void func(string n) {
    cout << n;
}

int main() {
    Fracao a(1,2), b(3), a1(1,2), b1(2,3), c1(3,4); //a->1/2    b->3/1
    const Fracao c(3,4); //nunca dá para alterar, só ver o conteúdo    c->3/4

    //a)
    //cout<<a.getNumerador()<<endl;
    //c.setDenominador(); //dá erro, c não pode ser alterado
    a.setNumerador(2);
    //cout<<a.getNumerador()<<endl;

    //b)
    a=b*c;
    //cout<<a.getAsString()<<endl;

    //c)
    Fracao resultado;
    resultado=a*b*c; //(a*b)*c -> (9/4 * 3/1) * 3/4
    //cout<<resultado.getAsString()<<endl;

    //d)
    a=b*4; //chama o construtor se o operador não for explicit criando um objeto temporario
    //cout<<a.getAsString()<<endl;

    //f)
    a=4*b; //o objeto temporario não pode ser usado para invocar funções, só para ser parâmetro
            // com a funcao global já funciona

    //g)
    //cout<<a<<b<<c<<endl;

    //j)
    a*=b;
    //cout<<a<<b;

    //k)
    (a1 *= b1) *= c1; //só dá erro na primeira conta porque ele faz as atribuições da direita para a esquerda
                     //como a função é void ele não consegue fazer a atrubuição do b ao a
    //cout<<a1;

    //cout<<++b1; //o complicador procura pelo operator++(c1) ou c1.operator++ (função global ou membro)
    //cout<<b1; //usar referência porque senão alteramos uma cópia em vez do próprio b1
    //++4; //não funciona

    cout<<c1++; //tenta chamar operator(c1,int) ou c1.operator++(int)
    cout<<c1; //mostra o valor após o incremento

    //o)
    if(a==b){
        //cout<<"Sao iguais!"<<endl;
    }
    else if(a!=b){
        //cout<<"Sao diferentes!"<<endl;
    }

    //p) ler uma fracao da consola
    cout<<"Nova fracao (dois inteiros): ";
    cin>>a;
    cout<<a;

    func(a); //não converte fracao em int nem string
             //operador não tem tipo de retorno

    return 0;
}

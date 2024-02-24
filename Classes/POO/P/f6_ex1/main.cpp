#include<string>
#include<iostream>
using namespace std;

#include "Aquario.h"
#include "Interacao.h"

int main() {
    Aquario a;

    Interacao interacao(a);
    interacao.menu();

    return 0;
}

/*
 * Aquario tem peixes (composição)
 * Peixe conhece aquario (agregação)
 *
 * Nomes:
 * Peixes: nome, cor, peso, numero de serie, *onde_estou (aquario), estado(vivo ou morto), nDiasDieta
 * Aquário: vector de ponteiro para peixe
 *
 * Métodos:
 * Peixe:
 *      peixe ser alimentado - void serAlimentado(unsigned int quantidade_racao)
 * Aquario:
 *      aquario insere peixo novo - bool inserir(Peixe *p)
 *      verificar se um peixe está no aquario pelo numero de serie - bool procuraPeixe(int numero)
 *      alimentar todos os peixes com uma determinada quantidade de comer - void alimentarTodos(unsigned int qt)
 *      eliminar peixes mortos - int removeMortos()
 *
*/
#include <stdio.h>
#include <stdlib.h>

typedef struct tipoA cliente, *pCliente;
typedef struct tipoB acesso, *pAcesso;

typedef struct {
    int h, m;
} hora;

struct tipoA {
    int id;         // Identificador único
    int contador;   // Número de utilizações nesse dia
    pAcesso lista;  // Ponteiro para a lista de acessos
    pCliente prox;  // Ponteiro para o próximo cliente
};

struct tipoB {
    hora in, out;   // Horas de entrada e saída
    pAcesso prox;
};

void libertaTudo(pCliente p) {
    pCliente auxC;
    pAcesso auxA;

    while (p != NULL) {
        while (p->lista != NULL) {
            auxA = p->lista;
            p->lista = p->lista->prox;
            free(auxA);
        }
        auxC = p;
        p = p->prox;
        free(auxC);
    }
}

pCliente criaExemploED() {
    int totC = 3, i, j, k = -1;
    cliente a[] = {{13, 2, NULL, NULL},
                   {17, 1, NULL, NULL},
                   {22, 3, NULL, NULL}};
    acesso b[] = {{{10, 20}, {11, 52}, NULL},
                  {{14, 30}, {17, 2},  NULL},
                  {{10, 50}, {-1, -1}, NULL},
                  {{9,  11}, {9,  12}, NULL},
                  {{10, 5},  {12, 0},  NULL},
                  {{14, 33}, {-1, -1}, NULL}};

    pCliente lista = NULL, novoC;
    pAcesso novoA;

    for (i = 0; i < totC; i++) {
        k += a[i].contador;
    }
    for (i = totC - 1; i >= 0; i--) {
        novoC = malloc(sizeof(cliente));
        if (novoC == NULL) {
            libertaTudo(lista);
            return NULL;
        }
        *novoC = a[i];
        novoC->prox = lista;
        lista = novoC;
        for (j = 0; j < novoC->contador; j++) {
            novoA = malloc(sizeof(acesso));
            if (novoA == NULL) {
                libertaTudo(lista);
                return NULL;
            }
            *novoA = b[k--];
            novoA->prox = novoC->lista;
            novoC->lista = novoA;
        }
    }
    return lista;
}

void mostraTudo(pCliente p) {
    pAcesso auxA;

    while (p != NULL) {
        printf("\nUtilizador com id %d efetuou %d acessos\n", p->id, p->contador);
        auxA = p->lista;
        while (auxA != NULL) {
            printf("Entrou as %2.2d:%2.2d. ", auxA->in.h, auxA->in.m);
            if (auxA->out.h == -1)
                printf("Ainda nao saiu do parque\n");
            else
                printf("Saiu as %2.2d:%2.2d\n", auxA->out.h, auxA->out.m);
            auxA = auxA->prox;
        }
        p = p->prox;
    }
}

int totClientes(pCliente lista) {
    int conta = 0;

    while (lista != NULL) {
        conta++;
        lista = lista->prox;
    }


    return conta;
}

int dentroParque(pCliente lista) {
    int conta = 0;
    pAcesso auxA;

    while (lista != NULL) {
        auxA = lista->lista;
        while (auxA != NULL) {
            if (auxA->out.h == -1)
                conta++;
            auxA = auxA->prox;
        }
        lista = lista->prox;
    }

    return conta;
}

int clienteMaisTempo(pCliente lista) {
    int id = -1, max = 0, tempo;
    pAcesso auxA;

    while (lista != NULL) {
        auxA = lista->lista;
        tempo = 0;
        while (auxA != NULL) {
            if (auxA->out.h == -1) {
                break;
            } else {
                tempo += (auxA->out.h * 60 + auxA->out.m) - (auxA->in.h * 60 + auxA->in.m);
            }
            auxA = auxA->prox;
        }
        if (tempo > max) {
            max = tempo;
            id = lista->id;
        }
        lista = lista->prox;
    }

    return id;
}

void retiraUtilizacoesAbertas(pCliente lista) { //falta eliminar clientes com o contador a 0
    pAcesso atual, anterior;

    while (lista != NULL) {
        atual = lista->lista;
        anterior=NULL;

        while (atual->prox != NULL) {
            anterior=atual;
            atual = atual->prox;
        }

        if(atual->out.h==-1) {
            if (anterior == NULL) {
                lista->lista = NULL;
                free(atual);
                lista->contador--;
            } else {
                anterior->prox = NULL;
                free(atual);
                lista->contador--;
            }
        }

        lista = lista->prox;
    }
}

//d) sair / entrar: 1 vez, outra vez
pCliente acessoParque(pCliente lista, int x, hora a){ //falta a entrada de novos clientes
    pCliente c1=lista;
    pAcesso a1, a2;

    while(c1!=NULL && c1->id!=x){
        c1=c1->prox;
    }

    if(c1==NULL){
        return lista;
    }

    a1=c1->lista;

    while(a1->prox!=NULL){
        a1=a1->prox;
    }

    if(a1->out.h==-1){ //atualiza hora de saida
        a1->out=a;
    }
    else{
        a2=malloc(sizeof(acesso));
        if(a2==NULL){
            return lista;
        }

        a2->prox=NULL;
        a2->in=a;
        a2->out.h=a2->out.m=-1;
        a1->prox=a2;

        c1->contador++;
    }

    return lista;
}

int main() {

    pCliente lista = criaExemploED();

    printf("\nTotal de clientes que acederam ao parque: %d\n", totClientes(lista));

    printf("Clientes que se encontram dentro do parque: %d\n", dentroParque(lista));

    printf("Cliente que passou mais tempo dentro do parque: %d\n", clienteMaisTempo(lista));

    retiraUtilizacoesAbertas(lista);

    /*lista= acessoParque(lista, 13, (hora){14,45});
    lista= acessoParque(lista, 13, (hora){18,00});
    lista= acessoParque(lista, 17, (hora){17,45});
    lista= acessoParque(lista, 22, (hora){17,45});
    lista= acessoParque(lista, 22, (hora){18,45});*/

    mostraTudo(lista);

    libertaTudo(lista);
    return 0;
}
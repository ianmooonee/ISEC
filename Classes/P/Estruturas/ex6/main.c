#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int num;
    char nome[100];
} Contacto;

void printContacto(Contacto c){
    printf("%s - %d", c.nome, c.num);
}

int initCt(Contacto *cont, Contacto *lista, int total){
    printf("Insira o nome: ");
    scanf(" %s", cont->nome);

    for(int i=0; i<total; i++){
        if(strcmp(lista[i].nome, cont->nome)==0){
            return 0;
        }
    }

    printf("Insira o numero: ");
    scanf(" %d", &cont->num);

    return 1;

}

void printArray(Contacto *lista, int total){
    if(total>0){
        for( int i = 0; i<total; i++){
        printContacto(lista[i]);
        putchar('\n');
        }
    }
}

Contacto* adicionarContacto(Contacto *lista, int *total){
    Contacto *aux;
    int flag;

    aux=realloc(lista, sizeof(Contacto)*(*total+1));
    if(aux==NULL){
        fprintf(stderr, "Erro ao realocar o array");
        return lista;
    }
    lista=aux;

    do {
        flag=initCt(lista + *total, lista, *total);
    }while(flag==0);

    (*total)++;

    return lista;
}

Contacto* eliminarContacto(Contacto *lista, int *total, char *nome){
    Contacto *aux, backup;
    int i;

    for(i=0; i<*total; i++){
        if(strcmp(lista[i].nome, nome)==0){
            break;
        }
    }

    if(i==*total){
        return lista;
    }

    backup=lista[i];
    lista[i]=lista[*total-1];

    aux=realloc(lista, sizeof(Contacto)*(*total-1));
    if(aux==NULL){
        fprintf(stderr, "Erro ao realocar o array");
        lista[i]=backup;
        return lista;
    }

    lista=aux;
    (*total)--;

    return lista;
}

int pesquisaContacto(Contacto *lista, int total, char *nome){
    for(int i=0; i<total; i++){
        if(strcmp(lista[i].nome, nome)==0){
            return lista[i].num;
        }
    }

    return 0;
}

void atualizaContacto(Contacto *lista, int total, char *nome, int novo){
    for(int i=0; i<total; i++){
        if(strcmp(lista[i].nome, nome)==0){
            lista[i].num=novo;
        }
    }
}

//para ordenar é começar um ciclo for pelo fim e comparar com o novo a inserir. É preciso realocar antes.
//para eliminar ordenado é copiar todos os que estão à frente e depois fazemos o realloc.

int main(){
    int total=0, flagContacto, novo;
    char nome[100];
    Contacto *lista=NULL;

    lista=adicionarContacto(lista, &total);
    lista=adicionarContacto(lista, &total);

    printf("Insira o nome a pesquisar: ");
    scanf(" %s", nome);

    flagContacto=pesquisaContacto(lista, total, nome);
    if(flagContacto==0){
        printf("Nao existe esse nome na lista!");
    }
    else{
        printf("O numero do(a) %s e: %d!", nome, flagContacto);
    }

    printf("\nNome e numero novo: ");
    scanf(" %s %d", nome, &novo);
    atualizaContacto(lista, total, nome, novo);

    lista= eliminarContacto(lista, &total, "Ana");

    if(lista!=NULL){
        printArray(lista, total);
    }
    else{
        printf("Lista vazia");
    }

    return 0;
}

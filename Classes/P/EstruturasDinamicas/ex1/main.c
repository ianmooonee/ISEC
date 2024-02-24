#include <stdio.h>
#include <stdlib.h>

typedef struct pessoa no, *pno;
 struct pessoa{
    char nome[100];
    int id;
    float peso, altura;
    pno prox;
};

 pno preenchePessoa(){
     pno pessoa;

     pessoa=malloc(sizeof(no));
     if(pessoa==NULL){
         return pessoa;
     }

     printf("Introduza nome, id, peso e altura: ");
     scanf(" %s %d %f %f", pessoa->nome,&pessoa->id, &pessoa->peso, &pessoa->altura);
     pessoa->prox=NULL;

     return pessoa;
 }

 pno adiciona(pno lista){
     pno novo, aux=lista;

     novo=preenchePessoa();

     if(lista==NULL){
         lista=novo;
     }
     else{
         while(aux->prox!=NULL){
             aux=aux->prox;
         }
         aux->prox=novo;
     }

     /*novo->prox=lista;
     lista=novo; //inserir na primeira posição*/

     return lista;
 }

 pno adicionaOrdenado(pno lista){
    pno novo, anterior=NULL, atual=lista;

     novo=preenchePessoa();

     if(lista==NULL){
         lista=novo;
     }
     else{
         while(atual!=NULL && atual->id<novo->id){
             anterior=atual;
             atual=atual->prox;
         }

         if(anterior==NULL){
             lista=novo;
             novo->prox=atual;
         }
         else{
             anterior->prox=novo;
             novo->prox=atual;
         }

     }

     return lista;
 }

 void listarTudo(pno lista){
     pno aux=lista;
     while(aux!=NULL){
         printf("%s %d %.1f %.1f\n", aux->nome, aux->id, aux->peso, aux->altura);
         aux=aux->prox;
     }
 }

 void alteraPeso(pno lista, int idAlt, float novoPeso){
     pno aux=lista;

     while(aux!=NULL){
         if(aux->id==idAlt){
             break;
         }
         aux=aux->prox;
     }

     if(aux==NULL){
         printf("Pessoa nao encontrada!\n");
     }
     else{
         aux->peso=novoPeso;
     }
 }

 pno eliminarPessoa(pno lista, int idElim){
     pno atual=lista, anterior=NULL;

     while(atual!=NULL && atual->id!=idElim){
         anterior=atual;
         atual=atual->prox;
     }

     if(atual==NULL){ //não encontrou
         return lista;
     }
     else if(anterior==NULL){ //é o primeiro nó da lista
         lista=atual->prox; //caso só haja 1 pessoa, a lista fica a NULL
     }
     else{ //é um nó no meio da lista
         anterior->prox=atual->prox;
     }
     free(atual);

     return lista;
 }

 pno eliminarAbaixoIMC(pno lista, float imc){ //assume que a lista não está ordenada
     pno aux=lista, atual=NULL, anterior=NULL;

     while(aux!=NULL){
         atual=aux;
         if(anterior==NULL){
             lista=atual->prox;
             free(atual);
         }

         aux=aux->prox;
     }

     return lista;
 }

 void libertaLista(pno lista){
     pno aux;

     while(lista!=NULL){
         aux=lista;
         lista=lista->prox;
         free(aux);
     }
 }

int main() {
    pno lista=NULL;
    int idP;
    float pP, imc;

    for(int i=0; i<3; i++){
        lista=adiciona(lista);
    }
    //lista= adicionaOrdenado(lista);

    /*printf("Id da pessoa e peso novo: ");
    scanf("%d %f", &idP, &pP);
    alteraPeso(lista, idP, pP);*/

    /*printf("Id da pessoa a eliminar: ");
    scanf("%d", &idP);*/

    //lista=eliminarPessoa(lista, idP);

    printf("IMC maximo: ");
    scanf("%f", &imc);
    lista=eliminarAbaixoIMC(lista, imc);

    listarTudo(lista);

    return 0;
}

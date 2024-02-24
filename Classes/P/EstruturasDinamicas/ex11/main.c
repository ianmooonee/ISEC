#include <stdio.h>
#include <stdlib.h>

#define N 4

typedef struct concorrente no, *pno;

struct concorrente{
    char nome[200];		/* nome do concorrente */
    int id;				/* n.� de aluno: identificador �nico */
    float analise;		/* n�vel de �lcool no sangue */
    pno prox;
};


// Funcao para criar a estrutura dinamica com base na informacao do ficheiro de texto
void cria_listas(pno tab[], char *n1)
{
    FILE *f;
    pno novo;
    int i, x, y;

    f = fopen(n1, "r");
    if(f==NULL)
    {
        printf("Erro no acesso ao ficheiro\n");
        return;
    }
    while(fscanf(f, " %d %d", &x, &y) == 2)
    {
        for(i=0; i<y; i++)
        {
            novo = malloc(sizeof(no));
            if(novo == NULL)
            {
                printf("Erro na alocacao de memoria\n");
                fclose(f);
                return;
            }
            fscanf(f, " %d %f %[^\n]", &novo->id, &novo->analise, novo->nome);
            novo->prox = tab[x];
            tab[x] = novo;
        }
    }
    fclose(f);
}

void listarTudo(pno tab[]){
    for(int i=0; i<N; i++) {
        pno aux=tab[i];
        printf("\nCorrida %d: \n", i+1);
        while (aux != NULL) {
            printf("%s %d %.2f\n", aux->nome, aux->id, aux->analise);
            aux=aux->prox;
        }
    }
}

void listarTerminouTodas(pno lista){
    pno aux=lista;
    printf("Atletas que terminaram tudo: \n");
    while(aux!=NULL){
        printf("%s %d\n", aux->nome, aux->id);
        aux=aux->prox;
    }
}

int getProvas(pno tab[], int id){
    int nprovas=0;

    for(int i=0; i<N; i++) {
        pno aux=tab[i];
        while (aux != NULL) {
            if(id==aux->id){
                nprovas++;
            }
            aux=aux->prox;
        }
    }

    return nprovas;
}



pno terminouTodas(pno tab[]){
    pno terminou=NULL, pessoa=NULL;

        pno aux=tab[0], aux2; //aux percorre os ponteiros do array e o aux2 é para percorrer a lista ligada de cada indíce
        while (aux != NULL) {
            if(getProvas(tab, aux->id)==4){
                pessoa=malloc(sizeof(no));
                if(pessoa==NULL){
                    return pessoa;
                }
                *pessoa=*aux; //copiar o conteudo
                pessoa->prox=NULL;

                if(terminou==NULL){
                    terminou=pessoa;
                }
                else{
                    aux2=terminou;
                    while(aux2->prox!=NULL){
                        aux2=aux2->prox;
                    }
                    aux2->prox=pessoa;
                }
            }
            aux=aux->prox;
        }

    return terminou;
}

void eliminaId(pno tab[], int id){
    for(int i=0; i<N; i++) {
        pno aux=tab[i], atual=aux, anterior=NULL;
        while (aux != NULL) {
            if(id==aux->id){
                while(atual!=NULL && atual->id!=id){
                    anterior=atual;
                    atual=atual->prox;
                }

                if(atual==NULL){
                    break; //não encontrou
                }
                else if(anterior==NULL){ //é o primeiro nó da lista
                    tab[i]=atual->prox;
                }
                else{ //é um nó no meio da lista
                    anterior->prox=atual->prox;
                }
            }
            aux=aux->prox;
        }
    }
}

void eliminaAcimaTaxa(pno tab[]){
    for(int i=0; i<N; i++) {
        pno aux=tab[i];
        while (aux != NULL) {
            if(aux->analise>0.5){
                eliminaId(tab, aux->id);
            }
            aux=aux->prox;
        }
    }
}

int main()
{
    // Declaracao do array de ponteiros
    int provasTerminadas, id;
    pno tab[N] = {NULL}, terminaramTodas=NULL;


    // Criar ED
    cria_listas(tab, "dados_11.txt");

    //listarTudo(tab);

    /*printf("\nInsira o id para ver em quantas provas aparece: ");
    scanf(" %d", &id);
    printf("O id %d terminou %d provas!\n", id, getProvas(tab, id));*/

    /*putchar('\n');
    terminaramTodas=terminouTodas(tab);
    listarTerminouTodas(terminaramTodas);*/

    eliminaAcimaTaxa(tab);
    listarTudo(tab);


    return 0;
}

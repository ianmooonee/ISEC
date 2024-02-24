#include <stdio.h>
#include <stdlib.h>

typedef struct aluno Alunos, *pAlunos;
struct aluno{
    int id; // Id numérico do aluno
    int nota; // Nota final obtida em percentagem
    pAlunos prox;
};

pAlunos insereAluno(pAlunos a, pAlunos novo){
    pAlunos aux;

    if(a==NULL){
        a=novo;
    }
    else{
        aux=a;
        while(aux->prox!=NULL){
            aux=aux->prox;
        }
        aux->prox=novo;
    }

    return a;
}

pAlunos mediaProvas(char *nomeFich, pAlunos a){
    FILE *f;
    pAlunos aluno;
    int nprovas=0, nalunos=0, peso[5]={0}, nota;
    float media;

    f=fopen(nomeFich, "r");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return NULL;
    }

    fscanf(f, " %*[^:]:%d", &nalunos);
    printf("Numero de alunos: %d\n", nalunos);

    fscanf(f, " %*[^:]:%d", &nprovas);
    printf("Numero de provas: %d\n", nprovas);

    fscanf(f, " %*[^:]:");

    printf("Peso das provas:");
    for(int i=0; i<nprovas; i++){
        fscanf(f, " %d", &peso[i]);
        printf(" %d", peso[i]);
    }
    putchar('\n');

    for(int i=0; i<nalunos; i++){
        aluno=malloc(sizeof (Alunos));
        aluno->id=i;
        aluno->nota=0;
        aluno->prox=NULL;

        media=0;
        fscanf(f, " %*[^:]:");
        for(int j=0; j<nprovas; j++){
            fscanf(f, " %d", &nota);
            media+=(float)nota*((float)peso[j]/100);
            a->nota=(int)media;
        }
        printf("Media aluno %d: %.2f\n", i+1, media);
        a=insereAluno(a, aluno);
    }

    //erro
    fclose(f);
    return a;
}

int main() {
    pAlunos a=NULL;

    a=mediaProvas("notas_ex9.txt", a);

    while(a!=NULL){
        printf("ID %d: %d", a->id, a->nota);
        a=a->prox;
    }

    return 0;
}

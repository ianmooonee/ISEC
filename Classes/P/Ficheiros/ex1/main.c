#include <stdio.h>
#include <string.h>

typedef struct paragem{
    char nome[50];
    int minutos;
}Paragem, *pParagem;

void mostraFich(char *nomeFich){
    FILE *f;
    Paragem p;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return;
    }

    while(fread(&p, sizeof(Paragem), 1, f)){
        printf("Paragem: %s %d\n", p.nome, p.minutos);
    }

    fclose(f);
}

int existe(char *nomeFich, char *nomeParagem){
    FILE *f;
    Paragem p;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return 0;
    }

    while(fread(&p, sizeof(Paragem), 1, f)){
        if(strcmp(p.nome, nomeParagem)==0){
            fclose(f);
            return 1;
        }
    }

    fclose(f);
    return 0;
}

int liga(char *nomeFich, char *inicio, char *fim){
    FILE *f;
    Paragem p;
    int flag=0, minutos=0;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return 0;
    }

    while(fread(&p, sizeof(Paragem), 1, f)){
        if(flag==1){
            minutos+=p.minutos;
        }
        if(strcmp(p.nome, inicio)==0){
            flag=1;
        }
        else if(strcmp(p.nome, fim)==0 && flag==1){
            fclose(f);
            return minutos;
        }
    }

    fclose(f);
    return -1;
}

int main() {

    mostraFich("cp_ex1.dat");
    printf("Existe? 0 ou 1: %d\n", existe("cp_ex1.dat", "Algarve"));
    printf("Existe ligaçao? -1 ou tempo: %d\n", liga("cp_ex1.dat", "Lisboa", "Pombal"));

    return 0;
}

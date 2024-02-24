#include <stdio.h>
#include <stdlib.h>

void mostraFich(char *nomeFich){
    FILE *f;
    int num;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return;
    }

    while(fread(&num, sizeof(int), 1, f)){
        printf("%d ", num);
    }

    fclose(f);
}

float soma_e_media(char *nomeFich, int *soma){
    FILE *f;
    int num, lidos=0;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return 0;
    }

    while(fread(&num, sizeof(int), 1, f)){
        (*soma)+=num;
        lidos++;
    }

    return (float)(*soma)/(float)lidos;
}

int *criaVetorPares(char *nomeFich, int *tam){
    int *pares=NULL, num, aux=0;
    FILE *f;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return NULL;
    }

    (*tam)=0;

    while(fread(&num, sizeof(int), 1, f)){
        if(num%2==0 && num>0){ //é par
            (*tam)++;
        }
    }

    rewind(f); //volta ao início do ficheiro

    if(*tam>0) {
        pares = malloc((*tam) * sizeof(int));
        if(pares==NULL){
            fprintf(stderr, "Erro ao alocar array.\n");
            fclose(f);
            return NULL;
        }

        while(fread(&num, sizeof(int), 1, f) && aux!=(*tam)){
            if(num%2==0 && num>0){ //é par
                pares[aux]=num;
                aux++;
            }
        }
    }

    return pares;
}

void armazena_valores(char *nomeFich, char *acimaIgual, char *abaixo, float media){
    FILE *f, *ai, *ab;
    int num;

    f=fopen(nomeFich, "rb");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", nomeFich);
        return;
    }

    ai=fopen(acimaIgual, "wb");
    if(ai==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", acimaIgual);
        fclose(f);
        return;
    }

    ab=fopen(abaixo, "wb");
    if(ab==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s.\n", abaixo);
        fclose(f);
        fclose(ai);
        return;
    }

    while(fread(&num, sizeof(int), 1, f)){
        if((float)num>=media){ //é par
            fwrite(&num, sizeof(int), 1, ai);
        }
        else{
            fwrite(&num, sizeof(int), 1, ab);
        }
    }

    fclose(f);
    fclose(ai);
    fclose(ab);
}

int main() {
    int soma=0, *pares, tam;
    float media;

    mostraFich("valoresEx2.bin");

    media=soma_e_media("valoresEx2.bin", &soma);
    printf("\nSoma: %d\tMedia: %.2f\n", soma, media);

    pares=criaVetorPares("valoresEx2.bin", &tam);
    printf("Vetor de pares: ");
    for(int i=0; i<tam; i++){
        printf("%d ", pares[i]);
    }

    armazena_valores("valoresEx2.bin", "AcimaIgualMedia.bin", "AbaixoMedia.bin", media);

    printf("\nVetor com valores acima e iguais a media: ");
    mostraFich("AcimaIgualMedia.bin");
    printf("\nVetor com valores inferiores a media: ");
    mostraFich("AbaixoMedia.bin");

    return 0;
}

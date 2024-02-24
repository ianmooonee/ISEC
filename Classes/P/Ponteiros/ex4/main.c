#include <stdio.h>

void f(const int *t, int tam, int *np, int *ni, int *maior, int *pos){
    for(int i=0; i<tam; i++){
        if(t[i]%2==0){
            (*np)++;
        }
        else{
            (*ni)++;
        }

        if(t[i]>*maior){
            *maior=t[i];
            *pos=i;
        }
    }
}

void separarParImpar(int *t, int tam){ //fazer sem tabela aux
    int aux, pares=0;

    for(int i=0; i<tam; i++){
        if(t[i]%2==0){
            aux=t[pares];
            t[pares]=t[i];
            t[i]=aux;
            pares++;
        }
    }
}

int main() {
    int tab[10]={1,3,7,5,2,10,9,7,7,1};
    int np=0, ni=0, maior=0, pos=0;

    f(tab, 10, &np, &ni, &maior, &pos);

    printf("Impares: %d\nPares: %d\nMaior: %d\nPosicao do maior: %d\n", ni, np, maior, pos);
    separarParImpar(tab, 10);

    for(int i=0; i<10; i++){
        printf("%d ", tab[i]);
    }

    return 0;
}

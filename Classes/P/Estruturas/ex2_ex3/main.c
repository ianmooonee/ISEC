#include <stdio.h>
#include "ponto.h"
#include "retangulo.h"

void addRet(retangulo2D *tab, int *total){
    if(*total>=10){
        printf("Total atingido!\n");
        return;
    }

    retangulo2D ret;

    initRet(&ret);
    tab[*total]=ret;
    (*total)++;
}

void remRet(retangulo2D *tab, int *total){
    if(*total < 1)
        return;
    else if(*total == 1) {
        (*total)--;
        return;
    }

    int area, menorArea=tab[0].largura*tab[0].altura, posMenor=0;

    for(int i=0; i<*total; i++){
        area=tab[i].largura * tab[i].altura;
        if(area<menorArea){
            menorArea=area;
            posMenor=i;
        }
    }

    for(int i=posMenor; i<(*total-1); i++){
        tab[i]=tab[i+1];
    }

    printf("\nEliminado Retangulo %d\n", posMenor);

    (*total)--;
}

void printArray(retangulo2D tab[], int total){
    for(int i=0; i<total; i++){
        printf("Retangulo %d:\n", i+1);
        printRetangulo(tab[i]);
        putchar('\n');
    }
}

int printQuadrados(retangulo2D tab[], int total){
    int conta=0;
    for(int i=0; i<total; i++){
        if(tab[i].largura==tab[i].altura && tab[i].cie.x==tab[i].cie.y) {
            conta++;
            printf("Retangulo quadrado %d:\n", i + 1);
            printRetangulo(tab[i]);
            putchar('\n');
        }
    }

    return conta;
}

int main(){
    retangulo2D tab[10];
    int total=0, quadrados;

    addRet(tab, &total);
    addRet(tab, &total);
    addRet(tab, &total);
    remRet(tab, &total);
    printArray(tab, total);
    quadrados=printQuadrados(tab, total);
    if(quadrados>0){
        printf("Fora encontrados %d quadrados!\n", quadrados);
    }
    else{
        printf("Nao existem quadrados!\n");
    }

    /* ex2
    retangulo2D ret={{0,0}, 3, 4}, ret2;
    ponto2D p={2,1};

    calculaArea(ret);
    dentroRet(ret, p);
    moveRet(&ret, 1, 1);
    printRetangulo(ret);

    //initRet(&ret2);
    //printRetangulo(ret2);
     */

    return 0;
}

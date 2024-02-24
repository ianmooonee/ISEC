//
// Created by Pedro Serrano on 23/03/2023.
//

#include <stdio.h>
#include "retangulo.h"

void printRetangulo(retangulo2D ret){
    ponto2D temp={ret.cie.x, ret.cie.y};

    printf("Canto inferior esquerdo: ");
    printPonto(temp);

    printf("Canto superior esquerdo: ");
    movePonto(&temp, 0, ret.altura);
    printPonto(temp);

    printf("Canto superior direito: ");
    movePonto(&temp, ret.largura, 0);
    printPonto(temp);

    printf("Canto inferior direito: ");
    movePonto(&temp, 0, -ret.altura);
    printPonto(temp);
}

void initRet(retangulo2D * ret){
    printf("Introduza as coordenadas do CIE, atura e largura: ");
    scanf("%d %d %d %d", &ret->cie.x, &ret->cie.y, &ret->altura, &ret->largura);
}

void dentroRet(retangulo2D r, ponto2D p){
    if((p.y > r.cie.y) && (p.x > r.cie.x) && (p.y < (r.cie.y + r.altura)) && (p.x < (r.cie.x + r.largura)))
        printf("Esta dentro!\n");
    else
        printf("Nao esta dentro!\n");
}

void calculaArea(retangulo2D ret){
    printf("Area do retangulo: %d\n", ret.altura*ret.largura);
}

void moveRet(retangulo2D* ret, int dx, int dy){
    ret->cie.x+=dx;
    ret->cie.y+=dy;
}
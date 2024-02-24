//
// Created by Pedro Serrano on 23/03/2023.
//

#include <stdio.h>
#include "ponto.h"

void printPonto(ponto2D a){
    printf("Ponto: (%d,%d)\n", a.x, a.y);
}

void initPonto(ponto2D* p){
    printf("Introduza as coordenadas pretendidas de x e y: ");
    scanf("%d %d", &p->x, &p->y);
}

void movePonto(ponto2D* p, int dx, int dy){
    p->x+=dx;
    p->y+=dy;
}
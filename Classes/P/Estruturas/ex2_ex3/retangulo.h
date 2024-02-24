//
// Created by Pedro Serrano on 23/03/2023.
//

#ifndef EX2_RETANGULO_H
#define EX2_RETANGULO_H

#include <stdio.h>
#include "ponto.h"

typedef struct retangulo{
    ponto2D cie;
    int altura;
    int largura;
} retangulo2D;

void printRetangulo(retangulo2D ret);

void initRet(retangulo2D * ret);

void calculaArea(retangulo2D ret);

void dentroRet(retangulo2D r, ponto2D p);

void moveRet(retangulo2D* p, int dx, int dy);

#endif //EX2_RETANGULO_H

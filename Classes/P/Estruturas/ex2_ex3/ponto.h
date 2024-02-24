//
// Created by Pedro Serrano on 23/03/2023.
//

#ifndef EX2_PONTO_H
#define EX2_PONTO_H

typedef struct ponto{
    int x, y;
} ponto2D;

void printPonto(ponto2D a);

void initPonto(ponto2D* p);

void movePonto(ponto2D* p, int dx, int dy);

#endif //EX2_PONTO_H

//
// Created by Pedro Serrano on 23/03/2023.
//

#ifndef EX1_PONTO_H
#define EX1_PONTO_H

// alinea a)
typedef struct ponto{
    int x, y;
} ponto2D;

void printPonto(ponto2D a);

void initPonto(ponto2D* p);

void movePonto(ponto2D* p, int dx, int dy);

void mesmaReta(ponto2D* p1, ponto2D* p2, ponto2D* p3);

#endif //EX1_PONTO_H

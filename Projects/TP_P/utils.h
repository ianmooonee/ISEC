//
// Created by Pedro Serrano on 17/05/2023.
//

#ifndef TP_P_UTILS_H
#define TP_P_UTILS_H

#include "linhas.h"
#include "paragens.h"

void retiraEspacos(char *s); //remove espaços de uma string
void saveAll(pLinha l, pParagem paragens, int totalLinhas, int totalParagens, char *nomeFich); //guarda tudo no ficheiro .dat
void loadAll(pParagem *p, pLinha *l, int *totalParagens, int *totalLinhas, int *id, char *nomeFich); //carrega tudo do ficheiro .dat
void loadLinhaTxt(pLinha *l, pParagem p, int total, char *nomeFich); //carrega linha de um ficheiro de texto
pParagem loadParagens(int *totalParagens, int *id, FILE *f); //lê array de paragens do ficheiro .dat
pLinha loadLinhas(pParagem p, int totalParagens, int *totalLinhas, FILE *f); //lê as linhas do ficheiro .dat
#endif //TP_P_UTILS_H

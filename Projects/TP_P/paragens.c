//
// Created by Pedro Serrano on 13/04/2023.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "paragens.h"

#define CDP "P00%d" //template para gerar código de paragem

void initParagem(pParagem p, char *nome, int codigoParagem) {
    strcpy(p->nome, nome);
    sprintf(p->codigo, CDP, codigoParagem);
}

int listarParagens(pParagem lista, int total) {
    if (total > 0) {
        for (int i = 0; i < total; i++) {
            printf("Paragem: %s %s", lista[i].nome, lista[i].codigo);
            putchar('\n');
        }

        return 1;
    }
    return 0;
}

int verificaExistenciaParagem(pParagem lista, int total, char *nome) {
    for (int i = 0; i < total; i++) {
        if (strcmp(lista[i].nome, nome) == 0) {
            //printf("Ja existe uma paragem com este nome!");
            return 1;
        }
    }

    return 0;
}

int verificaExistenciaParagemTxt(pParagem lista, int total, char *nome, char *codigo) {
    for (int i = 0; i < total; i++) {
        if (strcmp(lista[i].nome, nome) == 0 && strcmp(lista[i].codigo, codigo) == 0) {
            return 1;
        }
    }

    return 0;
}


pParagem adicionarParagem(pParagem lista, int *total, char *nome, int *codigoParagem) {
    pParagem aux;
    int ret;

    if (*total > 0) {
        ret = verificaExistenciaParagem(lista, *total, nome);
        if (ret == 1) {
            return lista;
        }
    }

    aux=malloc(sizeof(Paragem)*(*total+1));
    if (aux == NULL) {
        fprintf(stderr, "Erro ao realocar o array das paragens.");
        return lista;
    }

    for(int i=0; i<*total; i++){
        aux[i]=lista[i];
    }

    lista = aux;

    initParagem(lista + *total, nome, *codigoParagem);

    (*codigoParagem)++;
    (*total)++;

    return lista;
}

pParagem eliminarParagem(pParagem vec, int *total, char *nome) {
    pParagem aux;
    Paragem backup;
    int i;

    for (i = 0; i < *total; i++) {
        if (strcmp(vec[i].nome, nome) == 0) {
            break;
        }
    }

    if (i == *total) {
        return vec;
    }

    if ((*total - 1) == 0) {
        free(vec);
        (*total)--;
        return NULL;
    }

    backup = vec[i];
    vec[i] = vec[*total - 1];

    aux = realloc(vec, sizeof(Paragem) * (*total - 1));
    if (aux == NULL) {
        fprintf(stderr, "Erro ao realocar o array das paragens.\n");
        vec[i] = backup;
        return vec;
    }

    vec = aux;
    (*total)--;

    return vec;
}
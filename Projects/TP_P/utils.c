//
// Created by Pedro Serrano on 17/05/2023.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "utils.h"

void retiraEspacos(char *s) {
    char *d = s; //d itera a string
    do {
        while (*d == ' ') {
            ++d;
        }
    } while ((*s++ = *d++));
}

void saveAll(pLinha l, pParagem paragens, int totalLinhas, int totalParagens, char *nomeFich) {
    FILE *f;
    f = fopen(nomeFich, "wb");
    if (f == NULL) {
        fprintf(stderr, "Erro ao abrir o ficheiro %s para escrita.\n", nomeFich);
        return;
    }

    //escrever array das paragens
    fwrite(&totalParagens, sizeof(int), 1, f);
    fwrite(paragens, sizeof(Paragem), totalParagens, f);

    //escrever lista das linhas
    pParLinha aux;

    fwrite(&totalLinhas, sizeof(int), 1, f);
    while (l != NULL) {
        fwrite(l, sizeof(Linha), 1, f);
        aux = l->paragens;
        while (aux != NULL) {
            fwrite(aux->paragem->nome, 100 * sizeof(char), 1, f);
            aux = aux->prox;
        }

        l = l->prox;
    }

    fclose(f);
}

pParagem loadParagens(int *totalParagens, int *id, FILE *f) {
    pParagem auxP;

    //ler array das paragens
    fread(totalParagens, sizeof(int), 1, f);
    auxP = malloc(sizeof(Paragem) * (*totalParagens));
    if (auxP == NULL) {
        fprintf(stderr, "Erro ao alocar memoria para o array das paragens.\n");
        *totalParagens = 0;
        return NULL;
    }

    fread(auxP, sizeof(Paragem), *totalParagens, f);

    *id = *totalParagens;

    return auxP;
}

pLinha loadLinhas(pParagem p, int totalParagens, int *totalLinhas, FILE *f) {
    pLinha l = NULL, novo;
    int cont, contP;

    fread(totalLinhas, sizeof(int), 1, f);
    cont = *totalLinhas;


    //ler lista das linhas
    while (cont > 0) {
        novo = malloc(sizeof(Linha));
        if (novo == NULL) {
            fprintf(stderr, "Erro ao alocar memoria para a nova linha.\n");
            return l;
        }

        fread(novo, sizeof(Linha), 1, f);
        novo->prox = NULL;
        novo->paragens = NULL;

        l = insereLinha(l, novo);

        contP = novo->totalP;

        while (contP > 0) {
            pParLinha aux = NULL;
            char auxNome[100];

            fread(auxNome, 100 * (sizeof(char)), 1, f);

            for (int i = 0; i < totalParagens; i++) {
                if (strcmp(auxNome, p[i].nome) == 0) {
                    aux = malloc(sizeof(ParLinha));
                    if (aux == NULL) {
                        fprintf(stderr, "Erro ao alocar memoria para a paragem.\n");
                        return l;
                    }

                    aux->paragem = p + i;
                    aux->prox = NULL;

                    break;
                }
            }

            novo->paragens = insereParagemLinha(novo->paragens, aux);

            contP--;
        }

        cont--;
    }

    return l;
}

void loadLinhaTxt(pLinha *l, pParagem p, int totalParagens, char *nomeFich) {
    char nomeLinha[100], codigoParagem[100], nomeParagem[100], linhaParagem[100], token[2] = "#";
    pLinha novaLinha = malloc(sizeof(Linha));
    int contP = 0;

    FILE *f = fopen(nomeFich, "rt");
    if (f == NULL) {
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    if (fscanf(f, " %99[^\n]", nomeLinha) == 1) {
        if (verificaSeExisteLinha(*l, nomeLinha) == 1) {
            fprintf(stderr, "Linha ja existe.\n");
            fclose(f);
            return;
        }

        //iniciar nova linha
        strcpy(novaLinha->nome, nomeLinha);
        novaLinha->totalP = 0;
        novaLinha->paragens = NULL;
        novaLinha->prox = NULL;

    }

    while (fscanf(f, " %99[^\n]", linhaParagem) == 1) {
        strcpy(nomeParagem, strtok(linhaParagem, token));
        nomeParagem[strlen(nomeParagem) - 1] = '\0';

        strcpy(codigoParagem, strtok(NULL, token));
        codigoParagem[strlen(codigoParagem)] = '\0';
        retiraEspacos(codigoParagem);

        printf("nomeParagem: %s\tcodigoParagem: %s\n", linhaParagem, codigoParagem);

        if (verificaExistenciaParagemTxt(p, totalParagens, nomeParagem, codigoParagem) == 0) {
            fprintf(stderr, "Paragem nao existe.\n");
        } else {
            contP++;
            pParLinha aux = malloc(sizeof(ParLinha));
            if (aux == NULL) {
                fprintf(stderr, "Erro ao alocar memoria para a paragem.\n");
                free(novaLinha);
                return;
            }

            for (int i = 0; i < totalParagens; i++) {
                if (strcmp(p[i].nome, nomeParagem) == 0 && strcmp(p[i].codigo, codigoParagem) == 0) {
                    aux->paragem = p + i;
                    aux->prox = NULL;
                    break;
                }
            }

            novaLinha->paragens = insereParagemLinha(novaLinha->paragens, aux);
            novaLinha->totalP = contP;
        }
    }

    fclose(f);
    if (novaLinha->totalP > 0) {
        *l = insereLinha(*l, novaLinha);
    } else {
        free(novaLinha);
    }
}

void loadAll(pParagem *p, pLinha *l, int *totalParagens, int *totalLinhas, int *id, char *nomeFich) {
    FILE *f;
    f = fopen(nomeFich, "rb");
    if (f == NULL) {
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    *p = loadParagens(totalParagens, id, f);
    *l = loadLinhas(*p, *totalParagens, totalLinhas, f);


    fclose(f);
}
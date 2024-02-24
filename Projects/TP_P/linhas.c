//
// Created by Pedro Serrano on 06/05/2023.
//

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "linhas.h"

pLinha removeLinhasSemParagens(pLinha l, int *totalLinhas) {
    pLinha atual = l, ant = NULL;

    while (atual != NULL) {
        if (atual->totalP == 0) { //pesquisa se há linhas sem nenhuma paragem
            if (ant == NULL) {
                l = atual->prox;
            } else {
                ant->prox = atual->prox;
            }

            pLinha aux = atual;
            atual = atual->prox;
            free(aux);
            (*totalLinhas)--;
        } else {
            ant = atual;
            atual = atual->prox;
        }
    }

    return l;
}

pLinha insereLinha(pLinha l, pLinha novo) {
    pLinha aux;

    if (l == NULL) { //insere no inicio da lista
        l = novo;
    } else { //insere no fim da lista
        aux = l;
        while (aux->prox != NULL) {
            aux = aux->prox;
        }
        aux->prox = novo;
    }

    return l;
}

pParLinha insereParagemLinha(pParLinha paragens, pParLinha novo) {
    pParLinha aux;

    if (paragens == NULL) { //insere no inicio da lista de paragens
        paragens = novo;
    } else { //insere no fim da lista de paragens
        aux = paragens;
        while (aux->prox != NULL) {
            aux = aux->prox;
        }
        aux->prox = novo;
    }

    return paragens;
}

int verificaSeExisteParagem(pParLinha l, char *nomeParagem) {
    while (l != NULL) {
        if (strcmp(l->paragem->nome, nomeParagem) == 0) {
            fprintf(stderr, "Paragem %s ja existe nesta linha!\n", nomeParagem);
            return 1;
        }
        l = l->prox;
    }

    return 0;
}

int verificaSeExisteLinha(pLinha l, char *nomeLinha) {
    while (l != NULL) {
        if (strcmp(l->nome, nomeLinha) == 0) {
            return 1;
        }
        l = l->prox;
    }

    return 0;
}

pLinha criaLinha(pLinha l, char *nome, pParagem paragens, int totalParagens, int *totalLinhas) {
    char nomeParagem[100];
    pLinha novo, aux;
    int flag = 1;

    if (verificaSeExisteLinha(l, nome) == 1) {
        fprintf(stderr, "Ja existe uma linha com esse nome!\n");
        return l;
    }

    novo = malloc(sizeof(Linha));
    if (novo == NULL) {
        fprintf(stderr, "Erro ao alocar memoria para a nova linha.\n");
        return l;
    }

    strcpy(novo->nome, nome);
    novo->totalP = 0;
    novo->paragens = NULL;
    novo->prox = NULL;

    if (l == NULL) {
        l = novo;
    } else {
        aux = l;
        while (aux->prox != NULL) {
            aux = aux->prox;
        }
        aux->prox = novo;
    }

    do {
        printf("Insira o nome da paragem (fim para parar): ");
        scanf("%99[^\n]", nomeParagem);
        while (getchar() != '\n'); //limpar buffer
        if (addParagemLinha(l, nome, nomeParagem, paragens, totalParagens) == 1) {
            printf("Paragem adicionada com sucesso!\n");
        } else {
            fprintf(stderr, "Paragem nao encontrada!\n");
        }
        if (strcmp(nomeParagem, "fim") == 0 && l->totalP > 0) { //impedir criação de linhas sem paragens manualmente
            flag = 0;
        }
    } while (flag);

    (*totalLinhas)++;

    return l;
}

int addParagemLinha(pLinha l, char *nomeLinha, char *nomeParagem, pParagem paragens, int totalParagens) {
    pParLinha novo, aux;

    while (l != NULL && strcmp(l->nome, nomeLinha) != 0) {
        l = l->prox;
    }

    if (l != NULL) //encontramos a linha para adicionar uma paragem
    {
        aux = l->paragens;
        for (int i = 0; i < totalParagens; i++) {
            if (strcmp(paragens[i].nome, nomeParagem) == 0 && verificaSeExisteParagem(l->paragens, nomeParagem) == 0) {
                novo = malloc(sizeof(ParLinha));
                if (novo == NULL) {
                    fprintf(stderr, "Erro ao alocar memoria para a nova paragem.\n");
                    return -1;
                }

                novo->paragem = paragens + i;
                novo->prox = NULL;

                if (l->paragens == NULL) {
                    l->paragens = novo;
                } else {
                    while (aux->prox != NULL) {
                        aux = aux->prox;
                    }
                    aux->prox = novo;
                }
                l->totalP++;

                return 1;
            }
        }
    }

    return 0;
}

pLinha delParagemTodas(pLinha l, char *nomeParagem, int *totalLinhas) {
    pParLinha atual, ant;
    pLinha aux = l;

    while (aux != NULL) {
        atual = aux->paragens;
        ant = NULL;

        while (atual != NULL && strcmp(atual->paragem->nome, nomeParagem) != 0) {
            ant = atual;
            atual = atual->prox;
        }

        if (atual == NULL) {
             //não encontrou
        } else if (ant == NULL) { //é o primeiro nó da lista
            aux->paragens = atual->prox;
        } else { //é um nó no meio da lista
            ant->prox = atual->prox;
        }
        aux->totalP--;
        free(atual);

        aux = aux->prox;
    }

    l = removeLinhasSemParagens(l, totalLinhas);
    return l;
}

int delParagemLinha(pLinha l, char *nomeLinha, char *nomeParagem) { //não está a eliminar a linha vazia
    pParLinha atual, ant;

    while (l != NULL && strcmp(l->nome, nomeLinha) != 0) {
        l = l->prox;
    }

    if (l != NULL && l->totalP > 1) {
        atual = l->paragens;
        ant = NULL;

        while (atual != NULL && strcmp(atual->paragem->nome, nomeParagem) != 0) {
            ant = atual;
            atual = atual->prox;
        }

        if (atual == NULL) {
            return 0; //não encontrou
        } else if (ant == NULL) { //é o primeiro nó da lista
            l->paragens = atual->prox;
        } else { //é um nó no meio da lista
            ant->prox = atual->prox;
        }
        l->totalP--;
        free(atual);

        return 1;
    }

    return 0;
}

void listaLinhas(pLinha l) {
    pParLinha aux;

    while (l != NULL) {
        printf("%s com %d paragem(ns): ", l->nome, l->totalP);
        aux = l->paragens;
        while (aux != NULL) {
            printf("%s\t", aux->paragem->nome);
            aux = aux->prox;
        }
        l = l->prox;

        putchar('\n');
    }
}

void libertaTudo(pLinha l) {
    pParLinha aux, aux2;

    while (l != NULL) {
        aux = l->paragens;
        while (aux != NULL) {
            aux2 = aux;
            aux = aux->prox;
            free(aux2);
        }
        l = l->prox;
    }
}
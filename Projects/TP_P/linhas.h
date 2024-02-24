//
// Created by Pedro Serrano on 06/05/2023.
//

#ifndef TP_P_LINHAS_H
#define TP_P_LINHAS_H

#include "paragens.h"

typedef struct paragem ParLinha, *pParLinha;
typedef struct linha Linha, *pLinha;

struct paragem {
    pParagem paragem; //ponteiro para paragem do array de paragens
    pParLinha prox;
};

struct linha {
    char nome[100];
    int totalP; //total de paragens da linha
    pLinha prox;
    pParLinha paragens; //ponteiro para primeira paragem da linha
};

//fazer função para verificar se alguma linha fica vazia depois de chamar a função delParagemTodas
pLinha criaLinha(pLinha l, char *nome, pParagem paragens, int totalParagens, int *totalLinhas);  //cria uma nova linha
pLinha insereLinha(pLinha l, pLinha novo); //inserir linha no fim da lista
pLinha delParagemTodas(pLinha l, char *nomeParagem,int *totalLinhas); //eliminar uma paragem, ordenadamente, "nomeParagem" de todas as linhas
pLinha removeLinhasSemParagens(pLinha l, int *totalLinhas); //remover as linhas que não têm paragens após a remoção de uma paragem do array de paragens
pParLinha insereParagemLinha(pParLinha paragens, pParLinha novo); //inserir paragem no fim da lista
int addParagemLinha(pLinha l, char *nomeLinha, char *nomeParagem, pParagem paragens, int totalParagens); //adiciona uma paragem a uma linha
void listaLinhas(pLinha l); //lista todas as linhas
int verificaSeExisteParagem(pParLinha l, char *nomeParagem); //verifica se já existe uma paragem "nomeParagem" numa linha
int verificaSeExisteLinha(pLinha l, char *nomeLinha); //verifica se já existe uma linha "nomeLinha"
int delParagemLinha(pLinha l, char *nomeLinha,char *nomeParagem); //eliminar uma paragem, ordenadamente, "nomeParagem" de uma linha "nomeLinha"
void libertaTudo(pLinha l); //liberta toda a memória alocada para as linhas e paragens
#endif //TP_P_LINHAS_H

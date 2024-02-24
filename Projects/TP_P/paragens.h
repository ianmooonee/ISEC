//
// Created by Pedro Serrano on 13/04/2023.
//

#ifndef TP_P_PARAGENS_H
#define TP_P_PARAGENS_H

typedef struct {
    char nome[100];
    char codigo[5];
} Paragem, *pParagem;

void initParagem(Paragem *p, char *nome, int codigoParagem); //inicializa os valores de uma paragem
int listarParagens(Paragem *lista, int total); //mostra todas as paragens do array
int verificaExistenciaParagem(Paragem *lista, int total, char *nome); //verifica se uma paragem "nome" já existe no array
int verificaExistenciaParagemTxt(pParagem lista, int total, char *nome, char *codigo); //verifica se uma paragem "nome" e "codigo" já existe no array
pParagem adicionarParagem(Paragem *lista, int *total, char *nome, int *codigoParagem); //adiciona a paragem "nome" ao array
pParagem eliminarParagem(Paragem *lista, int *total, char *nome); //elimina a paragem "nome" do array
#endif //TP_P_PARAGENS_H

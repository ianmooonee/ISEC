#ifndef _BACKEND_H_
#define _BACKEND_H_

#include "geral.h"

void listItems(Node lista);
void sair(int sign,siginfo_t *i,void *secret);
void readItems(char *fich, pNode lista);
void listUsers(Node lista);
void listProms(Node lista);
void resetUserArray(pNode lista, int index);
void terminar(Node lista);
void resetVida(User user, pNode lista);
void *tempoItems(void *lista);
void *setUpPromotor(void* lista);
void *recebeClientes(void *lista1);
void *execComandosCliente(void *lista);
void *sinalVida(void *lista);
void leItems(pNode lista);
int validaUtilizadores(pNode lista, User usr);
void leUtilizadores(pNode lista);
void execSell(User u, pNode lista);
void readPromotores(char *fich, pNode lista);
void lePromotores(pNode lista);
void lancaProms(pNode lista);
void aplicaProm(pNode lista, char msg[TAM_STR]);
void resetPromArray(pNode lista, int index);

#endif
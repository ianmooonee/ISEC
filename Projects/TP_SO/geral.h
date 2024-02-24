#ifndef _GERAL_H_
#define _GERAL_H_

#define TAM_STR 256
#define MAX_CLI 20
#define MAX_ITEMS 30
#define MAX_PROM 10
#define FIFO_SERVER "FIFOSERVER"
#define FIFO_SERVER_CMD "FIFOCOMANDOS"
#define FIFO_CLI_INIT "FIFOCLI%d"
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>

typedef struct{
    int id;
    char nome_item[TAM_STR];
    char categoria[TAM_STR];
    int valor_atual;
    int compre_ja;
    int duracao;
    char vendedor[TAM_STR];
    char licitador[TAM_STR];
} Item, *pItem;

typedef struct{
    char nome_prom[TAM_STR];
    char categoria[TAM_STR];
    int tempoAtivo;
    int desconto;
    pid_t pid;
} Promotor, *pPromotor;

typedef struct{
    int op;
    char prom[TAM_STR];
    char frase[TAM_STR];
    char *ms;
} Msg, *pMsg;

typedef struct{
    char nome[TAM_STR];
    char password[TAM_STR];
    pid_t pid;
    int vida;
    char cmd[TAM_STR];
    char args[TAM_STR];
} User, *pUser;

typedef struct{
    User users[20];
    Item items[30];
    Promotor promotores[10];
    int nusers;
    int nitems;
    int nprom;
    int idTotal;
    pthread_mutex_t *m;
    int stop;
    int vidaValida;
    int promsLancados;
    char *fichuser;
    char *fichitems;
} Node, *pNode;

#endif
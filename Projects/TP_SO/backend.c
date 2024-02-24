#include "backend.h"
#include "env.h"
#include "users_lib.h"
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <errno.h>
#include <sys/stat.h>
#include <pthread.h>
#include <math.h>

#define FIFO_CLI_INIT "FIFOCLI%d"
char FIFO_CLI[100];
pthread_t threadClientes;
pthread_t t[3];
pthread_t threadPromotores[10];

void resetUserArray(pNode lista, int index)
{
    for (int i = index; i < lista->nusers; i++)
    {
        pthread_mutex_lock(lista->m);
        lista->users[i] = lista->users[i + 1];
        pthread_mutex_unlock(lista->m);
    }
    pthread_mutex_lock(lista->m);
    lista->nusers--;
    pthread_mutex_unlock(lista->m);
}

void resetItemArray(pNode lista, int index)
{
    for (int i = index; i < lista->nitems; i++)
    {
        pthread_mutex_lock(lista->m);
        lista->items[i] = lista->items[i + 1];
        pthread_mutex_unlock(lista->m);
    }
    pthread_mutex_lock(lista->m);
    lista->nitems--;
    pthread_mutex_unlock(lista->m);
}

void resetPromArray(pNode lista, int index)
{
    for (int i = index; i < lista->nprom; i++)
    {
        pthread_mutex_lock(lista->m);
        lista->promotores[i] = lista->promotores[i + 1];
        pthread_mutex_unlock(lista->m);
    }
    pthread_mutex_lock(lista->m);
    lista->nprom--;
    lista->promsLancados--;
    pthread_mutex_unlock(lista->m);
}

void leItems(pNode lista)
{
    char *nf = getenv_items();
    if (nf != NULL)
    {
        readItems(nf, lista);
        if (lista->nitems == 0)
        {
            printf("Erro a ler items!\n");
            return;
        }
        lista->fichitems=nf;
    }
    else
    {
        printf("Defina a variavel do ficheiro dos items.\n");
    }
}

void leUtilizadores(pNode lista){
    char *nu = getenv_users();
    int n = loadUsersFile(nu);

    if (nu != NULL)
    {
        if (n == -1)
        {
            printf("Erro a ler utilizadores!\n");
            return;
        }
        lista->fichuser=nu;
    }
    else
    {
        printf("Defina a variavel do ficheiro de utilizadores.\n");
    }

    printf("N utilizadores lidos: %d\n", n);

    lista->nusers = n;

    return;
}

int validaUtilizadores(pNode lista, User usr)
{
    int res;

    res = isUserValid(usr.nome, usr.password);
    if (res == 1 && lista->nusers <= MAX_CLI)
    {
        pthread_mutex_lock(lista->m);
        lista->users[lista->nusers] = usr;
        lista->nusers++;
        pthread_mutex_unlock(lista->m);
        return res;
    }
    

    return 0;
}

void readItems(char *fich, pNode lista)
{
    FILE *f;
    int id, duracao, valor_atual, compre_ja;
    char nome_item[TAM_STR], licitador[TAM_STR], categoria[TAM_STR], vendedor[TAM_STR];
    Item item;

    if (!access(fich, R_OK))
    {
        f = fopen(fich, "rt");
        if (f == NULL)
        {
            printf("Erro na leitura do ficheiro %s!\n", fich);
            return;
        }

        while ((fscanf(f, "%d %s %s %d %d %d %s %s", &id, nome_item, categoria, &valor_atual, &compre_ja, &duracao, vendedor, licitador) == 8) && lista->nitems <= MAX_ITEMS)
        {
            item.id = id;
            strcpy(item.nome_item, nome_item);
            strcpy(item.categoria, categoria);
            item.valor_atual = valor_atual;
            item.compre_ja = compre_ja;
            item.duracao = duracao;
            strcpy(item.vendedor, vendedor);
            strcpy(item.licitador, licitador);
            pthread_mutex_lock(lista->m);
            lista->items[lista->nitems] = item;
            lista->nitems++;
            lista->idTotal++;
            pthread_mutex_unlock(lista->m);
        }

        fclose(f);
    }
    else
    {
        f = fopen(fich, "wt");
        fclose(f);
        printf("Ficheiro %s criado!\n", fich);
    }

    printf("N itens lidos: %d\n", lista->nitems);
}

void saveItems(pNode lista){
    FILE *f;

    f = fopen(lista->fichitems, "wt");
    if (f == NULL)
    {
        printf("Erro na leitura do ficheiro %s!\n", lista->fichitems);
    }

    for(int i=0; i<=lista->nitems; i++){
        fprintf(f, "%d %s %s %d %d %d %s %s\n", lista->items[i].id, lista->items[i].nome_item, lista->items[i].categoria, 
                                                lista->items[i].valor_atual, lista->items[i].compre_ja, lista->items[i].duracao, 
                                                lista->items[i].vendedor, lista->items[i].licitador);
    }

    fclose(f);  
}

void lePromotores(pNode lista)
{
    char *nf = getenv_promotores();
    if (nf != NULL)
    {
        readPromotores(nf, lista);
        if (lista->nitems == 0)
        {
            printf("Erro a ler items!\n");
            return;
        }
    }
    else
    {
        printf("Defina a variavel do ficheiro dos promotores.\n");
    }
}

void readPromotores(char *fich, pNode lista)
{
    FILE *f;
    char nome_prom[TAM_STR];;
    Promotor promotor;

    if (!access(fich, R_OK))
    {
        f = fopen(fich, "rt");
        if (f == NULL)
        {
            printf("Erro na leitura do ficheiro %s!\n", fich);
            return;
        }

        while ((fscanf(f, "%s", nome_prom) == 1) && lista->nprom <= MAX_PROM)
        {
            sprintf(promotor.nome_prom, "./%s", nome_prom);
            pthread_mutex_lock(lista->m);
            lista->promotores[lista->nprom] = promotor;
            lista->nprom++;
            pthread_mutex_unlock(lista->m);
        }

        fclose(f);
    }
    else
    {
        f = fopen(fich, "wt");
        fclose(f);
        printf("Ficheiro %s criado!\n", fich);
    }

    printf("N promotores lidos: %d\n", lista->nprom);
}

void listItems(Node lista)
{
    if (lista.nitems > 0)
    {
        for (int i = 0; i <= lista.nitems; i++)
        {
            printf("Item: %d %s %s %d %d %d %s %s\n", lista.items[i].id, lista.items[i].nome_item, lista.items[i].categoria, lista.items[i].valor_atual,
                   lista.items[i].compre_ja, lista.items[i].duracao, lista.items[i].vendedor, lista.items[i].licitador);
        }
    }
    else
    {
        printf("Lista vazia!\n");
    }
}

void listProms(Node lista){
    if (lista.nprom > 0)
    {
        for (int i = 0; i < lista.nprom; i++)
        {
            printf("Promotor %s\n", lista.promotores[i].nome_prom);
        }
    }
    else
    {
        printf("Nao existem promotores ativos!\n");
    }
}

void listUsers(Node lista)
{
    if (lista.nusers > 0)
    {
        for (int i = 0; i < lista.nusers; i++)
        {
            printf("User %d: %s com a vida %d\n", i, lista.users[i].nome, lista.users[i].vida);
        }
    }
    else
    {
        printf("Nao existe utilizadores ligados!\n");
    }
}

void resetVida(User user, pNode lista)
{
    if (lista->nusers > 0)
    {
        for (int i = 0; i < lista->nusers; i++)
        {
            if (lista->users[i].pid == user.pid)
            {
                pthread_mutex_lock(lista->m);
                lista->users[i].vida = lista->vidaValida;
                pthread_mutex_unlock(lista->m);
            }
        }
    }
}

void *sinalVida(void *lista)
{
    Node *plista = (Node *)lista;
    union sigval valores;

    do
    {
        if (plista->nusers > 0)
        {
            for (int i = 0; i < plista->nusers; i++)
            {
                if (plista->users[i].vida <= 0)
                {
                    sigqueue(plista->users[i].pid, SIGUSR1, valores);
                    resetUserArray(lista, i);
                }
                else
                {
                    pthread_mutex_lock(plista->m);
                    plista->users[i].vida--;
                    pthread_mutex_unlock(plista->m);
                }
            }
        }
        sleep(1);
    } while (plista->stop);

    printf("A sair thread sinalVida\n");
    pthread_exit(NULL);
}

void *tempoItems(void *lista)
{
    Node *plista = (Node *)lista;
    char fifoCli[TAM_STR];
    Msg msg;
    msg.op = 2;

    do
    {
        if (plista->nitems > 0)
        {
            for (int i = 0; i <= plista->nitems; i++)
            {
                if (plista->items[i].duracao <= 0)
                {
                    if(plista->items[i].id >=1 ){
                        for (int j = 0; j < plista->nusers; j++)
                        {
                            sprintf(fifoCli, FIFO_CLI_INIT, plista->users[j].pid);
                            sprintf(msg.frase, "VENDIDO %s", plista->items[i].nome_item);
                            int fd_cli = open(fifoCli, O_WRONLY);
                            write(fd_cli, &msg, sizeof(Msg));
                            close(fd_cli);
                        }
                    }
                    resetItemArray(lista, i);
                }
                else
                {
                    pthread_mutex_lock(plista->m);
                    plista->items[i].duracao--;
                    pthread_mutex_unlock(plista->m);
                }
            }
        }
        sleep(1);
    } while (plista->stop);

    printf("A sair thread tempoItems\n");
    pthread_exit(NULL);
}

void *execComandosCliente(void *lista)
{
    Node *plista = (Node *)lista;
    User comando;
    Msg msg;
    char fifoCli[TAM_STR];

    int fd = open(FIFO_SERVER_CMD, O_RDWR);

    if (fd != -1)
    {
        do
        {
            int size = read(fd, &comando, sizeof(User));
            if (size > 0)
            {
                resetVida(comando, plista);
            }
            sprintf(fifoCli, FIFO_CLI_INIT, comando.pid);

            if (strcmp(comando.cmd, "sell") == 0)
            {
                msg.op = 2;
                if (plista->nitems <= MAX_ITEMS)
                {
                    execSell(comando, plista);
                    strcpy(msg.frase, "O teu comando foi aceite!\n");
                }
                else
                {
                    strcpy(msg.frase, "O teu comando nao foi aceite!\n");
                }

                int fd_cli = open(fifoCli, O_WRONLY);
                write(fd_cli, &msg, sizeof(Msg));
                close(fd_cli);
            }
            else if (strcmp(comando.cmd, "list") == 0)
            {
                msg.op = 2;
                for (int i = 0; i <= plista->nitems; i++)
                {
                    sprintf(msg.frase, "%s", plista->items[i].nome_item);

                    int fd_cli = open(fifoCli, O_WRONLY);
                    write(fd_cli, &msg, sizeof(Msg));
                    close(fd_cli);
                }
            }
            else if (strcmp(comando.cmd, "licat") == 0)
            {
                msg.op = 2;
                for (int i = 0; i <= plista->nitems; i++)
                {
                    if (strcmp(plista->items[i].categoria, comando.args) == 0)
                    {
                        sprintf(msg.frase, "%s", plista->items[i].nome_item);

                        int fd_cli = open(fifoCli, O_WRONLY);
                        write(fd_cli, &msg, sizeof(Msg));
                        close(fd_cli);
                    }
                }
            }
            else if (strcmp(comando.cmd, "lival") == 0)
            {
                msg.op = 2;
                for (int i = 0; i <= plista->nitems; i++)
                {
                    if (plista->items[i].valor_atual <= atoi(comando.args))
                    {
                        sprintf(msg.frase, "%s", plista->items[i].nome_item);

                        int fd_cli = open(fifoCli, O_WRONLY);
                        write(fd_cli, &msg, sizeof(Msg));
                        close(fd_cli);
                    }
                }
            }
            else if (strcmp(comando.cmd, "lisel") == 0)
            {
                msg.op = 2;
                for (int i = 0; i <= plista->nitems; i++)
                {
                    if (strcmp(plista->items[i].vendedor, comando.args) == 0)
                    {
                        sprintf(msg.frase, "%s", plista->items[i].nome_item);

                        int fd_cli = open(fifoCli, O_WRONLY);
                        write(fd_cli, &msg, sizeof(Msg));
                        close(fd_cli);
                    }
                }
            }
            else if (strcmp(comando.cmd, "litime") == 0)
            {
                msg.op = 2;
                for (int i = 0; i <= plista->nitems; i++)
                {
                    if (plista->items[i].duracao <= atoi(comando.args))
                    {
                        sprintf(msg.frase, "%s", plista->items[i].nome_item);

                        int fd_cli = open(fifoCli, O_WRONLY);
                        write(fd_cli, &msg, sizeof(Msg));
                        close(fd_cli);
                    }
                }
            }
            else if (strcmp(comando.cmd, "buy") == 0)
            {
                int idDum, valorDum;

                char *token = strtok(comando.args, ",");
                idDum = atoi(token);

                do
                {
                    valorDum = atoi(token);
                    token = strtok(NULL, ",");
                } while (token != NULL);

                msg.op = 2;

                for (int i = 0; i <= plista->nitems; i++)
                {
                    if (idDum == plista->items[i].id)
                    {   
                        int b = getUserBalance(comando.nome);
                        if (b > plista->items[i].valor_atual && valorDum > plista->items[i].valor_atual && b > valorDum)
                        {
                            strcpy(plista->items[i].licitador, comando.nome);
                            plista->items[i].valor_atual = valorDum;
                            updateUserBalance(plista->users[i].nome, -plista->items[i].valor_atual);
                            sprintf(msg.frase, "Aceite %s", plista->items[i].nome_item);

                            int fd_cli = open(fifoCli, O_WRONLY);
                            write(fd_cli, &msg, sizeof(Msg));
                            close(fd_cli);
                        }
                    }
                }
            }
            else if (strcmp(comando.cmd, "cash") == 0)
            {
                msg.op = 2;
                
                sprintf(msg.frase, "Saldo: %d", getUserBalance(comando.nome));
                int fd_cli = open(fifoCli, O_WRONLY);
                write(fd_cli, &msg, sizeof(Msg));
                close(fd_cli);
                
            }
            else if (strcmp(comando.cmd, "add") == 0)
            {
                msg.op = 2;

                int teste=updateUserBalance(comando.nome, getUserBalance(comando.nome)+atoi(comando.args));
                if(teste==1){
                    strcpy(msg.frase, "Saldo adicionado!");
                }
                else if(teste == -1){
                    strcpy(msg.frase, "Falha na adicao de Saldo!");
                }
            
                int fd_cli = open(fifoCli, O_WRONLY);
                write(fd_cli, &msg, sizeof(Msg));
                close(fd_cli);
            }
        } while (plista->stop);
    }

    close(fd);
    printf("A sair thread execComandosCliente\n");
    pthread_exit(NULL);
}

void execSell(User u, pNode lista)
{ // sell 0<nome-item> 1<categoria> 2<preço-base> 3<preço-compre-já> 4<duração> %d %s %s %d %d %d %s %s
    Item item;
    Msg msg;
    int count = 0;
    char fifoCli[TAM_STR];
    msg.op=2;

    char *token = strtok(u.args, ",");
    strcpy(item.nome_item, token);

    do
    {
        if (count == 1)
        {
            strcpy(item.categoria, token);
        }
        else if (count == 2)
        {
            item.valor_atual = atoi(token);
        }
        else if (count == 3)
        {
            item.compre_ja = atoi(token);
        }
        else if (count == 4)
        {
            item.duracao = atoi(token);
        }
        token = strtok(NULL, ",");
        count++;
    } while (token != NULL);

    strcpy(item.vendedor, u.nome);
    strcpy(item.licitador, "-");

    pthread_mutex_lock(lista->m);
    item.id = lista->idTotal;
    lista->nitems++;
    lista->idTotal++;
    lista->items[lista->nitems] = item;
    pthread_mutex_unlock(lista->m);

    for (int i = 0; i < lista->nusers; i++)
    {
        sprintf(fifoCli, FIFO_CLI_INIT, lista->users[i].pid);
        sprintf(msg.frase, "VENDA %s", item.nome_item);
        int fd_cli = open(fifoCli, O_WRONLY);
        write(fd_cli, &msg, sizeof(Msg));
        close(fd_cli);
    }
}

void *recebeClientes(void *lista1)
{
    Node *plista1 = (Node *)lista1;
    User usr;
    int res;

    int fd_leitura = open(FIFO_SERVER, O_RDWR);

    do
    {
        if (fd_leitura == -1)
        {
            printf("Erro abrir o pipe.\n");
            exit(-1);
        }

        int size = read(fd_leitura, &usr, sizeof(User));

        if (size > 0)
        {
            res=validaUtilizadores(plista1, usr);
        }
        else
        {
            printf("[SERVER] Nao recebi nada.\n");
        }
        sprintf(FIFO_CLI, FIFO_CLI_INIT, usr.pid);
        int fd_cliente = open(FIFO_CLI, O_WRONLY);
        int s = write(fd_cliente, &res, sizeof(res));
        if (s > 0)
            printf("\n[Server] Enviei %d ao cliente.\n", res);
        close(fd_cliente);
    } while (plista1->stop);

    close(fd_leitura);
    printf("A sair thread recebeClientes\n");
    pthread_exit(NULL);
}

void lancaProms(pNode lista){
    for(int i=0; i<lista->nprom; i++){
        if (pthread_create(&threadPromotores[i], NULL, &setUpPromotor, lista) != 0)
        {
            return;
        }
    }
}

void aplicaProm(pNode lista, char msg[TAM_STR]){
    int count=0;
    char *token = strtok(msg, " ");
    strcpy(lista->promotores[lista->promsLancados].categoria, token);

    do
    {
        if (count == 1)
        {
            lista->promotores[lista->promsLancados].desconto=(int)atoi(token)/100;
        }
        else if (count == 2)
        {
            lista->promotores[lista->promsLancados].tempoAtivo=atoi(token);
        }

        token = strtok(NULL, " ");
        count++;
    } while (token != NULL);

    for(int i=0; i<= lista->nitems; i++){
        if(strcmp(lista->items[i].categoria, lista->promotores[lista->promsLancados].categoria)==0){
            lista->items[i].valor_atual*=lista->promotores[lista->promsLancados].desconto;
        }
    }
}

void *setUpPromotor(void *lista)
{
    int filho, fd[2];
    Node *plista = (Node *)lista;
    char fifoCli[TAM_STR];
    Msg msg;
    msg.op=1;

    if (pipe(fd) != 0)
    {
        printf("Error na criacao do pipe anonimo!\n");
        pthread_exit(NULL);
    }

    plista->promotores[plista->nprom].pid = filho = fork();
        
    if (filho < 0)
    {
        printf("Error na criacao do fork : %d\n", filho);
        close(fd[0]);
        close(fd[1]);
        pthread_exit(NULL);
    }
    else if (filho == 0)
    { // filho
        close(1);
        dup(fd[1]);
        close(fd[1]);
        close(fd[0]);
        execl(plista->promotores[plista->promsLancados].nome_prom, plista->promotores[plista->promsLancados].nome_prom, NULL);
        printf("Erro ao executar promotor!\n");
        pthread_mutex_lock(plista->m);
        plista->promsLancados--;   
        pthread_mutex_unlock(plista->m);
        pthread_exit(NULL);
    }
    else if (filho > 0)
    { // pai

        close(fd[1]);

        pthread_mutex_lock(plista->m);
        plista->promsLancados++;   
        pthread_mutex_unlock(plista->m);

        do
        {
            int s = read(fd[0], msg.prom, 99);
            msg.prom[s] = '\0';

            if (s > 0)
            {
                printf("\nPromocao: %s", msg.prom);

                for (int j=0; j<=plista->nusers; j++){
                    sprintf(fifoCli, FIFO_CLI_INIT, plista->users[j].pid);
                    int fdCli = open(fifoCli, O_WRONLY);
                    write(fdCli, &msg, sizeof(Msg));
                    close(fdCli);
                }

                pthread_mutex_lock(plista->m);
                aplicaProm(plista, msg.prom);
                pthread_mutex_unlock(plista->m);
            }

            union sigval sa;
            sigqueue(filho, SIGUSR1, sa);

            break;
        } while (1);

        wait(&filho);
    }

    resetPromArray(lista, plista->promsLancados);

    printf("A sair thread setUpPromotor\n");
    pthread_exit(NULL);
}

void sair(int sign, siginfo_t *i, void *secret)
{
    unlink(FIFO_SERVER);
    unlink(FIFO_SERVER_CMD);
}

void terminar(Node lista)
{
    union sigval valores;

    for (int i = 0; i < lista.nusers; i++)
    {
        sigqueue(lista.users[i].pid, SIGUSR1, valores);
    }

    unlink(FIFO_SERVER);
    unlink(FIFO_SERVER_CMD);
}

void acorda(int s, siginfo_t *info, void *c) {}

int main(int argc, char **argv)
{
    char linha[TAM_STR], *comando = NULL, *token = NULL;
    union sigval valores;
    struct sigaction sa;
    struct sigaction act;
    sa.sa_sigaction = sair;
    act.sa_sigaction = acorda;
    sa.sa_flags = SA_SIGINFO;
    sa.sa_flags = SA_SIGINFO | SA_RESTART;
    sigaction(SIGUSR2, &act, NULL);
    sigaction(SIGINT, &sa, NULL);

    Node lista;
    lista.nusers = 0;
    lista.stop = 1;
    lista.nitems = 0;
    lista.idTotal = 1;
    lista.nprom=0;
    lista.promsLancados=0;

    pthread_mutex_t mutex;
    pthread_mutex_init(&mutex, NULL);
    lista.m = &mutex;

    if (mkfifo(FIFO_SERVER, 0666) == -1)
    {
        if (errno == EEXIST)
        {
            printf("Ja existe um backend a correr!\n");
        }
        printf("Erro abrir fifo.\n");
        return 1;
    }

    if (mkfifo(FIFO_SERVER_CMD, 0666) == -1)
    {
        if (errno == EEXIST)
        {
            printf("Ja existe um FIFO de comaandos!\n");
        }
        printf("Erro abrir fifo.\n");
        return 1;
    }

    leUtilizadores(&lista);
    leItems(&lista);
    lePromotores(&lista);
    lancaProms(&lista);

    int nhb = getenv_heartbeat();
    if (nhb != 0)
    {
        lista.vidaValida = nhb;
    }
    else
    {
        lista.vidaValida = 100;
    }

    if (pthread_create(&t[0], NULL, &recebeClientes, &lista) != 0)
    {
        return -1;
    }

    if (pthread_create(&t[1], NULL, &sinalVida, &lista) != 0)
    {
        return -1;
    }

    if (pthread_create(&t[2], NULL, &tempoItems, &lista) != 0)
    {
        return -1;
    }

    if (pthread_create(&threadClientes, NULL, &execComandosCliente, &lista) != 0)
    {
        printf("Erro ao iniciar a thread para atender o clientes \n");
    }

    setbuf(stdout, NULL);

CMD:
    do
    {
        printf("Introduza um comando: ");
        int size = read(0, linha, 49);
        linha[size - 1] = '\0';

        comando = strtok(linha, " ");

        if (comando == NULL)
            goto CMD;
        else if (strcmp(comando, "close") == 0)
        {
            break;
        }
        else if (strcmp(comando, "users") == 0)
        { // Listar utilizadores cliente atualmente a usar a plataforma
            token = strtok(NULL, " ");
            if (token == NULL)
            {
                listUsers(lista);
            }
            else
            {
                printf("Insira apenas o comando.\n");
            }
        }
        else if (strcmp(comando, "list") == 0)
        { // Listar itens à venda
            token = strtok(NULL, " ");
            if (token == NULL)
            {
                listItems(lista);
            }
            else
            {
                printf("Insira apenas o comando.\n");
            }
        }
        else if (strcmp(comando, "kick") == 0)
        { // kick <username>
            token = strtok(NULL, " ");
            if (token != NULL)
            {
                if (atoi(token) != 0)
                {
                    printf("Nome invalido!\n");
                }
                else
                {
                    for (int i = 0; i < lista.nusers; i++)
                    { // tirar o user do array
                        if (strcmp(lista.users[i].nome, token) == 0)
                        {
                            sigqueue(lista.users[i].pid, SIGUSR1, valores);
                            resetUserArray(&lista, i);
                        }
                    }
                }
            }
            else
            {
                printf("Insira o nome de um vendedor.\n");
            }
        }
        else if (strcmp(comando, "prom") == 0)
        { // Listar os promotores atualmente ativos
            token = strtok(NULL, " ");
            if (token == NULL)
            {
                listProms(lista);
            }
            else
            {
                printf("Insira apenas o comando.\n");
            }
        }
        else if (strcmp(comando, "reprom") == 0)
        { // Atualizar promotores
            token = strtok(NULL, " ");
            if (token == NULL)
            {
                printf("List por implementar...\n");
            }
            else
            {
                printf("Insira apenas o comando.\n");
            }
        }
        else if (strcmp(comando, "cancel") == 0)
        { // cancel <nome-do-executável-do-promotor>
            token = strtok(NULL, " ");
            if (token != NULL)
            {
                if (atoi(token) != 0)
                {
                    printf("Nome do executavel invalido!\n");
                }
                else
                {
                    printf("Promotor %s cancelado.\n", token);
                }
            }
            else
            {
                printf("Insira o nome de um executavel.\n");
            }
        }
        else
        {
            printf("Comando nao encontrado!\n");
        }

    } while (strcmp(comando, "close") != 0);

    if(saveUsersFile(lista.fichuser)==0)
        printf("Utilizadores gravados!\n");

    saveItems(&lista);

    lista.stop = 0;

    terminar(lista);

    pthread_kill(t[0], SIGUSR2);
    pthread_kill(t[1], SIGUSR2);
    pthread_kill(threadClientes, SIGUSR2);

    pthread_join(t[0], NULL);
    pthread_join(t[1], NULL);
    pthread_join(t[2], NULL);
    pthread_join(threadClientes, NULL);

    pthread_mutex_destroy(&mutex);

    return 1;
}
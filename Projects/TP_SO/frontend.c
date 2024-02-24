#include "frontend.h"
#include "env.h"
#include <string.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#include <fcntl.h>
#include <sys/stat.h>

char FIFO_CLI[100];

int validaUser(User user)
{
    int fd_envio, fd_recebe;
    int res;

    fd_envio = open(FIFO_SERVER, O_WRONLY);
    if (fd_envio == -1)
    {
        return fd_envio;
    }
    write(fd_envio, &user, sizeof(User));
    close(fd_envio);

    fd_recebe = open(FIFO_CLI, O_RDONLY);
    int size = read(fd_recebe, &res, sizeof(res));
    close(fd_recebe);

    if (size > 0)
    {
        printf("A retornar %d...\n", res);
        return res;
    }

    return 0;
}

void sair(int sign, siginfo_t *i, void *secret)
{
    terminar();
    printf("Expulso pelo servidor!\n");
    exit(1);
}

void terminar()
{
    unlink(FIFO_CLI);
}

int main(int argc, char **argv)
{
    int nfd, testUser;
    char linha[TAM_STR], *comando = NULL, *token = NULL;
    fd_set read_fds;
    Msg m;
    User user;
    struct sigaction sa;
    sa.sa_sigaction = sair;
    sa.sa_flags = SA_SIGINFO;
    sigaction(SIGUSR1, &sa, NULL);
    sigaction(SIGINT, &sa, NULL);

    setbuf(stdout, NULL);

    if (argc != 3)
    {
        printf("Login falhou!\n");
        return -1;
    }

    sprintf(FIFO_CLI, FIFO_CLI_INIT, getpid());

    if (mkfifo(FIFO_CLI, 0666) == -1)
    { // abrir fifo do cliente para leitura
        printf("Erro a abrir fifo.\n");
        return -1;
    }

    strcpy(user.nome, argv[1]);
    strcpy(user.password, argv[2]);
    user.pid = getpid();
    int nhb = getenv_heartbeat();
    if (nhb != 0)
    {
        user.vida = nhb;
    }
    else
    {
        user.vida = 100;
    }

    testUser = validaUser(user);
    if (testUser==0)
    {
        printf("Utilizador invalido!\n");
        terminar();
        return -1;
    }
    else if (testUser == -1)
    {
        printf("Servidor fechado!\n");
        terminar();
        return -1;
    }

    int fd_leitura = open(FIFO_CLI, O_RDWR);
    if (fd_leitura == -1)
    {
        printf("Erro ao abrir o pipe do Cliente!\n");
        return -1;
    }

CMD:
    do
    {
        printf("Introduza um comando: ");
        FD_ZERO(&read_fds);
        FD_SET(0, &read_fds);
        FD_SET(fd_leitura, &read_fds);
        nfd = select(fd_leitura + 1, &read_fds, NULL, NULL, NULL);

        if (nfd == -1)
        {
            printf("Erro ao criar select!\n");
            close(fd_leitura);
            unlink(FIFO_CLI);
            return 1;
        }

        if (FD_ISSET(0, &read_fds))
        {
            int size = read(0, linha, 49);
            linha[size - 1] = '\0';

            strcpy(user.cmd, "");
            strcpy(user.args, "");

            comando = strtok(linha, " ");

            if (comando == NULL)
                goto CMD;
            else if (strcmp(comando, "exit") == 0)
            {
                close(fd_leitura);
                unlink(FIFO_CLI);
                return 1;
            }
            else if (strcmp(comando, "sell") == 0)
            { // sell 0<nome-item> 1<categoria> 2<preço-base> 3<preço-compre-já> 4<duração>
                int count = 0;
                char dum[20];
                strcpy(user.cmd, "sell");
                token = strtok(NULL, " ");

                if (token != NULL)
                {
                    for (; count < 5 && token != NULL; count++)
                    {
                        if (token != NULL)
                        {
                            if (count == 2 || count == 3 || count == 4)
                            {
                                if (atoi(token) == 0)
                                {
                                    break;
                                }
                            }
                            else
                            {
                                if (atoi(token) != 0)
                                {
                                    break;
                                }
                            }
                        }

                        if (count == 0)
                            strcat(user.args, token);
                        else
                        {
                            sprintf(dum, ",%s", token);
                            strcat(user.args, dum);
                        }

                        token = strtok(NULL, " ");
                    }
                    if (count == 5)
                    {
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                }
                if (count < 5)
                {
                    printf("Erro no argumento %d!\n", count + 1);
                }
            }
            else if (strcmp(comando, "list") == 0)
            { // Listar todos os itens
                strcpy(user.cmd, "list");
                token = strtok(NULL, " ");
                if (token == NULL)
                {
                    int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                    int size = write(fd_escrita, &user, sizeof(User));
                    if (size > 0)
                    {
                        printf("Enviei o comando!\n");
                    }
                    close(fd_escrita);
                }
                else
                {
                    printf("Insira apenas o comando.\n");
                }
            }
            else if (strcmp(comando, "licat") == 0)
            { // licat <nome-categoria>
                strcpy(user.cmd, "licat");
                token = strtok(NULL, " ");
                if (token != NULL)
                {
                    if (atoi(token) == 0)
                    {
                        strcpy(user.args, token);
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                    else
                    {
                        printf("Categoria inválida!\n");
                    }
                }
                else
                {
                    printf("Insira uma categoria.\n");
                }
            }
            else if (strcmp(comando, "lival") == 0)
            { // lival <preço-máximo>
                strcpy(user.cmd, "lival");
                token = strtok(NULL, " ");
                if (token != NULL)
                {
                    if (atoi(token) != 0)
                    {
                        strcpy(user.args, token);
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                    else
                    {
                        printf("Preco invalido!\n");
                    }
                }
                else
                {
                    printf("Insira um preco.\n");
                }
            }
            else if (strcmp(comando, "lisel") == 0)
            { // lisel <username do vendedor>
                strcpy(user.cmd, "lisel");
                token = strtok(NULL, " ");
                if (token != NULL)
                {
                    if (atoi(token) == 0)
                    {
                        strcpy(user.args, token);
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                    else
                    {
                        printf("Nome invalido!\n");
                    }
                }
                else
                {
                    printf("Insira o nome de um vendedor.\n");
                }
            }
            else if (strcmp(comando, "litime") == 0)
            { // litime <hora-em-segundos>
                strcpy(user.cmd, "litime");
                token = strtok(NULL, " ");
                if (token != NULL)
                {
                    if (atoi(token) != 0)
                    {
                        strcpy(user.args, token);
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                    else
                    {
                        printf("Numero inválido!\n");
                    }
                }
                else
                {
                    printf("Insira um numero.\n");
                }
            }
            else if (strcmp(comando, "time") == 0)
            { // Obter a hora atual (em segundos)
                token = strtok(NULL, " ");
                if (token == NULL)
                {
                    time_t t = time(NULL);
                    struct tm tm = *localtime(&t);
                    printf("%d\n", tm.tm_hour * 3600 + tm.tm_min * 60 + tm.tm_sec);
                }
                else
                {
                    printf("Insira apenas o comando.\n");
                }
            }
            else if (strcmp(comando, "buy") == 0)
            { // buy 0<id> 1<valor>
                int count = 1;
                char *itemLicit = NULL, *valorLicit = NULL;
                strcpy(user.cmd, "buy");
                token = strtok(NULL, " ");

                if (token != NULL)
                {
                    for (; count < 3; count++)
                    {
                        if (token != NULL)
                        {
                            if (atoi(token) != 0)
                            {
                                if (count == 1)
                                {
                                    itemLicit = token;
                                }
                                else
                                {
                                    valorLicit = token;
                                }
                            }
                            else if (atoi(token) == 0)
                            {
                                printf("Valor numerico inválido!\n");
                                break;
                            }
                        }
                        token = strtok(NULL, " ");
                        if (token == NULL && count == 2)
                            printf("Insira o segundo parametro!\n");
                    }

                    if (count == 3 && itemLicit != NULL && valorLicit != NULL)
                    {
                        printf("Licitacao no item %s de %s$.\n", itemLicit, valorLicit);
                        char d[100];
                        sprintf(d, "%s,%s", itemLicit, valorLicit);
                        strcpy(user.args, d);
                        int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                        int size = write(fd_escrita, &user, sizeof(User));
                        if (size > 0)
                        {
                            printf("Enviei o comando!\n");
                        }
                        close(fd_escrita);
                    }
                }
                else
                {
                    printf("Insira os parametros!\n");
                }
            }
            else if (strcmp(comando, "cash") == 0)
            { // Consultar o saldo
                strcpy(user.cmd, "cash");
                token = strtok(NULL, " ");
                if (token == NULL)
                {
                    int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                    int sizeSaldo = write(fd_escrita, &user, sizeof(User));
                    if (sizeSaldo > 0)
                    {
                        printf("Enviei o comando!\n");
                    }
                    close(fd_escrita);
                }
                else
                {
                    printf("Insira apenas o comando.\n");
                }
            }
            else if (strcmp(comando, "add") == 0)
            { // add <valor>
                strcpy(user.cmd, "add");
                token = strtok(NULL, " ");
                if (token != NULL)
                {
                    strcpy(user.args, token);
                    int fd_escrita = open(FIFO_SERVER_CMD, O_WRONLY);
                    int sizeAdd = write(fd_escrita, &user, sizeof(User));
                    if (sizeAdd > 0)
                    {
                        printf("Comando enviado!\n");
                    }
                }
                else
                {
                    printf("Não foi adicionado nada ao seu saldo.\n");
                }
            }
            else
            {
                printf("Comando nao encontrado!\n");
            }
        }
        if (FD_ISSET(fd_leitura, &read_fds))
        {
            int xpto = read(fd_leitura, &m, sizeof(Msg));
            if (xpto > 0)
            {
                switch (m.op)
                {
                case 1:
                    if (xpto > 0)
                    {
                        printf("%s\n", m.prom);
                    }
                    break;
                case 2:
                    if (xpto > 0)
                    {
                        printf("%s\n", m.frase);
                    }
                    break;
                default:
                    break;
                }
            }
        }
    } while (1);
}

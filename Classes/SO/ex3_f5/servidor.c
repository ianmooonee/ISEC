#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include"geral.h"

#define FIFO_CLI_INIT "FIFOCLI%d"
char FIFO_CLI[100];

void sair(){
    unlink(FIFO_SERVER);
    exit(1);
}

int main(int argc, char** argv){
    Msg m;
    Res r;
    int fd_envio, fd_recebe, nfd;
    fd_set read_fds;
    char str[100];
    //struct timeval tv;
    setbuf(stdout, NULL);

    if (mkfifo(FIFO_SERVER,0666) == -1) {
       if (errno == EEXIST){
           printf ("Ja existe um backend a correr!\n");
       }
       printf("Erro abrir fifo.\n");
       return 1;
   }

    int fd_leitura = open(FIFO_SERVER,O_RDWR);

    do{
        printf("Introduza um comando: ");
        FD_ZERO(&read_fds);
        FD_SET(0,&read_fds);
        FD_SET(fd_leitura,&read_fds);
        nfd = select(fd_leitura+1,&read_fds,NULL,NULL,NULL);

        if(FD_ISSET(0,&read_fds)){
            printf("Introduza um comando: ");
            scanf(" %s", str);

            if(strcmp(str,"sair")==0){
                sair();
            }
            else if(strcmp(str,"teste")==0){
                printf("Comando de teste!\n");
            }
        }

        if(FD_ISSET(fd_leitura,&read_fds)){
            read(fd_leitura, &m, sizeof(Msg));

            if(m.op=='s'){
                unlink(FIFO_SERVER);
                exit(1);
            }
            else if(m.op=='+'){
                r.res=m.n1+m.n2;
            }
            

            sprintf(FIFO_CLI, FIFO_CLI_INIT, m.pid);
            int fd_cliente = open(FIFO_CLI,O_WRONLY);
            write (fd_cliente, &r,sizeof(Res));

            printf("[Server] Enviei %d ao cliente.\n", r.res);

            close (fd_cliente);
        } 
    }while(1);

    return 1;
}
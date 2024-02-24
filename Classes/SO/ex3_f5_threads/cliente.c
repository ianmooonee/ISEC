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
#include "geral.h"

#define FIFO_CLI_INIT "FIFOCLI%d"
char FIFO_CLI[100];

int main(int argc, char** argv){
    Msg m;
    Res r;
    int fd_envio, fd_recebe;

    sprintf(FIFO_CLI, FIFO_CLI_INIT, getpid());

    if (mkfifo(FIFO_CLI,0777)==-1){ 
        printf("Erro a abrir fifo.\n");
        return -1;
    }

    m.pid=getpid();

    do{
        printf("Introduza uma operacao: ");
        scanf(" %c", &m.op);

        if(m.op=='q'){
            unlink(FIFO_CLI);
            exit(1);
        }

        printf("Introduza n1: ");
        scanf(" %d", &m.n1);

        printf("Introduza n2: ");
        scanf(" %d", &m.n2);

        //código do envio
        fd_envio = open(FIFO_SERVER,O_WRONLY);
        write(fd_envio, &m, sizeof(Msg));
        close(fd_envio);

        //código da receção
        fd_recebe = open (FIFO_CLI,O_RDONLY);
        int size = read(fd_recebe, &r, sizeof(Res));
        close(fd_recebe);

        printf("Resultado: %d.\n", r.res);


    }while(1);

    return 1;
}
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
#include <pthread.h>
#include"geral.h"

#define FIFO_CLI_INIT "FIFOCLI%d"
char FIFO_CLI[100];

void sair(){
    unlink(FIFO_SERVER);
    exit(1);
}

void *leTecla(){
    char str[100];

    do{
        printf("Introduza um comando: ");
        fgets(str, sizeof(str), stdin);

        if(strcmp(str,"sair\n")==0){
            sair();
        }
        else if(strcmp(str,"teste\n")==0){
            printf("Comando de teste!\n");
        }
    }while(1);
}

int main(int argc, char** argv){
    Msg m;
    Res r;
    int fd_envio, fd_recebe;
    pthread_t t[2];
    pthread_mutex_t  mutex;  
    pthread_mutex_init(&mutex, NULL); //inicializar
    m.mut = &mutex;

    setbuf(stdout, NULL);

    if (mkfifo(FIFO_SERVER,0666) == -1) {
       if (errno == EEXIST){
           printf ("Ja existe um backend a correr!\n");
       }
       printf("Erro abrir fifo.\n");
       return 1;
   }

    int fd_leitura = open(FIFO_SERVER,O_RDWR);

    if (pthread_create (&t[0],NULL,&leTecla,&m)!=0){
        return -1;
    }
    
    do{
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
    }while(1);

    pthread_join(t[0], NULL);

    return 1;
}
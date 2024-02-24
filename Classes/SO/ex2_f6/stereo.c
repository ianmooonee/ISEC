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
#define P_ESQ "PESQ"
#define P_DRT "PDRT"

//usar o pthread_kill para dar skip nos reads com um sigusr2

typedef struct{
    int val;
    char nome[100];
}  dataMSG;

typedef struct{
    char fifo[100];
} TDADOS;

void *tarefa(void *dados) {
    TDADOS *pDados = (TDADOS *) dados;
    dataMSG msg;

    int fd=open(pDados->fifo, O_RDWR);

    do{
        read(fd, &msg, sizeof(msg));
        if(strcmp(pDados->fifo, P_DRT)==0){
            printf("Drt - Recebi o valor %d do cliente %s.\n", msg.val, msg.nome);
        }
        else{
            printf("Esq - Recebi o valor %d do cliente %s.\n", msg.val, msg.nome);
        }
        fflush(stdout);
    }while(1);
    close(fd);
	pthread_exit(NULL);
}

void fim(){
    unlink(P_DRT);
    unlink(P_ESQ);
    exit(1);
}

int main(){
    struct sigaction sa;
    sa.sa_sigaction = fim;
    sa.sa_flags = SA_SIGINFO;
    sigaction(SIGINT,&sa,NULL);
    pthread_t t[2];
    TDADOS dados[2];
    char cmd[100];

    if (mkfifo(P_ESQ,0666) == -1) {
       if (errno == EEXIST){
           printf ("fifo já existe");
       }
    }

    if (mkfifo(P_DRT, 0666) == -1) {
       if (errno == EEXIST){
           printf ("fifo já existe");
       }
    }

    strcpy(dados[0].fifo, P_DRT);
    strcpy(dados[1].fifo, P_ESQ);

    if(pthread_create(&t[0], NULL, &tarefa, &dados[0]) != 0) {
		return -1;
	}
    if(pthread_create(&t[1], NULL, &tarefa, &dados[1]) != 0) {
		return -1;
	}

    do{
        printf("Comando: ");
        scanf(" %s", cmd);

        if(strcmp(cmd,"sair")==0){
            break;
        }
    }while(1);

    pthread_join(t[0], NULL);
    pthread_join(t[1], NULL);

	return 0;
}
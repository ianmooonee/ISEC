
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/stat.h>
#include <signal.h>
#include <errno.h>
#define ECHO_FIFO  "FIFO1ECHO"
#define ECHO_CLIENTE  "FIFO1PRODUTOR%d"
char ECHO_CLIENTE_FINAL[100];

typedef struct {
    pid_t pid;
    char msg[100];
}  dataMSG;

void fim (int sign,siginfo_t *i,void *secret){
    unlink (ECHO_FIFO);
    exit (1);  
}


int main(){
    struct sigaction sa;
        sa.sa_sigaction = fim;
        sa.sa_flags = SA_SIGINFO;
    sigaction(SIGINT,&sa,NULL);
    dataMSG msg;
     if (mkfifo(ECHO_FIFO,0666) == -1)  {
       if (errno == EEXIST){
           printf ("fifo já existe");
       }
       printf("Erro abrir fifo\n");
       return 1;
   }
    int fd_echo_fifo = open(ECHO_FIFO,O_RDONLY);
   // int fd_echo_fifo = open(ECHO_FIFO,O_RDWR);
    if (fd_echo_fifo == -1){
        printf("Erro abrir o servidor");
        return 1;
    }

    while (1){
        int size = read(fd_echo_fifo,&msg,sizeof(msg));
        if (size != 0){
            if (strcmp("sair\n",msg.msg)==0){
              unlink (ECHO_FIFO);
              return 1;
            }
            printf("\n Msg:[%s]  Pid_Origem:[%d]",msg.msg,msg.pid);
           

            //resposta ao echo
            sprintf(ECHO_CLIENTE_FINAL,ECHO_CLIENTE,msg.pid);
            int fd_cliente = open(ECHO_CLIENTE_FINAL,O_WRONLY);
            //strcpy (msg.msg,"cheguei");
            msg.pid = getpid();
            int s = write (fd_cliente,&msg,sizeof(msg));
            close (fd_cliente);
        }
    }
    unlink(ECHO_FIFO);
}

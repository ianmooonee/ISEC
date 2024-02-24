
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
#define ECHO_CLIENTE  "FIFO1PRODUTOR%d" // FIFO1PRODUTOR12345
char ECHO_CLIENTE_FINAL[100];

typedef struct {
    pid_t pid;
    char msg[100];
}  dataMSG;

int main(){
    dataMSG msg,msgResposta;
    msg.pid = getpid();    
    char envio[100];
    int fd_envio, fd_recebe;
   sprintf(ECHO_CLIENTE_FINAL,ECHO_CLIENTE,getpid());
   //receber do echo
   if (mkfifo(ECHO_CLIENTE_FINAL,0777)==-1){
        printf("Erro abrir fifo");
        return 1;
   }

    do{
        printf("O que pretende enviar:\n");
        fgets(msg.msg,99,stdin);
        //envio para o echo
        fd_envio = open (ECHO_FIFO,O_WRONLY);
        write(fd_envio,&msg,sizeof(envio));
        close(fd_envio);

        if (strcmp(envio,"terminar\n")==0){
            unlink(ECHO_CLIENTE_FINAL);
            exit(1);
        }
        //recebe do echo
        fd_recebe = open (ECHO_CLIENTE_FINAL,O_RDONLY);
         // int fd_echo_fifo = open(ECHO_FIFO,O_RDWR);
            int size3 = read(fd_recebe,&msgResposta,sizeof(msgResposta));
        close(fd_recebe);

        printf("Resposta eco [%s %d  %d] [%s ]",ECHO_CLIENTE_FINAL,size3,msgResposta.pid, msgResposta.msg);
        if (strcmp(msgResposta.msg,"sair\n")==0){
            unlink(ECHO_CLIENTE_FINAL);
            exit(1);
        }

    }while(1);

}

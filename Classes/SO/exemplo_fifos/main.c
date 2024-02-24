#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>
#include <errno.h>
#define FIFO "FIFO1"

int main(){
    char msg[100];

    if(mkfifo(FIFO, 0666) == -1){
        if(errno==EEXIST){
            printf("FIFO ja existe.\n");
        }
        printf("Erro a abrir fifo.\n");
    }

    int fd=open(FIFO, O_RDWR);

    if(fd==-1){ //se abrirmos o fifo no cliente e ele não funcionar é porque o backend não está ligado
        printf("Erro a abrir fifo.\n");
        return -1;
    }

    int size=read(fd, msg, sizeof(msg));
    msg[size-1]='\0';

    if(size>0){
        printf("Mensagem: %s - %d\n", msg, size);
    }

    close(fd);
    unlink(FIFO);

    return 0;
}
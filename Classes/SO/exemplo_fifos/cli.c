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
    int fd=open(FIFO, O_WRONLY);
    char msg[100]="Ola\n";

    int size=write(fd, msg, strlen(msg));
    printf("Mesagem enviada: %s - %d!\n", msg, size);;

    close(fd);

    return 0;
}
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

typedef struct{
    int val;
    char nome[100];
}  dataMSG;

int main(int argc, char **argv){
    dataMSG msg;

    printf("Introduza o nome: ");
    scanf("%s", msg.nome);

    do{
        printf("Introduza um valor: ");
        scanf("%d", &msg.val);

        int fd = open(argv[1], O_WRONLY);
        int size=write(fd, &msg, sizeof(msg));
        close(fd);

    }while(1);

	return 0;
}
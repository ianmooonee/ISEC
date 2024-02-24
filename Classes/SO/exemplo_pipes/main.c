#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

int main() {
    int fd_fp[2], i;
    scanf("%d", &i);

    pipe(fd_fp);
    scanf("%d", &i);

    int p=fork();
    if(p==0){ //filho
        char str[100];
        close(fd_fp[1]);
        int size= read(fd_fp[0], str, sizeof(str));
        printf("[filho] recebeu %s com %d tamanho\n", str, size);
    }else if(p>0){
        close(fd_fp[0]);
        scanf("%d", &i);
        int size=write(fd_fp[1], "ola", 4);
    }
    return 0;
}

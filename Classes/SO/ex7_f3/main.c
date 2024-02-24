#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main() {
    char op;
    char palavra[20]="";
    int filho, fd[2];

    printf("x - sair; i - ingles; f - frances: ");
    scanf(" %c", &op);
    printf("Introduza a palavra: ");
    scanf("%s", palavra);
    pipe(fd);

    if(op=='x')
        return 1;

    if(op=='i'){
        filho=fork();
        if(filho==0){ //0-stdin    1-stdout   2-stderr
            close(0);
            dup(fd[0]);
            close(fd[0]);
            close(fd[1]);
            execl("./rding", "./rding", NULL);
        }else if(filho>0){
            close(fd[0]);
            write(fd[1], palavra, strlen(palavra)+1);
        }

    }else if(strcmp(&op, "f")==0){
        filho=fork();
        if(filho==0){
            execl("./fr", "./fr", palavra, NULL);
        }
        wait(&filho);
    }

    return 0;
}
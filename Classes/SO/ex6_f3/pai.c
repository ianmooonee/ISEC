#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdio.h>

int main(int argc, char **argv) {
    char op;
    char palavra[20]="";
    int filho;

    do{
        printf("x - sair; i - ingles; f - frances: ");
        scanf(" %c", &op);

        if(op=='x')
            return 1;

        printf("Introduza a palavra para traduzir: ");
        scanf("%s", palavra);

        if(op=='i'){
            filho=fork();
            if(filho==0){
                execl("./ing", "./ing", palavra, NULL);
            }
            wait(&filho);
        }
        else if(strcmp(&op, "f")==0){
            filho=fork();
            if(filho==0){
                execl("./fr", "./fr", palavra, NULL);
            }
            wait(&filho);
        }
    }while(op!='x');

    return 0;
}
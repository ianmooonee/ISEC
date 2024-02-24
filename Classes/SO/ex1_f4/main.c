#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
int counter=0;

void funcSignal(int sign){
    if(++counter==5){
        printf("Ok, pronto!\n");
        exit(1);
    }
    else
        printf("\nai!");
}

int main(){
    char nome[40];
    struct sigaction sa;
    sa.sa_handler=funcSignal;
    sa.sa_flags=SA_SIGINFO;
    sigaction(SIGINT, &sa, NULL);

    do{
        printf("Introduza o seu nome: ");
        scanf(" %s", nome);        //proteger o scanf
        printf("Ola %s!\n", nome);
    }while(strcmp(nome, "sair")!=0);
    
    return 1;
}
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <time.h>
int n=10;
int pontuacao=0;
int perdeu=0;


void funcSignal(int sign){
    
}

void jogo(){
    srand(time(NULL));
    int random1=rand() % 11, random2=rand() % 11;
    int soma=random1+random2;
    int guess;
    n--;

    printf("Introduza o valor da soma de %d+%d: ", random1, random2);
    scanf(" %d", &guess);
    if(soma==guess){
        pontuacao++;
        printf("Tem %d pontos.\n", pontuacao);
    }
    else{
        printf("Perdeu.\n");
        if(++perdeu==2){
            printf("Acabaram as tentativas.\n");
            exit(1);
        }
    }

}

int main(){
    int i=0;
    struct sigaction sa;
    sa.sa_flags=SA_RESTART|SA_SIGINFO;
    sa.sa_handler=funcSignal;
    sigaction(SIGALRM, &sa, NULL);

    while(1){
        if(i==5)
            exit(1);
        else{
            i++;
            alarm(n);
            jogo();
        }
    };
    
    return 1;
}
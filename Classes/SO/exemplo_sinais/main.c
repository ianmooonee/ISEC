#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void funcSignal(int sign){
    printf("\nRecebi o sinal %d\n", sign);
    exit(1);
}

int main(){
    int i;
    struct sigaction sa;
    sa.sa_handler=funcSignal;
    sa.sa_flags=SA_SIGINFO;

    printf("\nPID: %d\n", getpid());
    sigaction(SIGINT, &sa, NULL);
    scanf("%d", &i);
    
    return 1;
}
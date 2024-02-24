#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void funcSignal(int sign, siginfo_t *info, void *old){
    printf("\nRecebi o sinal %d", sign);
    printf("\nO autor do sinal foi o %d", info->si_pid);
    printf("O valor recebido foi %d", info->si_value.sival_int);
}

int main(){
    int i;
    struct sigaction sa;
    sa.sa_sigaction=funcSignal;
    sa.sa_flags=SA_SIGINFO;

    printf("\nPID: %d\n", getpid());
    sigaction(SIGINT, &sa, NULL);
    scanf("%d", &i);
    
    return 1;
}
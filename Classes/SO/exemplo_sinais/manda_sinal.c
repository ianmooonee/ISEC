#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(){
    int pid;

    printf("\nIntroduza o PID: ");
    scanf("%d", &pid);

    union sigval valores;
    valores.sival_int=321;

    sigqueue(pid, SIGINT, valores);

    return 1;
}
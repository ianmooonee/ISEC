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
#include <pthread.h>
//usar a flag -pthread para compilar

void *imprime(){
    printf("Ola da thread com o pid %d\n",getpid());    
    sleep(5);  
    printf("Adeus da thread\n");
    pthread_exit(NULL);
}

int  main(){
    pthread_t t[2];
    if (pthread_create (&t[0],NULL,&imprime,NULL) != 0)
       return -1;
    if (pthread_create (&t[1],NULL,&imprime,NULL) != 0)
        return -1;
    pthread_join(t[0], NULL);
    pthread_join(t[1], NULL);
}
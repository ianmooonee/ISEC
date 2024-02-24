
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <sys/select.h>
#include <pthread.h>
#include <signal.h>
int xpto=0;

void acorda(int s, siginfo_t *info, void *c) { }

typedef struct{
 int a;
 int b;
 int stop;
 int contador;
 pthread_mutex_t *m;
} TDADOS;


void *imprime(void * dados){
  TDADOS *pdados = (TDADOS * ) dados;
  for (int k=0;k<20000000;k++){
       
       //inicio de uma zona critica
        pthread_mutex_lock(pdados->m);  
        pdados->contador++; // le da memoria , incrementa localmente e grava na memoria
        pthread_mutex_unlock(pdados->m);
       //fim de uma zona critica
        fflush(stdout);
     
  }    
  //printf("[%d]",pdados->contador);
  pthread_exit(NULL);
}

int  main(){
    int i;
    pthread_t t[2];
    pthread_mutex_t  mutex;  
    pthread_mutex_init(&mutex, NULL); //inicializar
    TDADOS dados;
    dados.m = &mutex;
    dados.contador = 0;

    if (pthread_create (&t[0],NULL,&imprime,&dados)!=0)
     return -1;
       
    if (pthread_create (&t[1],NULL,&imprime,&dados)!=0)
     return -1;
     pthread_join(t[0], NULL);
     pthread_join(t[1], NULL);
     pthread_mutex_destroy(&mutex);
     printf("\n[%d]",dados.contador);
     
}

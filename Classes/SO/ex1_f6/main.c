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
#include <errno.h>
#include <pthread.h>

typedef struct {
	int stop;
	char tipo;
	pthread_mutex_t *m;
} TDADOS;

void imprimir(char letra, int num) {
	for(int i = 0; i < num; i++) {
		printf("%c", letra);
		fflush(stdout);
		sleep(1);
	}
}

void *tarefa(void *dados) {
	TDADOS *pDados = (TDADOS *) dados;
	while(pDados->stop) {
		srand(time(0));
		int t = rand() % (1 - 6);
		imprimir('.', t); // imprimir os pontos
		// mutex lock
		pthread_mutex_lock(pDados->m);
		imprimir(pDados->tipo, 3); // imprimir as letras
		// mutex unlock
		pthread_mutex_unlock(pDados->m);
	}

	pthread_exit(NULL);
}

int main() {
	char buffer[100];
	pthread_t t[2]; // thread A e B
	pthread_mutex_t mx;
	pthread_mutex_init(&mx, NULL);
	
	TDADOS valores[2];
	valores[0].stop = 1;
	valores[0].tipo = 'A';
	valores[0].m = &mx;
	valores[1].stop = 1;
	valores[1].tipo = 'B';
	valores[1].m = &mx;

	if(pthread_create(&t[0], NULL, &tarefa, &valores[0]) != 0) {
		return -1;
	}
	if(pthread_create(&t[1], NULL, &tarefa, &valores[1]) != 0) {
		return -1;
	}
	while(strcmp(buffer, "sair") != 0) {
		scanf(" %s", buffer);
	}

	valores[0].stop = 0;
	valores[1].stop = 0;
	pthread_join(t[0], NULL);
	pthread_join(t[1], NULL);

	sleep(2);

	return 0;
}
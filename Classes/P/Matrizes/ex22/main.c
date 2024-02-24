#include <stdio.h>
#include <string.h>

#define N 5

void escreve_sin(char *sin[][2], int tot_lin){
    for(int i=0; i<tot_lin; i++) {
        for (int j = 0; j < 2; j++) {
            if(j==0){
                printf("%s - ", sin[i][j]);
            }
            else{
                printf("%s ", sin[i][j]);
            }
        }
        putchar('\n');
    }
}

char *pesquisa_sinonimo(char *sin[][2], int tot_lin, char *p){
    for(int i=0; i<tot_lin; i++){
        for(int j=0; j<2; j++){
            if(strcmp(p, sin[i][j])==0){
                if(j==1) {
                    return sin[i][0];
                }
                else{
                    return sin[i][1];
                }
            }
        }
    }
    return NULL;
}

int pesquisa_dup(char *sin[][2], int tot_lin) {
    int count=0;
    char pal[50];

    for(int i=0; i<tot_lin; i++){
        strcpy(pal, sin[i][0]);
        for(int j=i+1; j<tot_lin; j++){
            if(strcmp(pal, sin[j][0])==0 || strcmp(pal, sin[j][1])==0){
                count++;
            }
        }
    }

    for(int i=0; i<tot_lin; i++){
        strcpy(pal, sin[i][1]);
        for(int j=i+1; j<tot_lin; j++){
            if(strcmp(pal, sin[j][0])==0 || strcmp(pal, sin[j][1])==0){
                count++;
            }
        }
    }

    return count;
}

int main(){
    char palavra[50], *p;

    char *s[N][2] = {{"estranho", "bizarro"},
                     {"desconfiar", "suspeitar"},
                     {"vermelho", "encarnado"},
                     {"duvidar", "desconfiar"},
                     {"carro", "automovel"}};


    escreve_sin(s, N);		// alinea 22.1

    printf("Palavra a pesquisar: ");
    scanf(" %s", palavra);

    p = pesquisa_sinonimo(s, N, palavra); // alinea 22.2

    if(p == NULL)
        printf("A palavra %s nao tem sinonimo conhecido\n", palavra);
    else
        printf("A palavra %s e sinonimo de %s\n", p, palavra);

    printf("Repetidas: %d", pesquisa_dup(s, N));

    return 0;
}
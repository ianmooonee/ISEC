#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    char pt[5][10]={"ola", "carro", "mundo", "azul", "ordenado"};
    char ing[5][10]={"hello", "car", "world", "blue", "wage"};

    for(int i=0; i<5; i++){
        if(strcmp(argv[1],pt[i])==0)
            printf("Traducao: %s\n", ing[i]);
    }

    return 0;
}
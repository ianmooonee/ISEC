#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    char pt[5][10]={"ola", "carro", "mundo", "azul", "ordenado"};
    char ing[5][10]={"hello", "car", "world", "blue", "wage"};
    char input[15]="";
    int f;

    scanf("%s", input);

    for(int i=0; i<5; i++){
        if(strcmp(input,pt[i])==0)
            printf("Traducao: %s\n", ing[i]);
    }
    //scanf("%d", &f);
    return 0;
}
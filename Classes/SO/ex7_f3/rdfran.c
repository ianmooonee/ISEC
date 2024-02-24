#include <stdio.h>
#include <string.h>

int main(int argc, char **argv) {
    char pt[5][15]={"ola", "carro", "mundo", "azul", "ordenado"};
    char fr[5][15]={"bonjour", "auto", "monde", "bleue", "un salaire"};
    char input[15]="";

    scanf("%s", input);

    for(int i=0; i<5; i++){
        if(strcmp(input,pt[i])==0)
            printf("Traducao: %s\n", fr[i]);
    }

    return 0;
}
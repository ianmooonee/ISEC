#include <stdio.h>
#include <string.h>

void inverte(char str[]){
    int i;

    for(i=0; str[i]!='\0'; i++){
        ;
    }

    for (int j=i; j>=0; j--){
        printf("%c", str[j]);
    }
}

int main() {
    char str[30]="Ola mundo cruel!";

    inverte(str);

    return 0;
}

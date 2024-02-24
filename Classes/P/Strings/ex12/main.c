#include <stdio.h>
#include <string.h>

void separa(char str[]){
    for(int i=0; str[i]!='\0'; i++){
        if(str[i]==' ' && str[i+1]!=' '){
            printf("\n");
        }
        else{
            printf("%c", str[i]);
        }
    }
}

int main() {
    char str[50]="  Hoje     e    Domingo    ";

    separa(str);

    return 0;
}

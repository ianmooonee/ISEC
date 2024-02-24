#include <stdio.h>
#include <string.h>

void compara(char str1[], char str2[], char str3[]){
    if(strcmp(str1, str2)==0){
        strcpy(str3, "Iguais!");
    }
    else if(strlen(str1)==strlen(str2)){
        strcpy(str3, "Diferentes com o mesmo tamanho!");
    }
    else if(strcmp(str1, str2)<0){
        strcpy(str3, str1);
        strcat(str3, str2);
    }
    else{
        strcpy(str3, str2);
        strcat(str3, str1);
    }
}

int main() {
    char str1[20]="AAA", str2[20]="BBBB", str3[50]="";

    compara(str1, str2, str3);
    printf("%s", str3);

    return 0;
}
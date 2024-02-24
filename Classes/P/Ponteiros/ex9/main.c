#include <stdio.h>
#include <ctype.h>

void f(char *t, char *res){
    int i=0;

    if(isdigit(t[0])==0 || t[i]!='2'){
        *res='I';
        return;
    }

    while(t[i]!='\0'){
        if(isdigit(t[i])==0){
            *res='I';
            return;
        }
        i++;
    }

    *res='V';
}

int main() {
    char tel[10]="239376123", res=' ';

    f(tel, &res);
    printf("Valido ou invalido: %c", res);

    return 0;
}
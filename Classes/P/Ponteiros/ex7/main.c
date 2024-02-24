#include <stdio.h>

void f(const int *tab, int tam, int *mmaior, int *maior){
    if(tab[0]>tab[1]){
        *mmaior=tab[0];
        *maior=tab[1];
    }
    else{
        *mmaior=tab[1];
        *maior=tab[0];
    }

    for(int i=2; i<tam; i++){
        if(tab[i]>*mmaior){
            *maior=*mmaior;
            *mmaior=tab[i];
        }
        else if(tab[i]>*maior){
            *maior=tab[i];
        }
    }
}

int main() {
    int tab[10]={11,3,7,5,2,10,9,7,7,1};
    int mmaior, maior;

    f(tab, 10, &mmaior, &maior);

    printf("Maior: %d\n2 maior: %d\n", mmaior, maior);

    return 0;
}

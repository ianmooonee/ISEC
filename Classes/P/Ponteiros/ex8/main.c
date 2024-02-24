#include <stdio.h>

int f(const int *t, int tam){
    int maior=0, subida, pos;

    for(int i=1; i<tam; i++){
        subida=t[i]-t[i-1];
        if(subida>maior){
            maior=subida;
            pos=i;
        }
    }

    return pos;
}

int main() {
    int tab[10]={1,15,7,5,2,10,9,7,7,1};

    printf("Elemento que regista maior subida: %d", f(tab, 10));

    return 0;
}

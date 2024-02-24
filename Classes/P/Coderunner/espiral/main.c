#include <stdio.h>

void printV(int *v, int tam){
    for(int i = 0; i < tam; i++)
        printf("%d ", v[i]);
}

void espiral(int dim, int mat[][dim], int *v){
    int conta=0, top=0, bottom=dim, left=0, right=dim;

    while(left<right && top<bottom){
        for(int i=left; i<right; i++){
            v[conta]=mat[top][i];
            conta++;
        }
        top++;

        for(int i=top; i<bottom; i++){
            v[conta]=mat[i][right-1];
            conta++;
        }
        right--;

        for(int i=right-1; i>=left; i--){
            v[conta]=mat[bottom-1][i];
            conta++;
        }
        bottom--;

        for(int i=bottom-1; i>=top; i--){
            v[conta]=mat[i][left];
            conta++;
        }
        left++;
    }
}

int main() {
    int mat[4][4] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    int tab[16];
    espiral(4, mat,tab);
    printV(v, 16);

    return 0;
}

#include <stdio.h>

void printMat(int mat[][3], int nLinhas){
    for(int i=0; i<nLinhas; i++){
        for(int j=0; j<3; j++){
            printf(" %d", mat[i][j]);
        }
        printf("\n ");
    }
}

int validacao(int mat[][3], int num, int linha){
    if(num<1 || num>100)
        return 0;
    for(int i=0;i<linha;i++){
        if(mat[i][0]==num)
            return 0;
    }
    return 1;
}

void preencheMat(int mat[][3], int nLinhas){
    int valor=0;

    for(int i=0; i<nLinhas; i++){
        do{
            printf("Introduza um valor para a coluna %d: ", i);
            scanf("%d", &valor);
        }while(validacao(mat, valor, i)==0);

        mat[i][0]=valor;
        mat[i][1]=mat[i][0]*valor;
        mat[i][2]=mat[i][1]*valor;
    }
}

int main() {
    int mat[4][3]={{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
    int mat2[3][3];

    preencheMat(mat2, 3);
    printMat(mat2, 3);

    return 0;
}
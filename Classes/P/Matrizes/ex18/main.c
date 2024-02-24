#include <stdio.h>

void f(int nCol, int nLin, float mat[][nCol], int *max1, int*min1){
    float media=0, maior_media, menor_media;

    for(int j=0; j<nCol; j++){
        for(int i=0; i<nLin; i++){
            media+=mat[i][j];
        }
        media/=(float)nLin;
        printf("Media coluna %d: %.2f\n", j, media);

        if(media>maior_media){
            maior_media=media;
            *max1=j;
        }
        else if(media<menor_media){
            menor_media=media;
            *min1=j;
        }
        else{
            maior_media=media;
            menor_media=media;
        }

        media=0;
    }
}

void f2(int nCol, int nLin, float mat[][nCol], int *max2, int*min2){
    float media=0, maior_media=0, menor_media;

    for(int j=0; j<nCol; j++){
        for(int i=0; i<nLin; i++){
            media+=mat[i][j];
        }
        media/=(float)nLin;
        printf("Media coluna %d: %.2f\n", j, media);

        if(j==0){
            menor_media=media;
        }

        if(media>maior_media){
            maior_media=media;
            *max2=j;
        }
        else if(media<menor_media){
            menor_media=media;
            *min2=j;
        }
        else{
            menor_media=media;
        }

        media=0;
    }
}

int main(int argc, char** argv) {
    float m1[3][2] = {{1.0, 4.5},{-2.5, 8.9},{0.3, 1.4}};
    float m2[2][6] = {{1.0, 1.6, 4.2, 1.4, 0.5, -3.4},{2.5, 8.1, 0.9, -0.1, 0.8, 3.5}};
    int min1=0, max1=0, min2=0, max2=0;

    //f(3, 2, m1, &max1, &min1);
    f2(6, 2, m2, &max2, &min2);

    printf("Matriz m1: (%d, %d)\n", min1, max1);
    printf("Matriz m2: (%d, %d)\n", min2, max2);
    return 0;
}

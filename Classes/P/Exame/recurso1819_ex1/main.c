#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int id; //id único do colaborador
    char nome[50];
    float latBase,longBase; //latitude e longitude do seu local base
    int ativo; //1 se o colaborador está ao serviço ou 0 se não está
} Colab, *pColab;

typedef struct {
    int id;
    float latitude, longitude;
    char status[100];
    int carga;
    int id_colaborador;
} Trotinete;

float dist(float latA, float longA, float latB, float longB){
    return (latA-latB)+(longA+longB);
}

void writeBin(char *nomeFich, pColab colabs, int numColabs){
    FILE* file = fopen("colabs.bin", "wb"); // Open the file in binary write mode

    if (file != NULL) {
        fwrite(colabs, sizeof(Colab), numColabs, file);
        printf("Array written to %s\n", nomeFich);
    }
    else {
        printf("Failed to open the file.\n");
    }
    fclose(file);
}

void readBin(const char* filename) {
    FILE* file = fopen(filename, "rb");

    if (file != NULL) {
        Colab colabs[4];
        int numColabs = fread(colabs, sizeof(Colab), 4, file);

        printf("Number of colabs read: %d\n", numColabs);

        for (int i = 0; i < numColabs; i++) {
            printf("ID: %d\n", colabs[i].id);
            printf("Nome: %s\n", colabs[i].nome);
            printf("Latitude base: %.3f\n", colabs[i].latBase);
            printf("Longitude base: %.3f\n", colabs[i].longBase);
            printf("Ativo: %d\n", colabs[i].ativo);
            putchar('\n');
        }
    }
    else {
        printf("Failed to open the file.\n");
    }

    fclose(file);
}

pColab criaArray(char *nomeFich, int *total){ //a)
    pColab colabs=NULL;
    Colab aux;
    int i=0;

    FILE *f= fopen(nomeFich, "rb");
    if(f==NULL){
        printf("Erro ao abrir fichero!\n");
        return colabs;
    }



    while(fread(&aux, sizeof(Colab), 1, f)){
        colabs=realloc(colabs,sizeof(Colab)*i+1);
        (colabs+i)->id=aux.id;
        strcpy((colabs+i)->nome, aux.nome);
        (colabs+i)->latBase=aux.latBase;
        (colabs+i)->longBase=aux.longBase;
        (colabs+i)->ativo=aux.ativo;
        i++;
    }

    *total=i;
    return colabs;
}

void separaInfo(char *fichInfo, char *fichColabs){ //b)
    FILE *info= fopen(fichInfo, "rt");
    if(info==NULL){
        printf("Erro ao abrir fichero 1!\n");
        return;
    }
    FILE *colabs= fopen(fichColabs, "rb");
    if(colabs==NULL){
        printf("Erro ao abrir fichero 2!\n");
        fclose(info);
        return;
    }
    FILE *avarias= fopen("avarias.txt", "wt");
    if(avarias==NULL){
        printf("Erro ao abrir fichero 3!\n");
        fclose(info);
        fclose(colabs);
        return;
    }
    FILE *recarga= fopen("recarga.txt", "wt");
    if(recarga==NULL){
        printf("Erro ao abrir fichero 4!\n");
        fclose(info);
        fclose(colabs);
        fclose(avarias);
        return;
    }

    Trotinete trotinete;
    Colab colab;
    float distancia, maxDistancia=500;
    int id;

    while(fscanf(info, " %d, %f, %f, %d, %s", &trotinete.id, &trotinete.latitude, &trotinete.longitude, &trotinete.carga, trotinete.status)==5){
        if(strcmp(trotinete.status, "avaria")==0 || strcmp(trotinete.status, "alarme")==0){
            fprintf(avarias, " %d, %f, %f, %d, %s\n", trotinete.id, trotinete.latitude, trotinete.longitude, trotinete.carga, trotinete.status);
        }
        else if(trotinete.carga<60){
            while(fread(&colab, sizeof(Colab), 1, colabs)){
                if(colab.ativo==1){
                    distancia=dist(trotinete.longitude, trotinete.latitude, colab.longBase, colab.latBase);
                    if(distancia<maxDistancia){
                        maxDistancia=distancia;
                        id=colab.id;
                    }
                }
            }
            fprintf(recarga, "%d, %f, %f, %d, %d\n", trotinete.id, trotinete.latitude, trotinete.longitude, trotinete.carga, id);
            rewind(colabs);
        }
    }

}

int main() {
    /*Colab colabs[4]={{1, "Pedro", 1.234, 2.345, 1},
                     {2, "Ana", 1.555, 2.555, 1},
                     {3, "Joao", 1.666, 2.666, 0},
                     {4, "Miguel", 1.777, 2.777, 0}};

    //writeBin("colabs.bin", colabs, 4);
    //readBin("colabs.bin");
    //pColab colabs=NULL;
    //int total=0;

    colabs=criaArray("colabs.bin", &total);

    for(int i=0; i<total; i++){
        printf("ID: %d\n", colabs[i].id);
        printf("Nome: %s\n", colabs[i].nome);
        printf("Latitude base: %.3f\n", colabs[i].latBase);
        printf("Longitude base: %.3f\n", colabs[i].longBase);
        printf("Ativo: %d\n", colabs[i].ativo);
        putchar('\n');
    }*/

    separaInfo("info.txt", "colabs.bin");

    return 0;
}

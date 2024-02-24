#include <stdio.h>

void printTxt(char *nomeFich){ //ex4
    FILE *f;
    //char str[100];
    int c;

    f=fopen(nomeFich, "r");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    /*while(fscanf(f, " %s", str)==1){
        printf("%s", str);
    }*/

    while((c=fgetc(f))!=EOF){
        printf("%c", c);
    }

    fclose(f);
}

void mostraTxtLin(char *nomeFich){ //ex6
    FILE *f;
    int c, linhas=0;

    f=fopen(nomeFich, "r");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    while((c=fgetc(f))!=EOF){
        if(linhas==0){
            printf("%d: ", ++linhas);
        }
        printf("%c", c);
        if(c=='\n'){
            printf("%d: ", ++linhas);
        }
    }

    fclose(f);
}

void mostraLin(char *nomeFich, int linha){ //ex7
    FILE *f;
    int c, linhas=0;

    f=fopen(nomeFich, "r");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    while((c=fgetc(f))!=EOF){
        if(linhas==0){
            linhas++;
        }
        if(linhas==linha){
            printf("%c", c);
        }
        if(c=='\n'){
            linhas++;
        }
    }

    fclose(f);
}

void vogalMaisComum(char *nomeFich){ //ex8
    FILE *f;
    int c, max=0, vogais[5]={'a', 'e', 'i', 'o', 'u'}, a=0, e=0, i=0, o=0, u=0;

    f=fopen(nomeFich, "r");
    if(f==NULL){
        fprintf(stderr, "Erro ao abrir o ficheiro %s para leitura.\n", nomeFich);
        return;
    }

    while((c=fgetc(f))!=EOF){
        if(c==vogais[0]){
            a++;
        }
        else if(c==vogais[1]){
            e++;
        }
        else if(c==vogais[2]){
            i++;
        }
        else if(c==vogais[3]){
            o++;
        }
        else if(c==vogais[4]){
            u++;
        }

        //incompleto
    }

    fclose(f);

}

int main() {
    //printTxt("linha1.txt");
    //mostraTxtLin("linha1.txt");
    //mostraLin("linha1.txt", 6);
    vogalMaisComum("linha1.txt");

    return 0;
}
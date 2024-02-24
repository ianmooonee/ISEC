#include <stdio.h>
#include <string.h>

int main() {
    int i;
    char mes[20];
    char mesPT[12][15] = {"janeiro", "fevereiro", "marco", "abril", "maio", "junho", "julho", "agosto", "setembro",
                          "outubro", "novembro", "dezembro"};
    char *mesING[12] = {"january", "february", "march", "april", "may", "june", "july", "august", "september",
                        "october", "november", "december"};

    do{
        printf("Introduza um mês: ");
        scanf(" %s", mes);

        for(i=0; i<12; i++){
            if(strcmp(mes, mesPT[i])==0){
                printf(" %s - %s\n", mes, mesING[i]);
                break;
            }
        }
        if(i==12){
            printf("Mes inexistente!\n");
        }

    }while(strcmp(mes, "sair")!=0);

    return 0;
}

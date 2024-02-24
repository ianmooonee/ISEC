//
// Created by Pedro Serrano on 13/04/2023.
//

#include <stdio.h>
#include <stdlib.h>
#include "paragens.h"
#include "linhas.h"
#include "utils.h"

int main() {
    pParagem paragens=NULL; //lista de paragens
    pLinha linhas=NULL; //lista de linhas

    int totalParagens=0, codigoParagem=0, totalLinhas=0; //total de paragens, valor "int" do código das paragens, total de linhas
    int ret=-1; //valor de retorno de funções
    char escolha, escolha1, escolha2, nomeP[100], nomeL[100], nomeFich[100]; //escolha do menu e auxiliares para nomes de paragens, linhas e ficheiros

    do{
        escolha = '0';

        printf("\n----------Menu----------\n");
        printf("Introduza uma opcao: \n1- Gerir paragens\n2- Gerir linhas\n3- Guardar sistema\n4- Ler sistema\n5- Sair\n Opcao: ");
        scanf("%c", &escolha);

        while (getchar() != '\n'); //limpar buffer

        switch (escolha) {
            case '1':
                do {
                    printf("\n----------Menu paragens----------\n");
                    printf("Introduza uma opcao: \n1- Adicionar paragem\n2- Eliminar paragem \n3- Listar paragens\n4- Sair\n Opcao: ");
                    scanf("%c", &escolha1);
                    while (getchar() != '\n'); //limpar buffer

                    switch (escolha1) {
                        case '1': //adicionar uma paragem
                            printf("Introduza o nome para a paragem: ");
                            scanf(" %99[^\n]", nomeP);
                            while (getchar() != '\n'); //limpar buffer
                            paragens=adicionarParagem(paragens, &totalParagens, nomeP, &codigoParagem);
                            break;
                        case '2': //eliminar uma paragem
                            printf("Introduza o nome da paragem a eliminar: ");
                            scanf(" %99[^\n]", nomeP);
                            while (getchar() != '\n'); //limpar buffer
                            linhas=delParagemTodas(linhas, nomeP, &totalLinhas);
                            paragens=eliminarParagem(paragens, &totalParagens, nomeP);
                            break;
                        case '3': //listar as paragens
                            ret=listarParagens(paragens, totalParagens);
                            if(ret==0){
                                fprintf(stderr, "Não existem paragens registadas!\n");
                            }
                            break;
                        case '4': //sair sem dar opção não reconhecida
                            break;
                        default:
                            fprintf(stderr, "Opcao nao reconhecida! Por favor tente novamente.\n");
                            break;
                    }
                }while(escolha1!='4');
                break;

            case '2':
                do {
                    printf("\n----------Menu Linhas----------\n");
                    printf("Introduza uma opcao: \n1- Adicionar linha\n2- Adicionar linha por ficheiro de texto\n3- Listar linhas\n4- Adicionar paragem a uma linha\n5- Remover paragem a uma linha\n6- Sair\n Opcao: ");
                    scanf("%c", &escolha2);
                    while (getchar() != '\n'); //limpar buffer

                    switch (escolha2) {
                        case '1': //criar linha manualmente
                            printf("Introduza o nome para a linha:  ");
                            scanf("%99[^\n]", nomeL);
                            while (getchar() != '\n'); //limpar buffer
                            linhas=criaLinha(linhas, nomeL, paragens, totalParagens, &totalLinhas);
                            break;
                        case '2': //ler ficheiro .txt para criar linha
                            printf("Introduza o nome do ficheiro para ler:  ");
                            scanf("%99[^\n]", nomeFich);
                            while (getchar() != '\n'); //limpar buffer
                            loadLinhaTxt(&linhas, paragens, totalParagens,nomeFich);
                            break;
                        case '3': //listar linhas
                            listaLinhas(linhas);
                            break;
                        case '4':
                            //adicionar paragem ao final de uma linha
                            printf("Introduza o nome da linha para adicionar uma paragem:  ");
                            scanf("%99[^\n]", nomeL);
                            while (getchar() != '\n'); //limpar buffer
                            printf("Introduza o nome da paragem a adicionar:  ");
                            scanf("%99[^\n]", nomeP);
                            while (getchar() != '\n'); //limpar buffer
                            ret=addParagemLinha(linhas, nomeL, nomeP, paragens, totalParagens);
                            if(ret==0){
                                fprintf(stderr, "Nao foi adicionada nenhuma paragem!\n");
                            }
                            else{
                                printf("Paragem adicionada com sucesso!\n");
                            }
                            break;
                        case '5': //removerParagem
                            printf("Introduza o nome da linha para remover uma paragem:  ");
                            scanf("%99[^\n]", nomeL);
                            while (getchar() != '\n'); //limpar buffer
                            printf("Introduza o nome da paragem a remover:  ");
                            scanf("%99[^\n]", nomeP);
                            while (getchar() != '\n'); //limpar buffer
                            ret=delParagemLinha(linhas, nomeL, nomeP);
                            if(ret==0){
                                fprintf(stderr, "Nao foi removida nenhuma paragem!\n");
                            }
                            else{
                                printf("Paragem removida com sucesso!\n");
                            }
                            break;
                        case '6': //sair sem dar opção não reconhecida
                            break;
                        default:
                            fprintf(stderr, "Opcao nao reconhecida! Por favor tente novamente.\n");
                            break;
                    }
                }while(escolha2!='6');
                break;
            case '3': //save ficheiro .dat
                printf("Introduza o nome do ficheiro para guardar:  ");
                scanf("%99[^\n]", nomeFich);
                while (getchar() != '\n'); //limpar buffer
                saveAll(linhas, paragens, totalLinhas, totalParagens, nomeFich);
                break;
            case '4': //ler ficheiro .dat
                printf("Introduza o nome do ficheiro para ler:  ");
                scanf("%99[^\n]", nomeFich);
                while (getchar() != '\n'); //limpar buffer
                loadAll(&paragens, &linhas, &totalParagens, &totalLinhas, &codigoParagem, nomeFich);
                break;
            case '5': //sair sem dar opção não reconhecida
                break;
            default:
                fprintf(stderr, "Opcao nao reconhecida! Por favor tente novamente.\n");
                break;
        }
    }while(escolha!='5');

    printf("A terminar programa...");
    libertaTudo(linhas);
    free(paragens);

    return 0;
}
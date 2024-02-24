#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct e Exercicio, *pExercicio;
typedef struct c Cliente, *pCliente;

struct c{
    int id; // id inteiro único do cliente
    char nome[80];
    int dia, mes, ano; //data limite para atualizar a lista de exercícios pExercicio treino;
    pExercicio treino;
};

struct e{
    char id[20]; // id alfanumérico único
    int series, repeticoes; // Número de séries e repetições para este
    pExercicio prox;
};

void exerciciosUnicos(pCliente clientes, int tam){ //a)
    pExercicio aux;
    for(int i=0; i<tam; i++){
        aux=clientes->treino;
        int conta=0;
        char id[20];

        while(aux!=NULL) {
            strcpy(id, aux->id);

            for (int j = i+1; j < tam; j++) {
                if(strcmp(aux->id,id)==0){
                    conta++;
                }
            }
            if(conta==0) {
                printf("%s\n", aux->id);
            }

            aux=aux->prox;
        }
    }
}


pCliente criaED(pCliente clientes, int dim, int* nDim, int d, int m, int a){

}

int main() {
    pCliente clientes=malloc(sizeof(Cliente)*3);

    // Populate the array with client information
    for (int i = 0; i < 3; i++) {
        pCliente cliente = &clientes[i];
        cliente->id = i + 1; // unique id
        sprintf(cliente->nome, "Client %d", i + 1); // client name
        cliente->dia = 1;
        cliente->mes = 1;
        cliente->ano = 2023;
    }

    // Create exercises as linked lists for each client
    for (int i = 0; i < 3; i++) {
        pCliente cliente = &clientes[i];
        cliente->treino = NULL; // initialize the exercise linked list as empty

        // Populate the exercise linked list for the client
        for (int j = 0; j < 3; j++) {
            pExercicio exercicio = malloc(sizeof(Exercicio)); // create a new exercise node
            sprintf(exercicio->id, "Exercise %d-%d", i + 1, j + 1); // unique id
            exercicio->series = j + 1;
            exercicio->repeticoes = j + 1;
            exercicio->prox = NULL;

            // Add the exercise node to the linked list
            if (cliente->treino == NULL) {
                cliente->treino = exercicio;
            } else {
                pExercicio current = cliente->treino;
                while (current->prox != NULL) {
                    current = current->prox;
                }
                current->prox = exercicio;
            }
        }
    }

    //exerciciosUnicos(clientes, 3);


    // Print the client information and their exercises
    /*for (int i = 0; i < 3; i++) {
        pCliente cliente = &clientes[i];
        printf("Client ID: %d\n", cliente->id);
        printf("Client Name: %s\n", cliente->nome);
        printf("Deadline: %d/%d/%d\n", cliente->dia, cliente->mes, cliente->ano);

        printf("Exercises:\n");
        pExercicio exercicio = cliente->treino;
        while (exercicio != NULL) {
            printf("  Exercise ID: %s\n", exercicio->id);
            printf("  Series: %d\n", exercicio->series);
            printf("  Repetitions: %d\n", exercicio->repeticoes);
            exercicio = exercicio->prox;
        }

        printf("\n");
    }*/

    return 0;
}

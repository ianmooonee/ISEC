#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// Estrutura auxiliar para armazenar as componentes de uma hora: horas + minutos
struct tempo{
    int h, m;
};

// Estrutura para armazenar informacao sobre um voo
typedef struct info voo, *pvoo;
struct info{
    int num;                      // Numero do voo
    char companhia[100];         // Companhia
    char destino[100];          // Destino
    struct tempo hora_p;		// Hora de partida
};

// Funcao que obtem a hora do sistema e a devolve numa estrutura do tipo struct tempo
struct tempo hora_atual(){
    time_t a;
    struct tm* b;
    struct tempo atual;

    time(&a);
    b = localtime(&a);
    atual.h = b->tm_hour;
    atual.m = b->tm_min;
    return atual;
}


pvoo initVoos(int *t){
    voo a[4] = {{123, "TAP", "Londres", {16,45}},{111, "IBERIA", "Madrid", {17,20}},
                {456, "TAP", "Rio de Janeiro", {18,9}},{99, "KLM", "Zurique", {22,40}}};
    int i;
    pvoo p = malloc(sizeof(voo)*4);
    if(p==NULL){*t=0; return NULL;}

    for(i=0; i<4; i++)
        p[i] = a[i];
    *t = 4;
    return p;
}

void printTodos(pvoo a, int t){
    int i;

    printf("\nExistem %d voos:\n", t);
    for(i=0; i<t; i++)
        printf("%d: %s - %s - %2d:%2.2d\n", a[i].num, a[i].companhia, a[i].destino, a[i].hora_p.h, a[i].hora_p.m);
}

pvoo addVoo(pvoo a, int *t){
    int i, hTemp, hVoo;

    voo *aux, temp, v={888, "JAVARDAIR", "Porto", {17, 30}};

    aux=realloc(a, sizeof(voo)*(*t+1));
    if(aux==NULL){
        fprintf(stderr, "Erro ao realocar o array");
        return a;
    }
    a=aux;

    hTemp=v.hora_p.h+v.hora_p.m/60;

    for(i=0; i<*t; i++){
        hVoo=a[i].hora_p.h+a[i].hora_p.m/60;
        if(hTemp<hVoo){
            break;
        }
    }

    for(int j=*t; j>i; j--){
        a[j]=a[j-1];
    }

    a[i]=v;

    (*t)++;

    return a;
}

void printNext(pvoo a, int t, int minutos){
    int mVoo, i;
    struct tempo hora_atual2=hora_atual();
    int mAtual=hora_atual2.h*60+hora_atual2.m;

    printf("\nA partir nos proximos %d minutos: \n", minutos);

    for(i=0; i<t; i++){
        mVoo=a[i].hora_p.h*60+a[i].hora_p.m;
        if(mAtual<mVoo && mVoo-mAtual<=minutos){
            printf("%d: %s - %s - %2d:%2.2d\n", a[i].num, a[i].companhia, a[i].destino, a[i].hora_p.h, a[i].hora_p.m);
        }
    }
}

pvoo delVoo(pvoo a, int *t){
    int i, mVoo, nVDel=0;
    struct tempo hora_atual2=hora_atual();
    int mAtual=hora_atual2.h*60+hora_atual2.m, j;

    for(i=0; i<*t; i++){
        mVoo=a[i].hora_p.h*60+a[i].hora_p.m;
        if(mVoo<mAtual){
            nVDel++;
        }
    }

    pvoo aux=malloc(sizeof(voo)*(*t-(nVDel)));

    for(i=0, j=0; i<*t; i++){
        mVoo=a[i].hora_p.h*60+a[i].hora_p.m;
        if(mVoo>mAtual){
            aux[j]=a[i];
            j++;
        }
    }

    *t=(*t-(nVDel));

    free(a);

    return aux;
}

int main(){

    pvoo tab = NULL;
    int total = 0;

    struct tempo x = hora_atual();
    printf("Hora atual: %2.2d:%2.2d\n", x.h, x.m);

    tab = initVoos(&total);

    printTodos(tab, total);

    tab = addVoo(tab, &total); //adicionar ordernado pela hora

    printTodos(tab, total);

    printNext(tab, total, 60); //voos que partem nos próximos x minutos - usar hora_atual

    tab=delVoo(tab, &total);

    printTodos(tab, total);

    free(tab);

    return 0;
}
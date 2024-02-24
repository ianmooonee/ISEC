#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    /*1
    char str[15];

    printf("Introduza chars: \n");
    scanf("%s", str);

    printf("\n%s\n", str);*/

    /*3
    if(argc!=3)
        return 0;

    setbuf(stdout, NULL);

    for(int i=0; i<atoi(argv[1]); i++) {
        printf("s");
        sleep(atoi(argv[2]));
    }

    printf("\n");
     */

    int op;

    printf("Introduza opcao: ");
    scanf(" %d", &op);

    if(op==1)
        execl("./port","./port", NULL);
    else
        execl("./ing","./ing", NULL);

    return 0;
}

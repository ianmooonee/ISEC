#include <stdio.h>
#include "ponto.h"

int main() {
    ponto2D p1 = {1,3}, p2, p3={10, 10};

    initPonto(&p2);

    printPonto(p1);

    printPonto(p2);

    movePonto(&p1, 3, -2);
    printPonto(p1);

    mesmaReta(&p1, &p2, &p3);

    return 0;
}

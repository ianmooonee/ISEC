#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int a = 10;
    int i = fork();

    if (i == 0)
        a++;
    else if (i>0) {

        wait(&i);
        a--;
    }
    else
        return -1;
    printf("\na = %d\n", a);

    return 0;
}

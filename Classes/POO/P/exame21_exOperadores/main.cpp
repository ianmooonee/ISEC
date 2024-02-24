#include "Texto.h"

int main() {
    Texto a("asd");
    Texto b({"ola", "isto", "tem", "piada"});

    a<<"tambem"<<"quero"<<"quero"; //procura a.operator<<(string s) ou operator<<(Texto a, sting s)

    return 0;
}

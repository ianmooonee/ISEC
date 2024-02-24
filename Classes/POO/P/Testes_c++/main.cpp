#include <iostream>
#include <string>
#include <ctime>
using namespace std;

int main() {
    // GERA ZONAS (6 tipos de zonas)
    srand (time(nullptr));
    int linhas = 3, colunas = 3;
    int controlo = 0, v = 0;
    bool estado[6];

    for (int k = 0; k < 6; k++)
        estado[k] = false;

    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            do {
                v = (rand() % 6);

                if (!estado[v]) {
                    // GERA ZONA
                    switch (v) {
                        case 0:
                            cout << "\nPastagem ";
                            break;
                        case 1:
                            cout << "\nDeserto ";
                            break;
                        case 2:
                            cout << "\nFloresta ";
                            break;
                        case 3:
                            cout << "\nMontanha ";
                            break;
                        case 4:
                            cout << "\nPantano ";
                            break;
                        case 5:
                            cout << "\nSerracao ";
                            break;
                    }
                }
            } while (estado[v]);

            estado[v] = true;
            controlo++;

            if (controlo == 6) {
                for (int k = 0; k < 6; k++)
                    estado[k] = false;
            }
        }
    }
}
/*
 *
 * for(int i=cartoes.begin(); i!=cartoes.end();){
 *      if(i->getSaldo()<0)
 *          i=cartoes.erase(i);
 *      else
 *          i++;
 *
 *
 *
 * int k=1;
 * for(auto i=cartoes.begin(); i!=cartoes.end(); i++; k++){
 *      if(i->getNumero()==numero)
 *          break;
 * }
 * return k;
 *
 *
 */
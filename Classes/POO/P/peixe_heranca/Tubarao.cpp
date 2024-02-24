//
// Created by ianmoone on 17/12/2021.
//

#include "Peixe.h"
#include "Tubarao.h"
#include "Aquario.h"

using namespace  std;

void Tubarao::serAlimentado(int gramas) {
    /*ignora a comida que lhe é dada. Se tiver mais do que 20 gramas, diminui um
    grama de peso. Se tiver menos de 20 gramas procura um outro peixe qualquer e come-o,
            acrescentando ao seu peso o peso do peixe ingerido. Se não houver nenhum peixe para
    comer e já tiver menos de 20 gramas, diminui o seu peso em 2 gramas. Se tiver menos de 5
    gramas morre. */
    if (isVivo()) {
        if (getPeso() >= 20) {
            setPeso(getPeso() - 1);
        } else {
            Peixe *p = getOndeEstou()->getPeixeAleatorio();
            if (p != this && p != nullptr) {
                p->setVivo(false);
                setPeso(getPeso() + p->getPeso());
            } else {
                setPeso(getPeso() - 2);
                if (getPeso() < 5)
                    setVivo(false);
            }
        }
    }
}

Tubarao *Tubarao::clone() const {
    return new Tubarao;
}
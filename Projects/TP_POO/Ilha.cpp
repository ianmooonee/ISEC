//
// Created by ianmoone on 16/11/2021.
//

#include "Ilha.h"

string Ilha::getAsString() {
    ostringstream os;
    os<<"Dinheiro: "<<this->dinheiro<<endl;
    os<<"Dia: "<<this->dia<<endl;
    os << "Ilha com " << this->total_zonas << " zonas" << ": "<<endl;
    for(int i=0;  i<zonas.size(); i++)
        os << this->zonas.at(i)->getAsString()<<endl;
    return os.str();
}

string Ilha::ZonaAsString(const int &cordx, const int &cordy){
    for(int i=0; i<this->zonas.size(); i++) {
                if (this->zonas.at(i)->getCordx() == cordx && this->zonas.at(i)->getCordy() == cordy) {
                    return this->zonas.at(i)->getAsString();
                }
    }
    return "Estas coordenadas nao sao validas!";
}

Ilha::Ilha(int nlinhas, int ncolunas) : total_zonas(nlinhas*ncolunas), dinheiro(100), nlinhas(nlinhas), ncolunas(ncolunas) {
    srand (time(nullptr));
    int flag = 0, v;
    bool estado[6];

    for (int l = 0; l < 5; l++)
        estado[l] = false;

    for (int i = 0; i < nlinhas; i++) {
        for (int j = 0; j < ncolunas; j++) {
            do {
                v = (rand() % 5);

                if (!estado[v]) {
                    switch (v) {
                        case 0:
                            zonas.push_back(new Pastagem(i,j));
                            break;
                        case 1:
                            zonas.push_back(new Deserto(i,j));
                            break;
                        case 2:
                            zonas.push_back(new Floresta(i,j));
                            break;
                        case 3:
                            zonas.push_back(new Montanha(i,j));
                            break;
                        case 4:
                            zonas.push_back(new Pantano(i,j));
                            break;
                    }
                }
            } while (estado[v]);

            estado[v] = true;
            flag++;

            if (flag == 5) {
                flag=0;
                for (int k = 0; k < 5; k++)
                    estado[k] = false;
            }
        }
    }
}



bool Ilha::contratarTrab(const string &tipotrab){
    for(int i=0; i<this->zonas.size(); i++){
        if(comparador(this->zonas.at(i)->getNome(),"Pastagem1")){
            if(comparador(tipotrab,"miner") || comparador(tipotrab,"oper") || comparador(tipotrab,"len")) {
                this->zonas.at(i)->addTrab(tipotrab, dia, dinheiro);
                return true;
            }
        }
    }
    return false;
}

bool Ilha::constroiEdi(const string &tipo, const int &cordx, const int &cordy){
    for(int i=0; i<this->zonas.size(); i++){
        if(this->zonas.at(i)->getCordx()==cordx && this->zonas.at(i)->getCordy()==cordy){
            if(comparador(tipo,"minaferro") || comparador(tipo,"minacarvao") || comparador(tipo,"central") || comparador(tipo,"bateria") || comparador(tipo,"fundicao") || comparador(tipo,"serracao")){
                if(this->zonas.at(i)->addEdificio(tipo, dinheiro))
                    return true;
            }
        }
    }
    return false;
}

bool Ilha::constroiEdiDeb(const string &tipo, const int &cordx, const int &cordy){
    for(int i=0; i<this->zonas.size(); i++){
        if(this->zonas.at(i)->getCordx()==cordx && this->zonas.at(i)->getCordy()==cordy){
            if(comparador(tipo,"minaferro") || comparador(tipo,"minacarvao") || comparador(tipo,"central") || comparador(tipo,"bateria") || comparador(tipo,"fundicao") || comparador(tipo,"serracao")){
                if(this->zonas.at(i)->addEdificioDeb(tipo))
                    return true;
            }
        }
    }
    return false;
}

void Ilha::avancaDia() {
    for(int i=0; i<this->zonas.size(); i++) {
        //pontos+=this->zonas.at(i)->getPontosTrabalhadores();
        dinheiro+=this->zonas.at(i)->getPontosEdificios();
        this->zonas.at(i)->resetMovidos();
    }
    dia++;
}

bool Ilha::moverTrabalhador(string &nomeTrab, int &cordx, int &cordy) {
    int flag=0;
    Trabalhador *novo;

    if(cordx>nlinhas-1 || cordy>ncolunas-1 || cordx<0 || cordy<0){
        return false;
    }

    for (int i = 0; i < this->zonas.size(); i++) {
        if(this->zonas.at(i)->procuraTrab(nomeTrab)!=nullptr){
            novo=this->zonas.at(i)->procuraTrab(nomeTrab);
            this->zonas.at(i)->removeTrabalhadorZona(nomeTrab);
            flag=1;
        }
    }

    if(flag) {
        for (int i = 0; i < this->zonas.size(); i++) {
            if (this->zonas.at(i)->getCordx() == cordx && this->zonas.at(i)->getCordy() == cordy) {
                this->zonas.at(i)->moverTrabalhadorZona(novo);
                return true;
            }
        }
    }
    return false;
}

void Ilha::setDinheiroDeb(int dinheiro) {

    if((this->dinheiro+dinheiro)<0)
        return;

    this->dinheiro=this->dinheiro+dinheiro;
}

void Ilha::ligaEdi(const int &cordx, const int &cordy) {
    for(int i=0; i<this->zonas.size(); i++){
        if(this->zonas.at(i)->getCordx()==cordx && this->zonas.at(i)->getCordy()==cordy){
            this->zonas.at(i)->ligaEdiZona();
            }
        }
    }

void Ilha::desligaEdi(const int &cordx, const int &cordy) {
    for(int i=0; i<this->zonas.size(); i++){
        if (this->zonas.at(i)->getCordx() == cordx && this->zonas.at(i)->getCordy() == cordy) {
            if (!this->zonas.at(i)->getEdificios().empty()) {
                this->zonas.at(i)->desligaEdiZona();
            }
        }
    }
}

void Ilha::delTrab(string &nomeTrab) {
    for(int i=0; i<this->zonas.size(); i++){
        if(this->zonas.at(i)->getTrabalhadores().size()>0){
            this->zonas.at(i)->delTrab(nomeTrab);
        }
    }
}

void Ilha::vendeEd(const int &cordx, const int &cordy) {
    for(int i=0; i<this->zonas.size(); i++){
        if (this->zonas.at(i)->getCordx() == cordx && this->zonas.at(i)->getCordy() == cordy && !this->zonas.at(i)->getEdificios().empty()){
            this->zonas.at(i)->vendeEdi();
        }
    }
}

Ilha::~Ilha() {
    for(auto el:zonas)
        delete el;
    zonas.clear();
}

Ilha &Ilha::operator=(const Ilha &il) {
    nlinhas=il.nlinhas;
    ncolunas=il.ncolunas;
    total_zonas=il.total_zonas;
    dinheiro=il.dinheiro;
    dia=il.dia;

    for (auto el : zonas) {
        delete el;
    }
    zonas.clear();

    for (auto i = 0; i < il.zonas.size(); i++) {
        zonas.push_back(il.zonas.at(i)->clone());
    }

    return *this;
}

Ilha::Ilha(const Ilha &il) {
    *this = il;
}

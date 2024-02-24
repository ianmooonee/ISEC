#include "Zona.h"

const string &Zona::getNome() const {
    return nome;
}

void Zona::setNome(const string &nomen) {
    Zona::nome = nomen;
}

int Zona::getCordx() const {
    return cordx;
}

int Zona::getCordy() const {
    return cordy;
}

bool Zona::addTrab(const string& str, int &dia, int &dinheiro){
    if(comparador(str,"miner") && dinheiro>=10) {
        this->trabalhadores.push_back(new Mineiro(dia));
        dinheiro-=10;
        return true;
    }
    else if(comparador(str,"oper") && dinheiro>=15) {
        this->trabalhadores.push_back(new Operario(dia));
        dinheiro-=15;
        return true;
    }
    else if(comparador(str,"len") && dinheiro>=20) {
        this->trabalhadores.push_back(new Lenhador(dia));
        dinheiro-=20;
        return true;
    }
    else
        return false;
}

bool Zona::addEdificio(const string &tipo, int &dinheiro) {
    if (this->edificios.empty()) {
        if (comparador(tipo,"minaferro") && dinheiro>=10) {
            this->edificios.push_back(new MinaFerro(50));
            dinheiro-=10;
            return true;
        }
        else if(comparador(tipo,"minacarvao") && dinheiro>=10){
            this->edificios.push_back(new MinaCarvao(55));
            dinheiro-=10;
            return true;
        }
        else if(comparador(tipo,"central") && dinheiro>=15){
            this->edificios.push_back(new CentralEletrica(60));
            dinheiro-=15;
            return true;
        }
        else if(comparador(tipo,"bateria") && dinheiro>=10){
            this->edificios.push_back(new Bateria(65));
            dinheiro-=10;
            return true;
        }
        else if(comparador(tipo,"fundicao") && dinheiro>=10){
            this->edificios.push_back(new Fundicao(70));
            dinheiro-=10;
            return true;
        }
        else if(comparador(tipo,"serracao") && dinheiro>=20){
            this->edificios.push_back(new Serracao(70));
            dinheiro-=20;
            return true;
        }
    }
    return false;
}

bool Zona::addEdificioDeb(const string &tipo) {
    if (this->edificios.empty()) {
        if (comparador(tipo,"minaferro")) {
            this->edificios.push_back(new MinaFerro(50));
            return true;
        }
        else if(comparador(tipo,"minacarvao")){
            this->edificios.push_back(new MinaCarvao(55));
            return true;
        }
        else if(comparador(tipo,"central")){
            this->edificios.push_back(new CentralEletrica(60));
            return true;
        }
        else if(comparador(tipo,"bateria")){
            this->edificios.push_back(new Bateria(65));
            return true;
        }
        else if(comparador(tipo,"fundicao")){
            this->edificios.push_back(new Fundicao(70));
            return true;
        }
        else if(comparador(tipo,"serracao")){
            this->edificios.push_back(new Serracao(70));
            return true;
        }
    }
    return false;
}


string Zona::getAsString() const{
    ostringstream buffer;
    buffer<<this->getNome()<<" x: "<<this->getCordx()<<" y: "<<this->getCordy();

    if(!this->getEdificios().empty()) {
        buffer << "   Edificio: ";

        for (int j = 0; j < this->getEdificios().size(); j++) {
            buffer << this->getEdificios().at(j)->getAsString();
        }
    }

    if(!this->getTrabalhadores().empty()) {
        buffer << "   Trabalhadores: ";

        for (int i = 0; i < this->getTrabalhadores().size(); i++) {
            buffer << this->getTrabalhadores().at(i)->getAsString() << " ";
        }
    }


    return buffer.str();
}

const vector<Trabalhador *> &Zona::getTrabalhadores() const {
    return trabalhadores;
}

const vector<Edificio *> &Zona::getEdificios() const {
    return edificios;
}

int Zona::getPontosEdificios() {
    int pontuacao=0;

    for(int i=0; i<this->edificios.size(); i++){
        if(this->edificios.at(i)->getEstado()==1)
            pontuacao+=this->edificios.at(i)->getProducao();
    }

    return pontuacao;
}

Trabalhador *Zona::procuraTrab(const string &nomeTrab) {

    for(int i=0; i<this->trabalhadores.size(); i++) {
        if(comparador(this->trabalhadores.at(i)->getNome(), nomeTrab) && this->trabalhadores.at(i)->getMovido()==0){
            return this->trabalhadores.at(i);
        }
    }

    return nullptr;
}

void Zona::moverTrabalhadorZona(Trabalhador *novo) {
    this->trabalhadores.push_back(novo);
    novo->setMovido(1);
}

void Zona::removeTrabalhadorZona(const string &nomee) {
    for(int i=0; i<this->trabalhadores.size(); i++) {
        if(comparador(this->trabalhadores.at(i)->getNome(), nomee)){
            this->trabalhadores.erase(this->trabalhadores.begin()+i);
            break;
        }
    }
}

void Zona::resetMovidos(){
    for(int i=0; i<this->trabalhadores.size(); i++){
        this->trabalhadores.at(i)->setMovido(0);
    }
}

void Zona::ligaEdiZona() {
    this->edificios.front()->setEstado(1);
}

void Zona::desligaEdiZona() {
    this->edificios.front()->setEstado(0);
}

void Zona::delTrab(const string &nome) {
    for(int i=0; i<this->trabalhadores.size(); i++){
        if(comparador(this->trabalhadores.at(i)->getNome(), nome)){
            delete this->trabalhadores.at(i);
            this->trabalhadores.erase(this->trabalhadores.begin()+i);
            break;
        }
    }
}

bool Zona::vendeEdi() {
    for(int i=0; i<this->edificios.size(); i++){
        delete this->edificios.front();
        this->edificios.erase(this->edificios.begin()+i);
    }
    return false;
}

Zona::~Zona() {
    for (auto el : trabalhadores) {
        delete el;
    }
    trabalhadores.clear();

    for (auto ele : edificios) {
        delete ele;
    }
    edificios.clear();

}

Zona &Zona::operator=(const Zona &z) {
    nome=z.nome;
    cordx=z.cordx;
    cordy=z.cordy;

    for (auto el : trabalhadores) {
        delete el;
    }
    trabalhadores.clear();

    for (auto ele : edificios) {
        delete ele;
    }
    edificios.clear();

    for (auto i = 0; i < z.trabalhadores.size(); i++) {
        trabalhadores.push_back(z.trabalhadores.at(i)->clone());
    }

    for (auto i = 0; i < z.edificios.size(); i++) {
        edificios.push_back(z.edificios.at(i)->clone());
    }

    return *this;
}

Zona::Zona(const Zona &z) {
    *this = z;
}



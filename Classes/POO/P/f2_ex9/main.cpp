#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
using namespace std;
static int contador=1;

class TV{

    string nome;
    bool ligada;
    vector<string> canais;
    int volume;
    string canal_sintonizado;

public:
    TV(initializer_list<string> canal):canais(canal){ //usar o igual na list para criar listas vazias
        this->ligada= false;
        this->volume=1;
        this->nome="Tv"+ to_string(contador++);
        this->canal_sintonizado=canais.at(0);
    }

    string getAsString(){
        ostringstream buffer;
        buffer<<"Tv: "<<nome<<" Estado: "<<ligada<<" Volume: "<<volume<<" Canal sintonizado: "<<canal_sintonizado<<"\tCanais: ";
        for(const string &c:canais){
            buffer<<c<<" ";
        }

        return buffer.str();
    }

    bool liga(){
        if(!this->ligada) {
            this->ligada = true;
            return true;
        }
        return false;
    }

    bool desliga(){
        if(this->ligada) {
            this->ligada = false;
            return true;
        }
        return false;
    }

    bool aumenta(){
        if(this->volume<20){
            this->volume++;
            return true;
        }
        return false;
    }

    bool diminui(){
        if(this->volume>0){
            this->volume--;
            return true;
        }
        return false;
    }

    bool mudarCanal(const int &i){
        if(this->ligada) {
            try { //apanhar o out of range caso seja passado um valor fora do vetor
                cout << this->canais.at(i) << '\n';
            } catch(std::out_of_range& e) {
                cerr << e.what( ) << '\n';
                return false;
            }
            this->canal_sintonizado = canais.at(i);
            return true;
        }

        return false;
    }

};

int main() {

    TV t1({"rtp1", "rtp2", "sic", "tvi"});
    TV t2{"sicnoticias", "tvi24"};

    //cout<<t1.getAsString()<<endl;
    //cout<<t2.getAsString()<<endl;

    if(!t1.liga()){
        cout<<"A tv 1 já se encontra ligada"<<endl;
    }

    if(!t1.aumenta()){
        cout<<"Volume t1 ja esta no maximo!"<<endl;
    }

    if(!t1.aumenta()){
        cout<<"Volume t1 ja esta no maximo!"<<endl;
    }

    //cout<<t1.getAsString()<<endl;

    if(!t2.diminui()){
        cout<<"Volume t2 ja esta no minimo!"<<endl;
    }
    if(!t2.diminui()){
        cout<<"Volume t2 ja esta no minimo!"<<endl;
    }

    //t1.desliga();

    if(!t1.mudarCanal(2)){
        cout<<"Tv desligada, nao e possivel mudar o canal ou esta out of range."<<endl;
    }

    //cout<<t1.getAsString()<<endl;
    //t2.mudarCanal(1);
    //cout<<t2.getAsString()<<endl;
    //t1.mudarCanal(100); //dá erro out of range na t1. t2 também dá esse erro

    if(!t1.desliga()){
        cout<<"A tv 1 já se encontra desligada"<<endl;
    }

    cout<<t1.getAsString()<<endl;
    cout<<t2.getAsString()<<endl;

    return 0;
}

/*Volume t2 ja esta no minimo!
Tv desligada, nao e possivel mudar o canal
A tv 1 já se encontra desligada
Tv: Tv1 Estado: 0 Volume: 3 Canal sintonizado: rtp1	Canais: rtp1 rtp2 sic tvi
Tv: Tv2 Estado: 0 Volume: 0 Canal sintonizado: sicnoticias	Canais: sicnoticias tvi24 */
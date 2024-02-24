#include <iostream>
#include <string>
#include <vector>
#include <sstream>
using namespace std;

class Excursao {
    string data;
    const string destino;
    int lotacao;
    vector<int> inscritos;
public:
    Excursao(const string &data, const string &destino, const int &lotacao, const vector<int> &inscritos={}):data(data), destino(destino), lotacao(lotacao){};
    string getAsString() const{
        ostringstream os;

        os<<"Data: "<<data<<" Destino: "<<destino<<" Lotacao: "<<lotacao<<" Inscritos: ";
        for(auto el:inscritos)
            os<<el<<" ";
        os<<endl;

        return os.str();
    }

    bool pesquisa(const int &id){
        for(int i=0; i<inscritos.size(); i++){
            if(id==inscritos.at(i))
                return true;
        }
        return false;
    }

    bool cheio(){
        if(lotacao==0)
            return true;
        lotacao--;
        return false;
    }

    Excursao &operator<<(const int &viajante){

        if(!pesquisa(viajante) && !cheio()) {
            inscritos.push_back(viajante);
        }

        return *this;
    }

    Excursao &operator>>(Excursao &e){
        auto ptr=inscritos.begin();

        while(ptr!=inscritos.end()){
            if(!e.cheio() && pesquisa(*ptr)){
                e.inscritos.push_back(*ptr);
                inscritos.erase(ptr);
                lotacao++;
                ptr--;
            }
            ptr++;
        }

        return *this;
    }

    Excursao &operator=(const Excursao &e){
        //dá para atribuir valores a uma string que é const?
        //destino=e.destino;
        data=e.data;
        lotacao=e.lotacao;
        inscritos=e.inscritos;

        return *this;
    }

    int &operator[](const int &i){
        return inscritos.at(i);
    }

    operator int() const{
        return (int)inscritos.size();
    }
};

int main(){
    Excursao e1("11/11/11", "Lisboa", 4);
    Excursao e2("12/12/12", "Porto", 3);
    Excursao e3("13/13/13", "Algarve", 5);
    int a;

    e1 << 123 << 789 << 123 << 764 << 378 << 987; //<< 167 << 245 << 3 << 4; //funciona

    //e1 >> e2 >> e3; //funciona? na 3 iteração vai buscar os restantes de e1 e põe para e3.

    //e2=e1; //funciona? não copia o destino (é const)

    //e1[1]=10; //funciona

    a=e1; //funciona

    cout<<e1.getAsString();
    //cout<<e2.getAsString();
    //cout<<e3.getAsString();
    cout<<"Numero de inscritos em a: "<<a<<endl;

    return 0;
}

/*class Num {
    int n;
public:
    Num() { n = 0; }
    void setN(int nn) { n = nn; }
    int getN()const { return n; }
};
void f(const Num * p) {
    p->setN(5); // linha 1 //Num é const, logo não pode ser alterado
    p = new Num(); // linha 2
}
void g(Num * const p) {
    p->setN(5); // linha 3
    p = new Num(); // linha 4 //p é const, logo não pode ser alterado
}
int main() {
    Num ob; // linha 5
    ob.setN(4); // linha 6
    f(&ob); // linha 7
    g(&ob); // linha 8
}*/
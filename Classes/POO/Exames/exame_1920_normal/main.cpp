#include <iostream>
#include <string>
#include <vector>
#include <sstream>
using namespace std;

/*class Ponto {
    int x, y;
public:
    Ponto(int x0 = 0, int y0 = 0) {
        x = x0;
        y = y0;
    }
};
class Figura {
    string nome;
public:
    Figura(const string & n) {
        nome = n;
    }
    void setNome(const string & n) {
        nome = n;
    }
};
class Circulo : public Figura {
    Ponto centro;
    const int raio;
public:
    Circulo(int x0, int y0, int r) :
            centro(x0, y0), raio(r){
        setNome("Circulo");
    }
};*/

/*class Trabalhador{
    string nome;
public:
    Trabalhador(const string &n):nome(n){};
    virtual string getNome()const { return nome; };
    virtual string getAsString()const=0;
    virtual ~Trabalhador(){};
};

class Doutor : public Trabalhador{
public:
    Doutor(const string & n):Trabalhador(n){};
    string getAsString() const override{
        ostringstream os;
        os<<"Doutor "<<getNome();
        return os.str();
    };
};

class Engenheiro : public Trabalhador{
public:
    Engenheiro(const string & n):Trabalhador(n){};
    string getAsString() const override{
        ostringstream os;
        os<<"Engenheiro "<<getNome();
        return os.str();
    };
};

class Empresa {
    vector<Trabalhador*> trab;
public:
    Empresa() {
        trab.push_back(new Doutor("D1"));
        trab.push_back(new Doutor("D2"));
        trab.push_back(new Engenheiro("E1"));
        trab.push_back(new Engenheiro("E2"));
    }
    void cumprimentar() {
        for (auto  & d : trab) {
            cout << "Bom dia " << d->getAsString() << endl;
        }
    }
    void removeDoutor(string nome) {
// remove o doutor com esse nome do seu vector
    }
    void removeEngenheiro(string nome) {
// remove o engenheiro com esse nome do seu vector
    }
};
int main() {
    Empresa empresa;
    empresa.cumprimentar();
}*/

class Bilhete{
    string passageiro;
    int passaporte;
    string & companhia;
    vector<int> id_malas;
public:
    Bilhete(const string &nomep, const int &npassaporte, string & com, const vector<int> &malas):passageiro(nomep), passaporte(npassaporte), companhia(com){
        for(auto el : malas)
            //if(pesquisa(el))
                //id_malas.push_back(el);
            *this<<el;
    }

    bool pesquisa(const int &id){
        for(int i=0;i<id_malas.size();i++) {
            if (id == id_malas.at(i)) {
                return true;
            }
        }
        return false;
    }

    string getAsString() const{
        ostringstream os;

        os<<"Nome: "<<passageiro<<" Passaporte:"<<passaporte<<" Companhia: "<<companhia<<" \tMalas: ";
        for(auto el : id_malas)
            os<<el<<" ";
        os<<endl;

        return os.str();
    }

    Bilhete &operator=(const Bilhete &bil){
        passageiro=bil.passageiro;
        passaporte=bil.passaporte;
        id_malas=bil.id_malas;
        return *this;
    }

    Bilhete &operator<<(const int &mala){
        if(!pesquisa(mala))
            id_malas.push_back(mala);
        return *this;
    }

    Bilhete &operator-=(const int &id){
        auto ptr=id_malas.begin();

        while(ptr!=id_malas.end()){
            if(*ptr>=id){
                ptr=id_malas.erase(ptr);
            }
            else
                ptr++;
        }

        return *this;
    }
};

ostream &operator<<(ostream &os, const Bilhete &bil){ //quando é que o operador é fora ou dentro?
    os<<bil.getAsString();
    return os;
}

int main() {
    string comp="TAP";
    Bilhete b1("Pedro", 12345, comp, {40,35,123,40,456,35,40,789,35,40});
    Bilhete b2("Joana", 98765, comp,{});

    b2=b1; //funciona
    b1<<345<<444<<345<<40<<35; //funciona
    cout<<b1.getAsString();
    (b1 -= 150) -= 200;//funciona

    cout<<b1.getAsString();
    //cout<<b2.getAsString();

    //cout<<"Passageiro 1 : "<<b1<<"Passageiro 2: "<<b2; //funciona

    return 0;
}
#include <iostream>
#include <string>
#include <regex>
using namespace std;

class Automovel{
    string matricula;
    string combustivel;
    string marca;
    string modelo;
    string cor;
public:
    Automovel(const string &marcaa, const string &modeloa, const string &cora, const string &combustivela, const string &matriculaa) {
        marca=marcaa;
        modelo=modeloa;
        cor=cora;
        combustivel=combustivela;
        matricula=matriculaa;
    }

    Automovel(){
        marca="Bmw";
        modelo="525tds";
        cor="azul";
        combustivel="Diesel";
        matricula="12-AB-34";
    }

    string getAsString(){
        ostringstream buffer;
        buffer<<"Marca: "<<marca<<" Modelo: "<<modelo<<" Cor: "<<cor<<" Matricula: "<<matricula<<" Combustivel: "<<combustivel<<endl;
        return buffer.str();
    }

    string getMarca(){
        return marca;
    }

    void setMarca(string m){
        this->marca=m;
    }

    string &getCor(){
        return this->cor;
    }

    void setCombustivel(string c){
        this->combustivel=c;
    }

    bool setMatricula(const string m){
        if(regex_match(m, regex("[0-9][0-9]-[A-Z][A-Z]-[0-9][0-9]"))){
            matricula=m;
            return true;
        }

        return false;
    }

    string getMatricula(){
        return matricula;
    }
};

int main() {
    Automovel a("Renault", "Megane", "Preto", "Gasolina", "10-ZX-10");
    Automovel b;

    cout<<a.getAsString()<<endl;
    cout<<b.getAsString()<<endl;
    a.getCor()="Branco"; //muda a cor do automovel para branco atraves da referencia
    cout<<a.getAsString()<<endl;
    a.setCombustivel("GPL");
    cout<<a.getAsString()<<endl;
    if(a.setMatricula("78-TY-59")){
        cout<<"Nova matricula de a: "<<a.getMatricula()<<endl;
    }
    if(b.setMatricula("AB-TY-59")){
        cout<<"Nova matricula de a: "<<b.getMatricula()<<endl;
    }
    else{
        cout<<"Matricula invalida para b. Ficou na mesma com "<<b.getMatricula()<<endl;
    }

    return 0;
}

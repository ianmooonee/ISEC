#include <iostream>
#include <string>
#include <regex>
using namespace std;
//usar regex no exercicio dos carros, regex_match

class Caderno {
    string marca;
    string cor;
    int nfolhas;
    string dimensao;
    string texto;
    int npag;
    int npal;
public:
    Caderno(){
        marca="Xyz";
        cor="Verde";
        nfolhas=1000;
        dimensao="1x2";
        texto="Texto do construtor.\n";
        npag=1;
        npal=0;
    }

    string getAsString(){
        ostringstream buffer;
        buffer<<"Marca: "<<marca<<" Cor: "<<cor<<" N folhas: "<<nfolhas<<" Dimensao: "<<dimensao<<" Pagina: "<<npag<<" Texto: "<<texto<<endl;
        return buffer.str();
    }

    string getMarca(){
        return marca;
    }

    string &getCor(){
        return this->cor;
    }

    int getNFolhas(){
        return nfolhas;
    }

    bool setNFolhas(const int &nf){
        if(nf>0&&nf<1500){
            nfolhas=nf;
            return true;
        }

        return false;
    }

    string getDimensao(){
        return dimensao;
    }

    bool setDimensao(const string &dim){
        if(regex_match(dim, regex("[0-9]+x[0-9]"))){
            dimensao=dim;
            return true;
        }

        return false;
    }

    int setNPagina(int n){
        npag=n;
    }

    int getNPagina(){
        return npag;
    }

    void insereTexto(string novo){
        //texto+=novo;
        texto += "Pag. " + to_string(npag) + ": "+novo+"\n";
    }

    void mudaPagina(){
        npag++;
    }

    string getTexto(){
        return texto;
    }

    int getNPalavras(){
        string s;
        istringstream is(texto);
        while(is>>s){
            npal++;
        }

        return npal;
    }

};

int main() {
    Caderno c;

    //cout<<c.getMarca()<<" "<<c.getCor()<<" "<<c.getNfolhas()<<" "<<c.getTamanho()<<endl;
    cout<<c.getAsString();
    //cout<<c.getMarca()<<" "<<c.getCor()<<endl;
    c.getCor()="Preto";
    //cout<<c.getCor()<<endl;
    c.setDimensao("2x3"); //valido - validado pelo regex_match()
    c.setDimensao("20x30"); //invalido
    c.setDimensao("30"); //invalido
    //<<c.getDimensao()<<endl;
    //cout<<c.getAsString();
    c.setNFolhas(79);
    c.setNFolhas(-1); //erro
    cout<<c.getAsString();
    //cout<<"nFolhas: "<<c.getNFolhas()<<endl;
    //cout<<Caderno::getContador()<<endl;
    //cout<<Caderno::getContador()<<endl;
    c.insereTexto("Aula pratica de POO 1");
    c.mudaPagina();
    c.insereTexto("Aula pratica de POO 2");
    c.mudaPagina();
    c.insereTexto("Texto teste");
    cout<<"-------------------"<<endl;
    cout<<c.getTexto();
    cout<<"-------------------"<<endl;
    cout<<"NPalavras: "<<c.getNPalavras()<<endl;

    return 0;
}

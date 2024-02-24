#include <iostream>
using namespace std;

struct Tabela{
    int  num[5][5]{};
};
typedef struct Tabela Tab;

void preenche(Tab &t, int p){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            t.num[i][j]=p;
        }
    }
}

void mostra(const Tab &t){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            cout<<t.num[i][j]<<" ";
        }
        cout<<" "<<endl;
    }
}

void mostra_valor_especifico(const Tab &t){
    int numi, numj;

    do{
        cout<<"Introduza uma coluna: "<<endl;
        cin>>numi;
    }while(numi<0 || numi>5);

    do{
        cout<<"Introduza uma linha: "<<endl;
        cin>>numj;
    }while(numi<0 || numi>5);

    cout<<t.num[numi][numj];
}

void altera_valor(Tab &t){
    int numi, numj, novo;

    do{
        cout<<"Introduza uma coluna: "<<endl;
        cin>>numi;
    }while(numi<0 || numi>5);

    do{
        cout<<"Introduza uma linha: "<<endl;
        cin>>numj;
    }while(numi<0 || numi>5);

    cout<<"Introduza o valor novo: "<<endl;
    cin>>novo;

    t.num[numi][numj]=novo;
}

void mostra_valor_especificov2(const Tab &t, const int &lin, const int &col){
    if(lin<0||lin>5||col<0||col>5){
        cout<<"Erro nas linhas ou colunas."<<endl;
        return;
    }
    else{
        cout<<"mostra_valor_especificov2: "<<t.num[lin][col]<<endl;
    }
}

void altera_valorv2(Tab &t, const int &lin, const int &col, const int &novo){
    if(lin<0||lin>5||col<0||col>5){
        cout<<"Erro nas linhas ou colunas."<<endl;
        return;
    }
    else if(novo<0||novo>10){
        cout<<"Erro no novo valor."<<endl;
        return;
    }
    t.num[lin][col]=novo;
}

int &elementoEm(Tab &t, const int &lin, const int &col){
    static int x=5;
    if(lin<0||lin>4||col<0||col>4){
        cout<<"Erro nas linhas ou colunas."<<endl;
        return x;
    }

    return t.num[lin][col];
}

class Tabela_class {
    int  num[5][5];
public:
    Tabela_class();
    void preenche_class(int p);
    void mostra_class();
    int &elementoEm_class(const int &lin, const int &col);
};

Tabela_class::Tabela_class(){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            this->num[i][j]=0;
        }
    }
}

int &Tabela_class::elementoEm_class(const int &lin, const int &col){
    static int x=5;
    if(lin<0||lin>4||col<0||col>4){
        cout<<"Erro nas linhas ou colunas."<<endl;
        return x;
    }

    return this->num[lin][col];
}

void Tabela_class::preenche_class(int p){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            this->num[i][j]=p;
        }
    }
}

void Tabela_class::mostra_class(){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            cout<<this->num[i][j]<<" ";
        }
        cout<<" "<<endl;
    }
}

class Aluno { //exemplo da aula
    string nome;
    bool presencas[26];
    int naluno;
public:
    Aluno(){
        cout<<"construtor por omissao"<<endl;
        naluno=0;
        for(int i=0;i<26;i++){
            presencas[i]=false;
        }
    }

    Aluno(string n, int naluno){
        if(!setNome(n)){
            nome="Invalido";
        }
        if(!setAluno(naluno)){
            naluno=9999;
        }
    }

    int setAluno(int n){
        naluno=n;
    }

    int getAluno(){
        return naluno;
    }

    string getNome(){
        return nome;
    }

    bool setNome(string novo){
        if(nome.length()>0 && novo[0]>='A' && novo[0]<='Z'){
            nome=novo;
            return true;
        }
        return false;
    }

    bool &getPresenca(int indice){
        static bool resultado=false;

        if(indice>=0 && indice <26){
            return presencas[indice];
        }

        return resultado;
    }

};

int main() {
    int num, numi, numj;
    //Tab t;
    Tabela_class t;
    Aluno a, b("Ezequiel", 13);
    string ola, ola2("Ana");

    //cout<<"Introduza um num para preencher: " <<endl;
    //cin>>num;
    //t.preenche_class(num);
    //mostra(t);
    //mostra_valor_especifico(t);
    //altera_valor(t);
    //mostra_valor_especificov2(t, 2, 2);
    //altera_valorv2(t, 2, 2, 9);
    //mostra(t);
    //cout << elementoEm(t, 4, 4)<<endl;
    //elementoEm(t, 4, 7) = 9;
    //cout << elementoEm(t, 4, 4)<<endl;
    //mostra(t);
    //cout << "Valor do elemento a substituir: "<<t.elementoEm_class(2, 2)<<endl;
    //t.elementoEm_class(2, 2) = 9; //elementoEm(t, 4, 7) = 9; //execucao com erro propositado
    //cout << "Valor do elemento depois de substituir: "<<t.elementoEm_class(2, 2)<<endl;
    //t.mostra_class();

    cout<<a.getNome()<<endl;
    cout<<a.getAluno()<<endl;
    if(a.setNome("!!!")){
        cout<<"Nome alterado."<<endl;
    }
    a.getPresenca(30);
    a.getPresenca(10)=true;


    return 0;
}

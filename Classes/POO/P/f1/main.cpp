#include <iostream>
#include <string>
#include <sstream>

using namespace std;

void ex1(){
    int n;
    char nome[10];
    //printf("nome e idade= ");
    //scanf(" %s %d", nome, &n);
    printf("nome= ");
    scanf(" %s", nome);
    printf("idade= ");
    scanf(" %d", &n);
    printf("%s e %d", nome, n);
}

void ex2(){ //com array de chars
    int idade;
    char nome[100];
    cout<<"Introduza o nome: "<<endl;
    cin.getline(nome, 100, '\n');//getline(cin, nome);
    cout<<"Introduza a idade: "<<endl;
    cin>>idade;

    while(cin.fail()){
        cout<<"Erro a ler idade."<<endl;
        cin.clear();
        cin.ignore(100, '\n');
        cout<<"Introduza a idade: "<<endl;
        cin>>idade;
        //return; //return success!
    }

    cout<<nome<<" com "<<idade<<" anos."<<endl;
}

void ex3(){//sem arrays de chars (com strings)
    int idade, flag=1;
    string nome;
    cout<<"Introduza o nome: "<<endl;
    getline(cin, nome);

    while(flag){
        if(nome[0]!='a') {
            cout << "Nome invalido." << endl;
            cout << "Introduza o nome: " << endl;
            getline(cin, nome);
        }
        else{
            flag=0;
        }
        //return; //return success!
    }

    cout<<"Introduza a idade: "<<endl;
    cin>>idade;

    while(cin.fail()){
        cout<<"Erro a ler idade."<<endl;
        cin.clear();
        cin.ignore(100, '\n');
        cout<<"Introduza a idade: "<<endl;
        cin>>idade;
        //return; //return success!
    }

    cout<<nome<<" com "<<idade<<" anos."<<endl;
}

void imprime(string s){
    cout<<s<<endl;
}

void imprime(string s, int i){
    cout<<s<<i<<endl;
}

void imprime(int i, string s){
    cout<<i<<s<<endl;
}

int somaoverload(){
    return 0;
}

int somaoverload(int x){
    return x;
}

int somaoverload(int x, int y){
    return x+y;
}

int somaoverload(int x, int y, int z){
    return x+y+z;
}

int somaomissao(int x=0, int y=0, int z=0){
    return x+y+z;
}

int somamista(int x, int y=0, int z=0){
    return x+y+z;
}

int somamista(){
    return 0;
}

void troca(int &a, int &b){
    int aux;
    aux=a;
    a=b;
    b=aux;
}

void troca2(int *a, int *b){
    int aux;
    aux=*a;
    *a=*b;
    *b=aux;
}

void imprimeref(const string &s){ //funcao recebe por referencia apesar de nao ir alterar o valor. ganha-se eficiencia.
    cout<<s<<endl;
} //const só protege para enventuais tentativas de mudar o valor dos parametros quando não é suposto ser mudado

void imprimeref(const string &s, const int &i){ //funcao recebe por referencia apesar de nao ir alterar o valor. ganha-se eficiencia.
    cout<<s<<i<<endl;
} //const só protege para enventuais tentativas de mudar o valor dos parametros quando não é suposto ser mudado

void imprimeref(const int &i, const string &s){ //funcao recebe por referencia apesar de nao ir alterar o valor. ganha-se eficiencia.
    cout<<i<<s<<endl;
} //const só protege para enventuais tentativas de mudar o valor dos parametros quando não é suposto ser mudado

void ex7(const string & nome){
    stringstream ss(nome);
    string pal;
    while(ss>>pal){
        cout<<pal<<endl;
        if(pal=="silva"){
            cout<<"Conheco alguem com esse nome!"<<endl;
        }
    }

}

void ex9() { //separar em duas funçoes!!
    string por_tratar, num_s, nums[10]{"um", "dois", "tres", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez"};
    int num=0;
    char c;

    while(true) {
        cout<<"Introduza um numero (pode ser por extenso):"<<endl;
        getline(cin, por_tratar);
        stringstream ss(por_tratar);

        if (por_tratar == "fim") {
            return;
        }
        else if (ss >> num) { //verifica se é numero
            switch (num) {
                case 1:
                    cout << "Um" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 2:
                    cout << "Dois" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 3:
                    cout << "Tres" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 4:
                    cout << "Quatro" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 5:
                    cout << "Cinco" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 6:
                    cout << "Seis" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 7:
                    cout << "Sete" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 8:
                    cout << "Oito" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 9:
                    cout << "Nove" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                case 10:
                    cout << "Dez" << endl;
                    cout<<"[Enter] para continuar"<<endl;
                    c=cin.get();
                    break;
                default:
                    cout << "De 1 a 10" << endl;
                    cout<<"Enter para continuar"<<endl;
                    c=cin.get();
                    break;

            }
        }
        else { //se não é numero, então é uma string
            stringstream ss2(por_tratar);
            ss2 >> num_s;
            for (int i = 0; i < 10; i++) {
                if (num_s == nums[i]) {
                    cout << i + 1 << endl;
                    cout<<"Enter para continuar"<<endl;
                    c=cin.get();
                }
            }
        }
    }
}

int main() {
    int a = 5, b = 10, num=0;
    string por_tratar;
    int &ra=a, &rb=b; //referencia -> ra é a, ra++ = a++
    string nome;
    //cout << "Hello, World!" << std::endl;
    //teste();
    //ex2();
    //ex3();
    //imprime("ola");
    //imprime("a idade e: ", 25);
    //imprime(100, " euros");
    //cout << "\n" << somaoverload() <<" "<< somaoverload(1)<<endl;
    //cout << somaoverload(1,2) <<" "<< somaoverload(1,2,3)<<endl;
    //cout << "\n" << somaomissao() <<" "<< somaomissao(1)<<endl;
    //cout << somaomissao(1,2) <<" "<< somaomissao(1,2,3)<<endl;
    //cout << "\n" << somamista() <<" "<< somamista(1)<<endl;
    //cout << somamista(1,2) <<" "<< somamista(1,2,3)<<endl;
    //cout << "ref &a= "<<&a<<" e "<<"ref &b= "<<&b<<endl;
    //troca(a,b); //versao referencia (para usar a partir de agora)
    //cout << "\na = " << a <<"ref &a= "<<&a<< "\nb = " << b<<"ref &b= "<<&b<<endl;
    //troca2(&a,&b); //versao ponteiro
    //cout << "\na = " << a << "\nb = " << b;
    //imprime("ola");
    //imprime("a idade e: ", 25);
    //imprime(100, " euros");
    //cout<<"Introduza o nome separado por espacos: "<<endl;
    //getline(cin, nome);
    //ex7(nome);
    ex9();

    return 0;
}

/*
 * int preenche (int a){...} - 1
 * int preenche (string s){...} - 2
 * preenche(4)->chama funçao 1
 * preenche("ola")->chama funçao 2
 * preenche('a')->chama funçao 1
 * preenche(17.4)-> chama funcao 1
 *
 * teste(int a, string b=" ") - 3
 * teste(double i=10.4) - 4
 * teste()->chama funçao 3
 *
 * no ex 5 para manter as duas versoes (omissao e overload) basta ter em atencao os casos de colisao.
 *
 * istringstream -> extração de partes de uma string
 * ostringstream -> acumular texto num buffer
*/
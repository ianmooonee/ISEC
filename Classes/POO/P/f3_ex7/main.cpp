#include "Pessoa.h"
#include "Banco.h"

int main(){
    Pessoa a(12345, 123456, "Ana");
    Pessoa b(23456, 789101, "Pedro");
    //usar vetor<Pessoa*> pessoas;
    //b = a;
    Banco c("CGD");
    Banco d("BPI");
    //Ana é cliente de dois bancos
    c.novaConta(a);
    d.novaConta(a);
    c.novaConta(b);
    //Ana tenta abrir nova conta no mesmo banco
    //d.novaConta(a); //Não deve deixar
    //cout << c.getAsString();
    //cout << d.getAsString();
    //cout<<endl;
    //cout<<"Ana mudou de nome para Ana Rita"<<endl;
    //cout<<endl;
    //Ana altera de nome
    a.setNome("Ana Rita");
    //As contas dos dois bancos tem
    //acesso a esta nova informação?
    //cout << c.getAsString();
    //cout << d.getAsString();
    c.depositarBanco(12345, 1000);
    c.depositarBanco(23456, 190);
    d.depositarBanco(23456, 190); //não existe a conta com cc 23456 no banco d
    cout << c.getAsString();
    cout << "Saldo total no banco c: "<<c.getSaldoTotal()<<endl;
    cout << d.getAsString()<<endl;
    c.eliminarConta(12345);
    cout << c.getAsString();

    //Leitura de um ficheiro
    //ifstream fich("pessoas.txt");

    /*vector<Pessoa> lista;
    //int npessoas=0;
    //if(fich){
        string line, nome;
        long bi, nif;
        while(getline(fich, line) && npessoas < 10){
            istringstream buffer(line);
            //BIF NIF Nome Completo
            if (buffer >> bi && buffer >> nif &&
                getline(buffer,nome) && nome.lenth() > 0) {
                lista.push_back(Pessoa(bi, nif, nome));
                npessoas++;

            }
        }

        cout << "\nLidos do ficheiro:\n";
        for(const Pessoa & p: lista)
            cout << p.getAsString() << endl;
        //Remover uma pessoa do vector - a primeira, n„o permite se os membros forem const, porque usa
        //o operador=
        //lista.erase(lista.begin());
    }
    ofstream fich2("log.txt");
    if (fich2) {
        for (const Pessoa & p : lista)
            fich2 << p.getAsString() << endl;

    }*/
    return 0;
}

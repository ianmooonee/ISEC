//
// Created by ianmoone on 17/11/2021.
//

#include "Controlador.h"
#include "utils.h"

Controlador::Controlador(Ilha& i){
    cout << "\n|+ Criacao da Ilha! +|\n";
    comandos(i);
}

void Controlador::cons(Ilha &i, const string &tipoedi, const int &cordx, const int &cordy){
    if(!i.constroiEdi(tipoedi, cordx, cordy)){
        cout<<"Construcao falhou!"<<endl;
    }
}

void Controlador::cont(Ilha &i, const string &tipoTrab){
    if(!i.contratarTrab(tipoTrab)){
        cout<<"O tipo de trabalhador "<<tipoTrab<<" ainda nao existe ou nao foi implementado!"<<endl;
    }
}

void Controlador::list(Ilha &i, istringstream &ss){
    int cordx, cordy;

    if(ss>>cordx && ss>>cordy) {
        cout<<i.ZonaAsString(cordx, cordy)<<endl;
    }
    else {
        cout << i.getAsString() << endl;
    }
}

void Controlador::execFich(Ilha &i, const string &nomeFich){
    cout<<"Funcao de executar comandos de um ficheiro"<<endl;
    ifstream file;
    string linha, comando, tipoTrab, tipoEdi;
    int cordx, cordy;

    file.open(nomeFich);
    if(!file){
        cout<<"Erro a abrir ficheiro de configuracao."<<endl;
    }

    while(getline(file, linha)){
        istringstream ss(linha);
        ss>>comando;

        if(comparador(comando,"cont") && ss>>tipoTrab){
            cont(i, tipoTrab);
        }
        else if(comparador(comando,"cons") && ss>>tipoEdi && ss>>cordx && ss>>cordy){
            cons(i, tipoEdi, cordx, cordy);
        }
        else if(comparador(comando, "list")){
            list(i, ss);
        }
    }

    file.close();
}


void Controlador::comandos(Ilha& i){
    string tipo, comando, tipoTrab, tipoedi, zona, nomeFich, token, nomeTrab;
    int cordx=-1, cordy=-1, quantia;
    do {

        cout << "[Comando]:";
        string linha;
        getline(cin, linha);
        istringstream ss(linha);
        ss >> comando;
        if (comparador(comando, Comandos[0])) {
            //exec
            ss>>nomeFich;
            execFich(i, nomeFich);
            nomeFich="";
        }
        else if (comparador(comando, Comandos[1])) {
            //cons
            ss>>tipoedi;
            ss>>cordx;
            ss>>cordy;
            cons(i, tipoedi, cordx, cordy);
            cordx=-1; cordy=-1;
        }
        else if (comparador(comando, Comandos[2])) {
            //liga
            ss>>cordx;
            ss>>cordy;
            i.ligaEdi(cordx, cordy);
            cordx=-1; cordy=-1;
        }
        else if (comparador(comando, Comandos[3])) {
            //des
            ss>>cordx;
            ss>>cordy;
            i.desligaEdi(cordx, cordy);
            cordx=-1; cordy=-1;
        }
        else if (comparador(comando, Comandos[4])) {
            //move
            ss>>nomeTrab;
            ss>>cordx;
            ss>>cordy;
            i.moverTrabalhador(nomeTrab, cordx, cordy);
            cordx=-1; cordy=-1;
        }
        else if (comparador(comando, Comandos[5])) {
            //vende
            ss>>cordx;
            ss>>cordy;
            i.vendeEd(cordx, cordy);
            cordx=-1; cordy=-1;
        }
        else if (comparador(comando, Comandos[6])) {
            //cont
            ss>>tipoTrab;
            cont(i, tipoTrab);
            tipoTrab="";
        }
        else if (comparador(comando, Comandos[7])) {
            //list
            list(i, ss);
        }
        else if (comparador(comando, Comandos[8])) {
            //next
            i.avancaDia();
        }
        else if (comparador(comando, Comandos[9])) {
            //save
            cout << "Save por implementar" << endl;
        }
        else if (comparador(comando, Comandos[10])) {
            //load
            cout << "Load por implementar" << endl;
        }
        else if (comparador(comando, Comandos[11])) {
            //apaga
            cout << "Apaga por implementar" << endl;
        }
        else if (comparador(comando, Comandos[12])) {
            //config
            cout << "Config por implementar" << endl;
        }
        else if (comparador(comando, Comandos[13])) {
            //debcash
            ss>>quantia;
            i.setDinheiroDeb(quantia);
        }
        else if (comparador(comando, Comandos[14])) {
            //debed
            ss>>tipoedi;
            ss>>cordx;
            ss>>cordy;
            if(i.constroiEdiDeb(tipoedi, cordx, cordy))
                cout<<"Edificio adicionado."<<endl;
        }
        else if (comparador(comando, Comandos[15])) {
            //debkill
            ss>>nomeTrab;
            i.delTrab(nomeTrab);
        }
        else if (comparador(comando, Comandos[16])) {
            //terminar
            cout << "Terminado." << endl;
            break;
        }
        else {
            cout << "Comando nao reconhecido!" << endl;
        }

    } while (comando != Comandos[16]);
}
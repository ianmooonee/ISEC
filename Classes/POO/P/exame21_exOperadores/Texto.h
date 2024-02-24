//
// Created by ianmoone on 19/11/2021.
//

#ifndef EXAME21_EXOPERADORES_TEXTO_H
#define EXAME21_EXOPERADORES_TEXTO_H
#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
#include <cmath>
#include <fstream>
using namespace std;

class Texto {
    string palavra;
    vector<string> palavras;
public:
    Texto(const string &pal=""):palavra(pal){};

    Texto(const vector<string> &palavras): palavras(palavras){}

    string getAsString() const;
};


#endif //EXAME21_EXOPERADORES_TEXTO_H

//
// Created by ianmoone on 16/11/2021.
//

#include "utils.h"

bool comparador(string s1, string s2) {
    //converter as strings para lower case
    transform(s1.begin(), s1.end(), s1.begin(), ::tolower);
    transform(s2.begin(), s2.end(), s2.begin(), ::tolower);
    if (s1.compare(s2) == 0)
        return true;
    return false;
}
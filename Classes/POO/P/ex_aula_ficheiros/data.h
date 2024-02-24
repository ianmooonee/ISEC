//
// Created by Ana Oliveira Alves on 03/11/2021.
//

#ifndef DUVIDAS_DATA_H
#define DUVIDAS_DATA_H
#include <string>

class data {
    int d, m, a;
public:
    data(int d, int m, int a);
    std::string getAsString() const;

    virtual ~data();
};


#endif //DUVIDAS_DATA_H

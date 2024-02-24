#include <iostream>
#include <sstream>
#include <vector>
using namespace std;

int main() {
    vector<int> valores = {1, 2, 3, 4, 5, 10};
    auto ptr=valores.begin();

    for (int i = 0; i < valores.size(); i++) {
        cout << valores.at(i) << " ";
    }
    cout << endl;

    /*for(int j=0; j<valores.size(); j++){ //ciclo infinito
        cout<<valores.at(j)<<" ";
        if(valores.at(j)%2==0){
            valores.push_back(valores.at(j));
        }
    }*/

    /*for (int j = 0; j < valores.size(); j++) { //apaga os pares
        if (valores.at(j) % 2 == 0) {
            valores.erase(valores.begin()+j);
        }
    }*/

    while(ptr!=valores.end()){
        cout<<*ptr<<"\t";
        if(*ptr%2==0){
            valores.push_back(*ptr);
        }
        ptr++;
    }

    for (int k = 0; k < valores.size(); k++) {
        cout << valores.at(k) << " ";
    }

    return 0;
}
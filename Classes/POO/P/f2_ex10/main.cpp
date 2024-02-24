#include <iostream>
#include <string>
#include <sstream>
#include <initializer_list>
#include <vector>
using namespace std;

class Eleitor{
    int num;
    vector<int> nums;
public:
    //TV(initializer_list<string> canal):canais(canal)
    Eleitor(initializer_list<int> numel):nums(numel){
        for(auto i:nums)
            this->num=i;
    }

    string getAsString(){
        ostringstream buffer;
        buffer<<"Eleitores: ";
        for(const int &n:nums){
            buffer<<n<<" ";
        }
        return buffer.str();
    }

    void elimina(){
        for(auto ptr=nums.begin(); ptr!=nums.end();){
            if(*ptr%2==0){
                ptr=nums.erase(ptr);
            }
            else{
                ptr++;
            }
        }
    }

};

int main() {

    Eleitor l1({});
    Eleitor l2({1001,1002,1003,1004,9999});
    Eleitor l3({1001,1002,1003,1003,9999});
    //l2.elimina(); //elimina valores pares do vetor l2
    cout<<l1.getAsString()<<endl;
    cout<<l2.getAsString()<<endl;
    cout<<l3.getAsString()<<endl;

    return 0;
}

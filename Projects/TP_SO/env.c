#include "env.h"
#include <stdio.h>
#include <stdlib.h>

char* getenv_prom(){
    char* fich_prom;

    if ((fich_prom = getenv("FPROMOTERS")) != NULL) {
        return fich_prom;    
    }

    return NULL;
}

char* getenv_items(){
    char* fich_items;

    if ((fich_items = getenv("FITEMS")) != NULL) {
        return fich_items;    
    }

    return NULL;
}

char* getenv_promotores(){
    char* fich_items;

    if ((fich_items = getenv("FPROMOTERS")) != NULL) {
        return fich_items;    
    }

    return NULL;
}

char* getenv_users(){
    char* fich_users;

    if ((fich_users = getenv("FUSERS")) != NULL) {
        return fich_users;    
    }

    return NULL;
}

int getenv_heartbeat(){
    char *hb;

    if ((hb = getenv("HEARTBEAT")) != NULL){
        if(atoi(hb)!=0){
            return atoi(hb);
        }
    }

    return 0;
}
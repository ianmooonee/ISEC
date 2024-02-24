#define FIFO_SERVER "FIFOSERVER"

typedef struct{
    char op;
    int n1;
    int n2;
    int pid;
    pthread_mutex_t *mut;
} Msg, *pMsg;

typedef struct{
    int res;
} Res, *pRes;
#ifndef _FRONTEND_H_
#define _FRONTEND_H_

#include "geral.h"

void terminar();
int validaUser(User user);
void sair(int sign, siginfo_t *i, void *secret);

#endif
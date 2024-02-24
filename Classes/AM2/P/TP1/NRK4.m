function y = MRK4(f,a,b,n,y0)
%RK2 Método de Runge-Kutta de ordem 4 para resolução numérica de EDO/PVI
%   y'=f(t,y), t=[a,b], y(a)=y0
%   k1=hf(ti,yi)
%   k2=h*f(t(i)+(h/2),y(i)+(1/2)*k1);
%   k3=h*f(t(i)+(h/2),y(i)+(1/2)*k2);
%   k4=h*f(t(i+1),y(i)+k3);
%   y(i+1)=(1/6)*(k1+2*k2+2*k3+k4);
%INPUT:
%   f - função da EDO y'=f(t,y)
%   [a,b] - intervalo de valores da variável independente t
%   n - número de subintervalos ou iterações do método
%   y0 - aproximação inicial y(a)=y0
%OUTPUT:
%   y - vetor das soluções aproximadas do PVI em cada um dos t(i)
%
%   14/04/2021  Nelson Simão

h=(b-a)/n;                                    %tamanho de cada intervalo ou distancia entre dois pontos consecutivos
t=a:h:b;                                      %vetor dos pontos de a até b com intervalo h
y=zeros(1,n+1);                               %t0 até tn, ou seja de a até b
y(1) = y0;                                    %a primeira solução é nos dada
for i =1:n                                    %n iterações
    k1=h*f(t(i),y(i));                        %calculo da inclinação no inicio do intervalo
    k2=h*f(t(i)+(h/2),y(i)+(1/2)*k1);         %calculo da inclinação no ponto medio do intervalo
    k3=h*f(t(i)+(h/2),y(i)+(1/2)*k2);         %calculo da inclinação, outra vez, no ponto medio do intervalo
    k4=h*f(t(i+1),y(i)+k3);                   %calculo da inclinação no final do intervalo
    y(i+1)=y(i)+(1/6)*(k1+2*k2+2*k3+k4);      %media das inclinações e solução do PVI no ponto t(i)
end
end

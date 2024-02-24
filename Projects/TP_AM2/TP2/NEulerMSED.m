%N_HeunSED  M�todo N�merico para resolver um SED: Heun (Euler melhorado)
%   [t,u,v] = N_HeunSED(f,g,a,b,n,u0,v0) M�todo num�rico para a
%   resolu��o de um SED (ordem 2)
%   u'= f(t,u,v), v'=g(t,u,v), t=[a, b], u(a)=u0 e v(a)=v0  
%
%INPUT:
%   f, g - fun��es do 2.� membro das Equa��es Diferenciais
%   [a, b] - extremos do intervalo da vari�vel independente t
%   n - n�mero de subintervalos ou itera��es do m�todo
%   u0, v0 - condi��es iniciais t=a -> u=u0 e v=v0
%
%OUTPUT: 
%   [t,u,v] - vector das solu��es aproxima��es e da discretiza��o de t
%

function [t,u,v] = NEulerMSED(f,g,a,b,n,u0,v0)
    h = (b-a)/n;                                       
    
    t = a:h:b;                                        
    u = zeros(1, n+1);                               
    v = zeros(1, n+1);                             
   
    u(1) = u0;                                      
    v(1) = v0;                                      
    
    for i=1:n                                        
        uK1 = f(t(i),u(i),v(i));                    
        vK1 = g(t(i),u(i),v(i));                   
        
        uK2 = f(t(i+1), u(i) + uK1*h, v(i) + vK1*h);   
        vK2 = g(t(i+1), u(i) + uK1*h, v(i) + vK1*h);   
        
        uK = 0.5*(uK1 + uK2);                       
        vK = 0.5*(vK1 + vK2);                        
        
        u(i + 1) = u(i) + h*uK;                        
        v(i + 1) = v(i) + h*vK;                        
    end
end
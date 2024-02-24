%N_RK4SED  M�todo N�merico para resolver um SED: Runge-Kutta de ordem 4
%   [t,u,v] = N_RK4SED(f,g,a,b,n,u0,v0) M�todo num�rico para a
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

function [t,u,v] = NRK4SED(f,g,a,b,n,u0,v0)
    h = (b-a)/n;                                                   
    
    t = a:h:b;                                                    
    u = zeros(1, n+1);                                              
    v = zeros(1, n+1);                                              
    
    u(1) = u0;                                                      
    v(1) = v0;                                                   
    
    for i=1:n                                                       
        uK1 = h*f(t(i), u(i), v(i));                               
        vK1 = h*g(t(i), u(i), v(i));                               
        
        uK2 = h*f(t(i) + h/2, u(i) + 0.5*uK1, v(i) + 0.5*vK1);     
        vK2 = h*g(t(i) + h/2, u(i) + 0.5*uK1, v(i) + 0.5*vK1);    
        
        uK3 = h*f(t(i) + h/2, u(i) + 0.5*uK2, v(i) + 0.5* vK2);     
        vK3 = h*g(t(i) + h/2, u(i) + 0.5*uK2, v(i) + 0.5* vK2);  
        
        uK4 = h*f(t(i+1), u(i) + uK3, v(i) + vK3);                  
        vK4 = h*g(t(i+1), u(i) + uK3, v(i) + vK3);                  
        
        uK = (uK1 + 2*uK2 + 2*uK3 + uK4)/6;                       
        vK = (vK1 + 2*vK2 + 2*vK3 + vK4)/6;                      
        
        u(i + 1) = u(i) + uK;                                     
        v(i + 1) = v(i) + vK;                                      
    end
end

%NPMI
%
%INPUT:
%   f - Fun��o da equa��o diferencial, em t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - Numero de sub-intervalos
%   y0 - Condi��o Inicial do PVI
%
%OUTPUT: 
%   y - Vector das solu��es aproximadas
%
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt

function y = N_PM(f,a,b,n,y0)

    h = (b-a)/n;                                                    % Tamanho de cada subintervalo (passo)
    
    t = a:h:b;                                                      % Aloca��o de mem�ria - vetor das abcissas
    y = zeros(1, n+1);                                              % Aloca��o de mem�ria - vetor das ordenadas
    
    y(1) = y0;                                                      % O primeiro valor de y � sempre y0 (condi��o inicial do pvi)

    for i=1:n                                                       % O n�mero de itera��es vai ser igual a n
        k1 = 0.5 * f(t(i), y(i));                                   
        y(i+1) = y(i) + h*f(t(i) + h/2, y(i) + h*k1);               % Ponto m�dio expl�cito
        y(i+1) = y(i) + h*f(t(i) + h/2, 0.5*(y(i) + y(i+1)));       % Ponto m�dio impl�cito e pr�ximo valor aproximado da solu��o do problema original
    end



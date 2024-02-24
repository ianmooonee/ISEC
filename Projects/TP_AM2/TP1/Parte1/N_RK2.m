%NRK2
%
%INPUT:
%   f - Fun��o da equa��o diferencial em fun��o de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - N�mero de sub-intervalos
%   y0 - Condi��o inicial do PVI
%
%OUTPUT: 
%   y - Vector das solu��es aproximadas
%
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt

function y = N_RK2(f,a,b,n,y0)
    h = (b-a)/n;                            % Tamanho de cada subintervalo
   
    t = a:h:b;                              % Vetor das abcissas - aloca��o de mem�ria
    y = zeros(1, n+1);                      % Vetor das ordenadas - aloca��o de mem�ria
        
    y(1) = y0;                              % Condi��o inicial do pvi
    
    for i=1:n                               % N itera��es
        k1 = h * f(t(i), y(i));             % Declive no in�cio do intervalo
        k2 = h * f(t(i + 1), y(i) + k1);    % Declive no fim do intervalo
        
        y(i + 1)=y(i) + (k1 + k2)/2;        % Pr�ximo valor aproximado
    end
end

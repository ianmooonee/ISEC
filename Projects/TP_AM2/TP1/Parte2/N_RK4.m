%NRK4
%
%INPUT:
%   f - Função da equação diferencial em função de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - Número de sub-intervalos
%   y0 - Condição inicial do PVI
%
%OUTPUT: 
%   y - Vetor das soluções aproximadas
%
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt

function y = N_RK4(f,a,b,n,y0)
    h = (b-a)/n;                                        % Tamanho de cada subintervalo
    
    t = a:h:b;                                          % Vetor das abcissas - alocação de memória
    y = zeros(1, n+1);                                  % Vetor das ordenadas - alocação de memória
    
    y(1) = y0;                                          % Condição inicial do pvi

    for i=1:n                                           % N iterações
        k1 = h*f(t(i), y(i));                           % Declive no início do intervalo
        k2 = h*f(t(i) + h/2, y(i) + 0.5*k1);            % Declive no ponto médio do intervalo
        k3 = h*f(t(i) + h/2, y(i) + 0.5*k2);            % Declive novamente no ponto médio do intervalo
        k4 = h*f(t(i+1), y(i) + k3);                    % Declive no final do intervalo
        
        y(i + 1) = y(i) + (k1 + 2*k2 + 2*k3 + k4)/6;    % Próximo valor aproximado
    end
end

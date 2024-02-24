%NEulerM
%
%INPUT:
%   f - Função da equação diferencial em função de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - Número de sub-intervalos
%   y0 - Condição inicial do PVI
%
%OUTPUT: 
%   y - Vector das soluções aproximadas
%
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt

function y = N_EulerM(f,a,b,n,y0)
    h = (b-a)/n;                     % Tamanho dos sub-intervalos
    
    t = a:h:b;                       % Vetor das abcissas - alocação de memória
    y = zeros(1, n+1);               % Vetor das ordenadas - alocação de memória
    
   
    y(1) = y0;                       % Condição inicial do pvi
    
    for i=1:n                        % N interações
        k1 = f(t(i),y(i));           % Declive no início do intervalo
        k2 = f(t(i+1), y(i) + k1*h); % Declive no fim do intervalo
        k = 0.5*(k1+k2);             % Cálculo da média das inclinações
        y(i+1)=y(i)+h*k;             % Próximo valor aproximado
    end
end
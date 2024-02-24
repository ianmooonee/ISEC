%NODE45
%
%INPUT:
%   f - Função da equação diferencial em função de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - Numero de sub-intervalos
%   y0 - Condição inicial do PVI
%
%OUTPUT: 
%   y - Vetor das soluções aproximadas
%
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt

function y = N_ODE45(f,a,b,n,y0)
    h = (b-a)/n;                        % Tamanho dos sub-intervalos
    
    t = a:h:b;                          % Vetor das abcissas
    
    [~,y] = ode45(f, t, y0);            % Aproximação através da função ODE45
    y = y';                             % Transpor o vetor
end
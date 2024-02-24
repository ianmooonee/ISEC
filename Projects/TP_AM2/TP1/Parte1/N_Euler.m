%NEuler
%
%INPUT:
%   f - Função da equação diferencial em função de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - Número de sub-intervalos
%   y0 - Condição inicial do PVI
%
%OUTPUT: 
%   y - Vetor das soluções aproximações
%
%   03/03/2020 - 
%                ArménioCorreia .: armenioc@isec.pt 
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt


function y = N_Euler(f,a,b,n,y0)
    h = (b-a)/n;                        % Tamanho dos sub-intervalos
	
    t = a:h:b;                          % vetor das abcissas - alocação de memória
    y = zeros(1, n+1);                  % vetor das ordenadas - alocação de memória
	
    y(1) = y0;                          % Condição inicial do pvi
    
    for i=1:n                           % n iterações
        y(i+1)=y(i)+h*f(t(i),y(i));     % Aproximação do método de Euler para a iésima iteração
    end
end

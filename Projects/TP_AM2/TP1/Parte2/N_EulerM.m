%NEulerM
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

function y = N_EulerM(f,a,b,n,y0)
    h = (b-a)/n;                     % Tamanho dos sub-intervalos
    
    t = a:h:b;                       % Vetor das abcissas - aloca��o de mem�ria
    y = zeros(1, n+1);               % Vetor das ordenadas - aloca��o de mem�ria
    
   
    y(1) = y0;                       % Condi��o inicial do pvi
    
    for i=1:n                        % N intera��es
        k1 = f(t(i),y(i));           % Declive no in�cio do intervalo
        k2 = f(t(i+1), y(i) + k1*h); % Declive no fim do intervalo
        k = 0.5*(k1+k2);             % C�lculo da m�dia das inclina��es
        y(i+1)=y(i)+h*k;             % Pr�ximo valor aproximado
    end
end
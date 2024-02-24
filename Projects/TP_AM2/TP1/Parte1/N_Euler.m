%NEuler
%
%INPUT:
%   f - Fun��o da equa��o diferencial em fun��o de t e y
%   a - Limite esquerdo do intervalo
%   b - Limite direito do intervalo
%   n - N�mero de sub-intervalos
%   y0 - Condi��o inicial do PVI
%
%OUTPUT: 
%   y - Vetor das solu��es aproxima��es
%
%   03/03/2020 - 
%                Arm�nioCorreia .: armenioc@isec.pt 
%   19/04/2022 - 
%                Marinela Bettencourt .: a2020110142@isec.pt
%                Ana Silva .: a2021154586@isec.pt
%                Pedro Serrano .: a21260364@isec.pt


function y = N_Euler(f,a,b,n,y0)
    h = (b-a)/n;                        % Tamanho dos sub-intervalos
	
    t = a:h:b;                          % vetor das abcissas - aloca��o de mem�ria
    y = zeros(1, n+1);                  % vetor das ordenadas - aloca��o de mem�ria
	
    y(1) = y0;                          % Condi��o inicial do pvi
    
    for i=1:n                           % n itera��es
        y(i+1)=y(i)+h*f(t(i),y(i));     % Aproxima��o do m�todo de Euler para a i�sima itera��o
    end
end

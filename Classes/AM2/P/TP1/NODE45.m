%===================Descrição====================%
%                                                %
%   O M_ODE45 é um método númerico para          %
%   resolver um PVI com base na função           %
%   integrada ODE45 do MATLAB                    %
%                                                %
%   y = M_ODE45(f,a,b,n,y0)                      %
%                                                %
%====================INPUT========================
%                                                %
%   y0 - Valor inicial do PVI                    %
%   f - Função em t e y                          %
%   n - n sub-intervalos                         %
%   a - Limite esquerdo do [a,b]                 %
%   b - Limite direito do [a,b]                  %
%                                                %
%                                                %
%====================OUTPUT=======================
%                                                %
%   y - vetor das soluções aproximadas           %
%                                                %
%===================Feito por=====================
%                                                %
%        Kylix Afonso a2020146228@isec.pt        %
%        Nelson a2020...@isec.pt                 %
%        Gabriel Lameiras a2020...@isec.pt       %
%                                                %
%=================Em 19/04/2021==================%

function y = M_ODE45(f,a,b,n,y0)
    h = (b-a)/n;               % Tamanho de cada subintervalo
    t = a:h:b;        % Pré-alocação da memória necessária ao vetor t, que corresponde a a, a+h, a+2h, ...,a+(n-1)h, b
    [~,y] = ode45(f, t, y0);   % Determinar resultados através da ODE45 e colocação desses valores no vetor y
    y = y';                    % Mudança da orientação do vetor
end
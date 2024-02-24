%interpolação a 2D
clear;
clc;

%leitiura de dados
%dados=readmatrix('Dados_Entalpia_interp2.xlsx');
pressao=readmatrix('Dados_Entalpia_interp2.xlsx', 'Range','D6:G6');
temperatura=readmatrix('Dados_Entalpia_interp2.xlsx', 'Range','C7:C11');
entalpia=readmatrix('Dados_Entalpia_interp2.xlsx', 'Range','D7:G11');

%interface
p=input('Introduza o valor pretendido para a pressão entre 1.00 e 1.60: ');
t=input('Introduza o valor pretendido para a temperatura entre 250 e 500: ');
e=interp2(pressao, temperatura, entalpia, p, t);
fprintf('Pressão: %.2f e Temperatura: %d -> Entalpia: %.2f\n', p, t, e);
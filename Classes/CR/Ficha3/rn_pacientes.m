function rn_pacientes()
%Funcao rn3b: cria, treina e testa uma RN feedforward
%usando as funcoes da NNTool

% limpar
clear;
close all;

% inicializar entrada
p = readmatrix('heart_train.csv', 'Delimiter', ',', 'DecimalSeparator', '.');
t = p(:, end)';
p(:, end)=[];
in=p';

% inicializar targets


%Criar RN chamada rede
rede=feedforwardnet([20 20 20], 'trainlm');

%Ajustar os parametros seguintes:

% FUNCAO DE ATIVACAO DA CAMADA DE SAIDA
for i=1:3
    rede.layers{i}.transferFcn='tansig';
end

% FUNCAO DE TREINO
rede.trainFcn='trainlm';

% NUMERO DE EPOCAS DE TREINO
rede.trainParam.epochs = 500;

% TODOS OS EXEMPLOS DE INPUT SAO USADOS NO TREINO
%rede.divideFcn = '';                 

%Treinar a rede
rede=train(rede, in, t);

% visualizar a rede
view(rede)

%Simular a rede e guardar o resultado na variavel y
y=rede(in);
save('rn_pac.mat', 'rede');

% Mostrar resultado
y = (y >= 0.5);
erro=perform(rede, t, y);
fprintf("Erro=%f\n", erro);

end

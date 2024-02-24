function [w, out_init, out_sim] = perceptrao3_aula()
%  Implementacao de um perceptrao para aprender as funcoes logicas AND, OR, NAND e XOR
%  Vector out_init devolve o resultado da simulacao inicial do perceptrao (aleatorio) 
%  Vector out_sim devolve o resultado da simulacao final do perceptrao (depois do treino)


%input: entradas possíveis das operações lógicas
in = (dec2bin(0:31, 5) - '0')';
n_in=length(in(:,1));
n_ex=length(in(1,:));

%Inicializacao dos pesos do perceptrao com valores aleatorios
w=rand(1, n_in+1);

%output de cada operação lógica
%a variavel 's' contem apenas informacao para criar o grafico

target=(mode(in, 1) >= 0.5);

%PARTE 1: Simulacao do perceptrao criado aleatoriamente
fprintf('**********************************\n')
fprintf('A SIMULAR O PERCEPTRAO ALEATORIO\n')
fprintf('**********************************\n\n')

out_init = zeros(1,n_ex);
for j=1:n_ex
		S = 1*w(1);
        for i=2:length(w)
            S=S+in(i-1,j)*w(i);
        end

        if S >= 0
            out_init(j) = 1;
        else 
			out_init(j) = 0;
        end
end


%PARTE 2: TREINO
fprintf('**********************************\n')
fprintf('A TREINAR O PERCEPTRAO\n')
fprintf('**********************************\n')

for it=1:100
    fprintf('Iteracao %d\n', it);
    
    out = zeros(1, n_ex);				% VECTOR ONDE SERAO COLOCADAS AS SAIDAS PARA OS 4 EXEMPLOS DE TREINO
    delta = zeros(1, n_ex);			% VECTOR ONDE SERAO COLOCADOS OS VALORES DO ERRO (DELTA)
   
	for j=1:n_ex
		S = 1*w(1);
        for i=2:length(w)
            S=S+in(i-1,j)*w(i);
        end
        
        if S >= 0
            out(j) = 1;
        else 
			out(j) = 0;
        end
        
        delta(j)=target(j)-out(j);
        w(1)=w(1)+1*delta(j);
       
		%AJUSTAR OS PESOS
        for i=2:length(w)
            w(i)=w(i)+1*in(i-1, j)*delta(j);
        end

    end
    
end

fprintf('********************************************************\n')
fprintf('FIM DO TREINO\n')
fprintf('********************************************************\n\n')

%PARTE 3: TESTE
%Usar os pesos obtidos com o treino
%Fornecer a mesma entrada in
%Guardar os resultados em out_sim
%Se a rede aprendeu correctamente o padrao out_sim = target

fprintf('**********************************\n')
fprintf('A TESTAR O PERCEPTRAO DEPOIS DA APRENDIZAGEM\n')
fprintf('**********************************\n\n')

out_sim = zeros(1,n_ex);

    for j=1:n_ex
		S = 1*w(1);
        for i=2:length(w)
            S=S+in(i-1,j)*w(i);
        end

        if S >= 0
            out_sim(j) = 1;
        else 
			out_sim(j) = 0;
        end
    end
end



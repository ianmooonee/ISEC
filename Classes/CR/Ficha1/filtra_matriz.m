function vec=filtra_matriz(matriz, opcao)
%Função que recebe uma matriz e gera um vetor com os elementos pares da martriz.
%Opção 1: com ciclos.
%Opção 2: sem ciclos.

switch opcao
    case 1
        vec=[];
        for coluna=1:size(matriz,2)
            for linha=1:size(matriz,1)
                value=matriz(linha, coluna);
                if mod(value, 2)==0
                    vec=[vec value]; %v = v anterior mais o valor novo
                end

            end
        end

    case 2
        vec=[];
        vec=matriz(mod(matriz,2)==0)';
end

end
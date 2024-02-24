function maior_num(opcao)
%Função que recebe um conjunto de números e imprime o maior no ecrã

    switch opcao
        case 1
            vec=[];
            while true
                num=input("Introduza um numero: ");
                if(num==0)
                    break;
                else
                    vec=[vec num];
                end  
            end
            m=max(vec) %imprimir numero
    
        case 2
            maior=0;
            while true
                num=input("Introduza um numero: ");
                if(num==0)
                    break;
                elseif(num>maior)
                    maior=num;
                end  
            end
            maior %imprimir numero
    end
end
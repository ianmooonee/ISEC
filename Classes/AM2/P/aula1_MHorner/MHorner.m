function P = MHorner(a, x)
%Método de Horner
%Avalia o valor de x num polinómio definido pelos coeficientes a.
%Inputs:
%   a: vetor de coeficientes (a0, a1, ..., an)
%   x: valor/valores (vetor) de objetos a calcular
%Outputs:
%   P: imagem/imagens (vetor) no/nos x dado/dados

n=length(a);
P=a(n);

for i=n-1:-1:1
    P=P.*x+a(i);
end
    

end
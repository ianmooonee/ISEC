%Interpolação polinomial
%Função interp1
clear;clc;

%doc interp1

x=10:10:100; %abcissas
xq=15:10:100;
v=log(x); %ordenadas

lin1=interp1(x,v,15); %calcular o valor aproximado no 15
lin2=interp1(x,v,xq);

plot(x,v,'r',"LineWidth",3);
hold on
plot(xq,lin2,'b',"LineWidth",2);
hold off
legend('Exata', 'Interpolação Linear');

spline1=interp1(x,v,xq,"spline");
exato=log(xq);

tabela=[xq' exato' lin2' spline1' abs(exato-lin2)' abs(exato-spline1)'];
clc;
disp('------------Interp1--------------')
disp('')
fprintf('\n\tobj\t\t\texato \tlinear \tspline \terroLinear \terroSpline\n');
disp(tabela)
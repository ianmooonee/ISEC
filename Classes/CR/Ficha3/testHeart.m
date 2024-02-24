function y=testHeart()
    load('rn_pac.mat');
    p = readmatrix('heart_test.csv', 'Delimiter', ',', 'DecimalSeparator', '.');
    in=p';
    t=[1 1 1 0 0 0];
    
    y=rede(in);

    y = (y >= 0.5);
erro=perform(rede, t, y);
fprintf("Erro=%f\n", erro);
end
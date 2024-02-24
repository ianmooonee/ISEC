function rn_diabetes()
    p = readmatrix('diabetes.csv', 'Delimiter', ',', 'DecimalSeparator', '.');
    t = p(:, end)';
    p(:, end)=[];
    in=p';

    rede=feedforwardnet;
    
    rede.trainParam.epochs=2000;
    rede.divideFcn = 'dividerand';
    rede.divideParam.trainRatio = 0.7; 
    rede.divideParam.valRatio = 0.0; %para fazer as 1000 iterações
    rede.divideParam.testRatio = 0.3;

    [rede, tr] = train(rede, in, t);
    out = sim(rede, in);
    out=out>=0.5;
    disp(tr);

    plotconfusion(t, out);
    
    r=0;
    for i=1:length(out)
        if out(i)==t(i)
            r=r+1;
        end
    end
    accuracy = r/length(out)*100;
    fprintf('Precisao treino: %f\n', accuracy)

    TInput = in(:, tr.testInd);
    TTargets = t(:, tr.testInd);
    out = sim(rede, TInput);
    out=out>=0.5;

    r=0;
    for i=1:length(out)
        if out(i)==TTargets(i)
            r=r+1;
        end
    end
    accuracy = r/length(out)*100;
    fprintf('Precisao teste: %f\n', accuracy)



end
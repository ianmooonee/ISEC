function u = sExataSED(f,g,u0,v0)

    syms t u(t) v(t)
    
    ci=[u(0)==u0,v(0)==v0];
    
    eqns=[diff(u,t)==f(t,u,v),diff(v,t)==g(t,u,v)];
    
    [u(t), ~] = dsolve(eqns,ci);

    if(isempty(u(t)))
        u = 0;
    end
end
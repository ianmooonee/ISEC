a) a=[0:2:100]

b) a'

c) a=[1 2 3; 4 5 6; 7 8 9]

d) a(1,3)=33

e) ones(3)

f) b=rand(4,4)

g) b(:,2)=[]

h)  syms x
    x = 0:pi/100:2*pi;
    y = sin(x);
    plot(x,y)

i)  hold on;
    y2=cos(x);
    plot(x,y2, 'g');

j)  legend('Seno', 'Cosseno');
    title('Graficos');
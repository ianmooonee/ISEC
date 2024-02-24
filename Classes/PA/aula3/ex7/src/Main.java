public class Main {
    public static void main(String[] args) throws Exception{
        Matriz m1 = new Matriz(4,3);

        for(int i=0;i<4;i++)
            for(int j=0;j<3;j++)
                m1.set(i, j, (int) Math.random()*100);

        Matriz m2 = new Matriz(m1);
        Matriz m3 = (Matriz) m1.clone();

        m1.set(1, 1, 1000);
        m2.set(1, 1, 2000);
        m3.set(1, 1, 3000);

        Matriz m4 = Matriz.add(m1, m2);

        System.out.println("M1:");
        m1.mostrar();
        System.out.println("\nM2:");
        m2.mostrar();
        System.out.println("\nM3:");
        m3.mostrar();
        System.out.println("\nM4 (M1+M2):");
        m4.mostrar();

        m4.add(m3);

        System.out.println("\nM4 += M3:");
        m4.mostrar();

    }
}
/*
* int [][]b=new int [8][9];
* Podem ter vários elementos por linha:
*   int [][]b=new int [2][]; b[0]=new int[4]; b[1]=new int[5];
*/
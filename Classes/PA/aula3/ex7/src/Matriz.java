import java.util.Arrays;

public class Matriz {
    private int [][] valores;

    private Matriz(){}

    public Matriz(int nl, int nc){
        valores =new int[nl][nc];
    }

    public Matriz(Matriz m){

    }

    private void copy(Matriz m){
        valores =new int[m.valores.length][];
        for(int i = 0; i< valores.length; i++){
            valores[i]= Arrays.copyOf(m.valores[i], m.valores[i].length);
        }
    }

    protected Object clone() throws CloneNotSupportedException{
        Matriz novam=new Matriz();
        novam.copy(this);

        return novam;
    }

    public float get(int nl, int nc){

    }

    public float set(int nl, int nc){

    }

    public void mostrar(){
        /*for(int i=0;i<valores.length;i++){
            for(int j=0;j<valores.length;j++){
                System.out.printf("%d", valores[i][j]);
            }
        }*/

        for (int[] linha : valores) {
            for (int val:linha) {
                System.out.printf("%d", linha[val]);
            }
        }
        System.out.println();
    }

    public boolean isSizeEqual(Matriz m2){
        if(valores.length!=m2.valores.length)
            return false;
        for(int i=0;i<valores.length;i++){
            if(valores[i].length!=m2.valores[i].length)
                return false;
        }

        return true;
    }

    public boolean add(Matriz m2){
        if(!this.isSizeEqual(m2))
            return false;

        for(int i=0;i<this.valores.length; i++){
            for(int j=0;j<this.valores[i].length; j++)
            this.valores[i][j]+=m2.valores[i][j];
        }

        return true;
    }

    public static Matriz add(Matriz m1, Matriz m2){
        Matriz nova=new Matriz(m1);

        if(!nova.add(m2))
            return null;

        return nova;
    }


}


public class Tabela {
    private static final int MAX=100;
    private static final int TAM=20;
    private final int []tab;
    private int nInseridos=0;
    private int nGerados=0;

    public Tabela(){
        tab= new int[TAM];
    }

    public boolean existe(int n){
        for(int i:tab){
            if(i==n)
                return true;
        }

        return false;
    }

    public boolean adiciona(int n){
        if (!existe(n)) {
            tab[nInseridos] = n;
            return true;
        }

        return false;
    }

    public void geraNums(){ //preencher array com números aleatórios e não duplicados
        int n;
        while(nInseridos<TAM){
            n=(int)(Math.random()*(MAX+1));
            nGerados++;

            if(adiciona(n))
                nInseridos++;
        }
    }

    public void listar(){
        System.out.println("\n");
        for(int i:tab)
            System.out.printf("%d ", i);
        System.out.println("\n");
    }

    public void mostrarDup(){
        System.out.println("Quantidade de numeros gerados em duplicado: "+(nGerados-nInseridos));
    }


}

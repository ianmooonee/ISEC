public class TrianguloPascal {
    private int t[][];

    TrianguloPascal(int tamanho){
        criar(tamanho);
    }

    private void criar(int tamanho){
        t=new int[tamanho][];

        for(int i=0;i<tamanho;i++){
            t[i]=new int[i+1];
            t[i][0]=t[i][i]=1;

            for(int j=1; j<i; j++){ //não entra nos 2 primeros casos que definimos anteriormente
                t[i][j]=t[i-1][j-1]+t[i-1][j];
            }
        }
    }

    public String gerar(boolean alinhar){
        StringBuilder sb=new StringBuilder();

        for(int i=0;i<t.length;i++){
            if(alinhar){
                sb.append(" ".repeat((t.length*6-i*6)/2));
            }

            for (int j = 0; j < t[i].length; j++){
                sb.append(String.format("%6d", t[i][j]));
            }
            sb.append(System.lineSeparator()); //sb.append('\n');
        }

        return sb.toString();
    }


}

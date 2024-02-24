import java.util.Arrays;
import java.util.StringTokenizer;

public class Report {
    private static final int INC_AUTORES=5;
    private static final String DELIMITADORES=" \t\n,.;:-_!?";
    //private static final int MAX_AUTORES=5;
    private String titulo;
    private String []autores; //ArrayList<String>
    private int nAutores;
    private StringBuilder texto;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public StringBuilder getTexto() {
        return texto;
    }

    public Report(String titulo){
        this.titulo=titulo;
        this.autores=new String[INC_AUTORES];
        this.nAutores=0;
        this.texto=new StringBuilder();
    }

    public boolean addAutor(String novo){
        if(autores==null || nAutores>=autores.length || novo==null)
            return false;

        for(int i=0; i<nAutores; i++){
            if(autores[i].equalsIgnoreCase(novo)){
                return false;
            }
        }

        autores[nAutores++]=novo;

        return true;
    }

    public boolean addAutor2(String novo){ //função que aumenta o array
        if(novo==null)
            return false;

        for(int i=0; i<nAutores; i++){
            if(autores[i].equalsIgnoreCase(novo)){
                return false;
            }
        }

        if(nAutores>=autores.length){
            String []novoAutores=new String[autores.length+INC_AUTORES];
            System.arraycopy(autores, 0, novoAutores, 0, autores.length);
            //Arrays.copyOf(autores, autores.length+INC_AUTORES); //alternativa
            autores=novoAutores;
        }

        autores[nAutores++]=novo;

        return true;
    }

    public boolean removeAutor(String autor){
        if(autor==null)
            return false;

        for(int i=0; i<nAutores; i++){
            if(autores[i].equalsIgnoreCase(autor)){
                for(int j=i;j<nAutores-1; j++){
                    autores[j]=autores[j+1];
                }
                nAutores--;
                autores[nAutores]=null; //opcional neste caso
                return true;
            }
        }

        return false;
    }

    public void addTexto(String novo){
        texto.append(novo);
    }

    public int getNumeroPalavras(){
        StringTokenizer st=new StringTokenizer(texto.toString(), DELIMITADORES);
        //poderiamos também usar o split e o scanner (com useDelimiter() com uma regex) para fazer mais 2 versões.
        return st.countTokens(); //conta o número de tokens encontrados. Evitamos fazer um ciclo.
    }

    public int repeticoesPalavra(String palavra){
        int contador=0;
        StringTokenizer st=new StringTokenizer(texto.toString(), DELIMITADORES);

        while(st.hasMoreTokens()){
            if(palavra.equalsIgnoreCase(st.nextToken())){
                contador++;
            }
        }

        return contador;
    }

    public void tornarMaiuscula(){
        boolean mudarProx=true;

        for(int i=0; i<texto.length(); i++){
            char c=texto.charAt(i);

            if(mudarProx && Character.isLetter(c)){
                texto.setCharAt(i, Character.toUpperCase(c));
                mudarProx=false;
            }
            else if(c=='.' || c=='!' || c=='?'){ //else if(".!?".indexOf(c)>=0) //alternativa
                mudarProx=true;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();

        sb.append(String.format("Titulo: %s\n", titulo));
        sb.append("Autores: ");
        for(int i=0; i<nAutores; i++){
            if(i>0)
                sb.append(",");
            sb.append(autores[i]);
        }
        sb.append("\nTexto: ");
        sb.append(texto);

        return sb.toString();
    }
}

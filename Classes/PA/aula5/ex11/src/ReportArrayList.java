import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReportArrayList {
    private static final int INC_AUTORES=5;
    private static final String DELIMITADORES=" \t\n,.;:-_!?";
    //private static final int MAX_AUTORES=5;
    private String titulo;
    private ArrayList<String> autores; //ArrayList<String>
    //private int nAutores; //é o size() do ArrayList
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

    public ReportArrayList(String titulo){
        this.titulo=titulo;
        this.autores=new ArrayList<>();
        this.texto=new StringBuilder();
    }

    public boolean addAutor(String novo){
        if(autores==null)
            return false;

        /*
        for(int i=0; i<nAutores; i++){
            if(autores.get(i).equalsIgnoreCase(novo)){
                return false;
            }
        }*/
        if(autores.contains(novo)) //é case sensitive
            return false;

        autores.add(novo);
        //autores[nAutores++]=novo;

        return true;
    }

    public boolean removeAutor(String autor){
        if(autor==null)
            return false;

        return autores.remove(autor);
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

        if(!autores.isEmpty()) {
            sb.append("Autores: ");
            for (int i = 0; i < autores.size(); i++) {
                if (i > 0)
                    sb.append(",");
                sb.append(autores.get(i));
            }
        }

        sb.append("\nTexto: ");
        sb.append(texto);

        return sb.toString();
    }
}

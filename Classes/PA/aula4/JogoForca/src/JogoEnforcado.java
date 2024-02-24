import java.util.Locale;

public class JogoEnforcado {
    private static final int MAX_ERROS=7;
    private static final String ESCONDIDO=".";

    private String palavra; //palavra a descobrir
    private StringBuffer situacao; //estado da descoberta da palavra
    private StringBuilder letrasUsadas; //caracteres já jogados
    private int nTentativas, nAcertos; //número de tentativas totais e tentativas acertadas
    private boolean opcaoPalavra;

    public JogoEnforcado(){
        inicia();
    }

    public void inicia(){
        nTentativas=0;
        nAcertos=0;
        opcaoPalavra=false;
        int indicePalavra=(int)(Math.random()*Dicionario.getNrPalavras());
        palavra=Dicionario.getPalavra(indicePalavra).toUpperCase();
        situacao=new StringBuffer(ESCONDIDO.repeat(palavra.length())); //situacao.append(ESCONDIDO.repeat(palavra.length()));
        letrasUsadas=new StringBuilder();
    }

    public boolean acertou(){
        return palavra!=null && situacao!=null && palavra.equalsIgnoreCase(situacao.toString()); //se alguma das condições for false, então retorna logo
        //return palavra.equalsIgnoreCase(situacao.toString()); //palavra==situacao.toString() -> está errado porque compara endereços e não o objetio em si
    }

    public boolean concluido(){
        return acertou() || nTentativas-nAcertos>=MAX_ERROS || opcaoPalavra;
    }

    public String getSituacao(){
        return situacao==null ? null : situacao.toString();
    }

    public String getLetrasUsadas(){
        return letrasUsadas==null ? null : letrasUsadas.toString();
    }

    public int getNTentativas(){
        return nTentativas;
    }

    String getPalavra(){
        nTentativas=-100; //para acabar o jogo
        return palavra;
    }

    public boolean setOpcao(String opcao){
        if(palavra==null || concluido())
            return false;

        if(opcao==null || opcao.isBlank())
            return false;

        opcao=opcao.trim().toUpperCase();

        if(opcao.length()>1 && opcao.length()!=palavra.length()) //se for uma palavra com tamanho diferente da palavra a adivinhar retorna falso
            return false;

        nTentativas++;

        if(opcao.length()>1){
            opcaoPalavra=true;
            situacao=new StringBuffer(opcao);
            return true;
        }

        char charOpcao=opcao.charAt(0);
        if(letrasUsadas.toString().indexOf(charOpcao)>=0) {
            nTentativas--; //mau fix
            return false;
        }
        letrasUsadas.append(charOpcao);

        //acabar
    }
}


package exercicio.model;
import java.util.HashMap;
import java.util.Map;

public class Dicionario {
    private String linguaAtual;
    private Map<String, Map<String, String>> dicionariosLinguas;

    public Dicionario(){
        linguaAtual =null;
        dicionariosLinguas=new HashMap<>();
    }

    public void setLinguaAtual(String linguaAtual) {
        this.linguaAtual = linguaAtual.trim().toLowerCase();
    }

    public void add(String lingua, String original, String traducao) {
        lingua=lingua.trim().toLowerCase();
        Map<String, String> tempMap=dicionariosLinguas.get(lingua); //dicionario interior temporario

        if (tempMap==null) {
            tempMap=new HashMap<>();
            dicionariosLinguas.put(lingua, tempMap);
        }

        original=original.trim().toLowerCase();
        traducao=traducao.trim().toLowerCase();

        tempMap.put(original, traducao);
    }

    public String get(String original){
        if (linguaAtual ==null) {
            return null;
        }

        Map<String, String> dictLang=dicionariosLinguas.get(linguaAtual);

        if(dictLang==null)
            return null;

        return dictLang.get(original.trim().toLowerCase());
    }

    @Override
    public String toString(){
        return "Lingua atual: "+ linguaAtual +"\n"+"Conteudo: "+dicionariosLinguas;
    }

}

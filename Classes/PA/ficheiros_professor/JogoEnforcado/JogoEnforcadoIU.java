package pa.aula4.ex10;

import java.util.Scanner;
import pa.aula4.ex10.JogoEnforcadoModelo;

public class JogoEnforcadoIU {

    JogoEnforcadoModelo jogo;

    public JogoEnforcadoIU(JogoEnforcadoModelo jogo) {
        this.jogo = jogo;
    }

    public void jogar() {

        Scanner sc = new Scanner(System.in);
        String opcao;

        while(!jogo.concluido()){

            System.out.println(jogo.getSituacao()); //mostrar as letras descobertas
                                                    // se a palavra for CAFE
                                                    // inicialmente deverá mostrar:  ....
            System.out.println();

            System.out.println("Tentativas realizadas: " + jogo.getNTentativas()); //inicio: 0
            System.out.println("Caracteres jogados: " + jogo.getLetrasUsadas()); // inicialmente: ""
                                                                        // depois: AODG
            System.out.println();

            System.out.print("Caracter ou palavra completa com " +
                    jogo.getSituacao().length() + " caracteres para finalizar: ");

            opcao = sc.next();
            opcao = opcao.trim();

            jogo.setOpcao(opcao);

            System.out.println();

        }

        if (jogo.acertou()) {
            System.out.println("Parabens, acertou na palavra \"" +
                    jogo.getPalavra() + "\" em " +
                    jogo.getNTentativas() + " tentativas!");
        }else {
            System.out.println("Apos " + jogo.getNTentativas() +
                    " tentativas nao acertou na palavra que era \"" + jogo.getPalavra());
        }
    }

}
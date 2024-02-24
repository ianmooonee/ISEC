package pa.aula4.ex10;

public class JogoEnforcado {

    public static void main(String[] args) throws Exception {
        JogoEnforcadoModelo jogo = new JogoEnforcadoModelo();
        JogoEnforcadoIU jogoIU = new JogoEnforcadoIU(jogo);
        jogoIU.jogar();
    }
}

import java.util.Scanner;

public class ex2{
    public static int obtemResposta(int palpite){
        Scanner sc=new Scanner(System.in);
        int resposta;

        do {
            System.out.printf("O numero que pensou e %d?\n", palpite);
            System.out.println("""
                    1 - Numero certo
                    2 - Numero maior
                    3 - Numero menor""");
            System.out.println("\nResposta: ");

            if(sc.hasNextInt())
                resposta=sc.nextInt();
            else{
                resposta=-1;
                sc.next(); //limpa o input para não entrar em ciclo infinito com o input errado (ex: 2c)
            }

        }while(resposta<1 || resposta>3);

        return resposta;
    }

    public static void main(String[] args){
        int min=1, max=100, palpite, nTentativas=0, resposta;

        System.out.println("Numero: ");
        do{
            nTentativas++;
            palpite=(min+max)/2;
            resposta=obtemResposta(palpite);

            if(resposta==2)
                min=palpite+1;
            else
                max=palpite-1;
        }while(resposta!=1 && min<=max);

        System.out.println("\nFiz "+nTentativas+" tentativas para acertar no numero "+palpite+"!");
    }
}

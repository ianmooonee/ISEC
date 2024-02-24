package exercicio;

import exercicio.model.Frota;
import exercicio.model.Ligeiro;

public class Main {

    public static void main(String[] args) {
	    Frota f=new Frota("Frota1");
        Ligeiro l1=new Ligeiro(1998, "AA-45-BB", 5), l2=new Ligeiro(1999, "AB-45-BB", 2);
        f.addVeiculo(l1);
        f.addVeiculo(l2);
        System.out.println(f.toString());
    }
}

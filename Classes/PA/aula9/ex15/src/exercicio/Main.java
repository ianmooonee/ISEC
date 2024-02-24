package exercicio;

import exercicio.model.Dicionario;

public class Main {

    public static void main(String[] args) {
        Dicionario d = new Dicionario();
        d.add("english", "BOOK", "Book");
        d.add("francais", "BOOK", "livre");
        d.add("portugues", "BOOK", "livro");
        d.add("english", "YEAR", "year");
        d.add("francais", "YEAR", "an");
        d.add("portugues", "YEAR", "ano");

        d.setLinguaAtual("english");
        System.out.println(d.get("YEAR")); // year

        d.setLinguaAtual("portugues");
        System.out.println(d.get("YEAR")); // ano

        d.setLinguaAtual("francais");
        System.out.println(d.get("BOOK")); // livre

        System.out.println(d.toString());
    }
}

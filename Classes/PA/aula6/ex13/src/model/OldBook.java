package model;

import java.util.List;

public class OldBook extends Livro {
    private int copias=0;

    public OldBook(String titulo, List<String> autores, int copias) {
        super(titulo, autores);
        this.copias=copias;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    @Override
    public String toString(){
        return "OldBook"+super.toString()+" copias: "+copias;
    }
}

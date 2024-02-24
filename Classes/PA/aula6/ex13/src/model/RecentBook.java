package model;

import model.Livro;

import java.util.List;

public class RecentBook extends Livro {
    private int isbn;
    private float preco;

    public RecentBook(String titulo, List<String> autores, int preco, int isbn) {
        super(titulo, autores);
        this.preco=preco;
        this.isbn=isbn;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "RecentBook"+super.toString()+" preco: "+preco+" isbn:"+isbn;
    }
}

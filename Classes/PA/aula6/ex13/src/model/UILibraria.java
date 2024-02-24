package model;

public interface UILibraria {
    String getNome();
    void setNome(String nome);
    boolean removeLivro2(int id);
    Livro getLivro2(int id);
    int addLivroSet(Livro livro);
}

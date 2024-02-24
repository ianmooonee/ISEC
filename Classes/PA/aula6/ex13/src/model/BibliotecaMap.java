package model;
import java.util.*;

public class BibliotecaMap {
    private String nome;
    //private List<Livro> lstLivros;
    private Map<Integer, Livro> mapaLivro;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BibliotecaMap(String nome) {
        this.nome = nome;
        this.mapaLivro = new HashMap<>();
    }

    public int addLivro(Livro livro) {
        mapaLivro.put(livro.getId(), livro);

        return livro.getId();
    }

    public int addLivro(String titulo, List<String> autores){
        return addLivro(new Livro(titulo, autores));
    }

    public Livro getLivro(int id) { //obtém o livro naquela posição
        if(mapaLivro==null||mapaLivro.isEmpty())
            return null;

        return mapaLivro.get(id);
    }

    public Livro getLivro2(int id) {
        if(mapaLivro==null||mapaLivro.isEmpty())
            return null;

        Collection<Livro> values = mapaLivro.values(); //retorna uma coleção de livros

        Set<Integer> keys=mapaLivro.keySet();

        for(Livro livro : values)
            if(id==livro.getId())
                return livro;

        return null;
    }

    public boolean removeLivro(int id) {
        if (mapaLivro == null)
            return false;

        return mapaLivro.remove(id)!=null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Libraria " + nome + "\n");

        sb.append("Livros:\n");

        if (mapaLivro == null || mapaLivro.size() == 0) {
            sb.append("Livraria vazia!");
        } else {
            Collection<Livro> values = mapaLivro.values();
            for (Livro livro : values)
                sb.append("- " + livro + "\n");
        }

        return sb.toString();
    }
}

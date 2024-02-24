package model;

import java.util.Comparator;

public class LivroReverseComparator implements Comparator<Livro> {
    @Override
    public int compare(Livro l1, Livro l2){
        return -l1.getTitulo().compareToIgnoreCase(l2.getTitulo());
    }
}

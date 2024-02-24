package model;

import java.util.*;

public class Biblioteca implements UILibraria{
    private String nome;
    private List<Livro> lstLivros;
    private Set<Livro> setLivro;

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    /*public Biblioteca(String nome){
        this.nome = nome;
        this.lstLivros = new ArrayList<>();
    }*/

    public Biblioteca(String nome){
        this.nome = nome;
        this.setLivro = new HashSet<>();
    }

    /*public int addLivro(Livro livro){
        lstLivros.add(livro);
        return livro.getId();
    }*/

    /*public int addLivro(String titulo, List<String> autores){
        return addLivro(new Livro(titulo, autores));
    }*/

    @Override
    public int addLivroSet(Livro livro){
        if(!setLivro.add(livro))
            return -1;

        return livro.getId();
    }

    public Livro getLivro(int id){ //obtém o livro naquela posição
        for(Livro l : setLivro){
            if(l.getId()==id)
                return l;
        }

        return null; ///o que retornar no caso de não encontrar?
    }

    @Override
    public Livro getLivro2(int id){
        if(setLivro==null||setLivro.isEmpty())
            return null;

        Iterator<Livro> it=setLivro.iterator();

        while(it.hasNext()){
            Livro livro=it.next();
            if(id==livro.getId())
                return livro;
        }

        return null;
    }

    /*public boolean removeLivro(Livro livro){
        if(livro==null)
            return false;

        return lstLivros.remove(livro);
    }*/

    public boolean reamoveLivroSet(int id){
        if(setLivro==null)
            return false;

        for(Livro livro : setLivro)
            if(livro.getId()==id)
                return setLivro.remove(livro);

        return false;
    }

    @Override
    public boolean removeLivro2(int id){
        return setLivro==null || setLivro.remove(Livro.getDummyBook(id));
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("Libraria "+nome+"\n");

        sb.append("Livros:\n");

        if(setLivro==null || setLivro.size()==0){
            sb.append("Livraria vazia!");
        }
        else {
            for (Livro livro : setLivro)
                sb.append("- " + livro + "\n");
        }

        return sb.toString();
    }

    public String toStringSortedByName(){
        StringBuilder sb=new StringBuilder(super.toString()+"\n");

        sb.append("Livros:\n");

        if(setLivro==null || setLivro.size()==0){
            sb.append("Livraria vazia!");
        }
        else {
            List<Livro> sortedList=new ArrayList<>(lstLivros);
            Collections.sort(lstLivros); //é preciso ir ao livro fazer implement da interface Comparable e redifinir o método compareTo

            for (Livro livro : sortedList)
                sb.append("- " + livro + "\n");
        }


        return sb.toString();
    }

    public String toStringSortedByName2(){
        StringBuilder sb=new StringBuilder(super.toString()+"\n");

        sb.append("Livros:\n");

        if(setLivro==null || setLivro.size()==0){
            sb.append("Livraria vazia!");
        }
        else {
            List<Livro> sortedList=new ArrayList<>(lstLivros);
            Collections.sort(lstLivros, new LivroReverseComparator()); //ordenar de acordo com a classe LivroReverseComparator, neste caso por ordem alfabética inversa

            for (Livro livro : sortedList)
                sb.append("- " + livro + "\n");
        }


        return sb.toString();
    }

    /*@Override
    public String toString(){
        StringBuilder sb=new StringBuilder("Libraria "+nome+"\n");

        sb.append("Livros:\n");

        if(lstLivros==null || lstLivros.size()==0){
            sb.append("Livraria vazia!");
        }
        else {
            for (Livro livro : lstLivros)
                sb.append("- " + livro + "\n");
        }

        return sb.toString();
    }*/

}

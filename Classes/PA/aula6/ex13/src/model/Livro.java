package model;

import java.util.ArrayList;
import java.util.List;

public class Livro implements Comparable<Livro>{
    private static int countID=0;
    private int id;
    private String titulo;
    private List<String> autores;

    private static int getNewID(){
        return ++countID;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    private Livro(int id){
        this.id=id;
        autores=null;
        titulo=null;
    }

    protected Livro(String titulo, List<String> autores){ //era public. para evitar serem criados livros normais mas deixar que as subclasses acedam ao construtor (na mesma package)
        this.titulo=titulo;
        this.autores=autores;
        this.id=getNewID();
    }

    public Livro(String titulo, String ... autores){
        this.titulo=titulo;
        this.autores=List.of(autores);
        this.id=getNewID();

        /*this.autores=new ArrayList<>();
        for(String autor:autores)
            this.autores.add(autor);*/
    }

    @Override
    public String toString(){ //fazer com o string builder
        if(titulo==null)
            return "DummyBook ["+id+"]";

        if(autores==null || autores.size()==0)
            return "["+id+"], '"+titulo+"'";

        String strAutores=autores.toString(); //apresenta a lista na forma de string -> [autor1, autor2, autor3]

        return "["+id+"]'"+titulo+"', "+strAutores.substring(1, strAutores.length()-1); //substring para tirar os "[]"
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;

        if(o==null || !(o instanceof Livro))//o.getClass()!=this.getClass()) -> deixa de funcionar quando introduzimos herança
            return false;

        Livro livro=(Livro) o; //para aceder aos métodos se o for instanceof Livro

        return id==livro.id;
    }

    @Override
    public int hashCode(){
        return id;
    }


    public static Livro getDummyBook(int id){return new Livro(id);}

    @Override
    public int compareTo(Livro o) {
        return this.titulo.compareToIgnoreCase(o.titulo);
    }
}

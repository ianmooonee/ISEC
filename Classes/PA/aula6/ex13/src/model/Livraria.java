package model;

import java.util.HashSet;
import java.util.List;

public abstract class Livraria implements UILibraria {
    private String nome;

    public Livraria(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}

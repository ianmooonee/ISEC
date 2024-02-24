package exercicio.model;

import java.util.*;

public class Frota {
    String nome;
    private Set<Veiculo> frota=new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Veiculo> getFrota() {
        return frota;
    }

    public void setFrota(Set<Veiculo> frota) {
        this.frota = frota;
    }

    public Frota(String nome){
        this.nome=nome;
    }

    public boolean addVeiculo(Veiculo v){
        return frota.add(v);
    }

    public boolean removeVeiculo(String matricula){
        Veiculo dummy = new Veiculo(0, matricula);
        return frota.remove(dummy);
    }

    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("Empresa " + nome + "\n");

        if(frota==null || frota.isEmpty())
            sb.append("Sem veiculos!");
        else
            for(Veiculo v : frota)
                sb.append(v.toString());

        return sb.toString();
    }

    public String toStringOrdenaPorPass() {
        StringBuilder sb= new StringBuilder("Empresa " + nome + "\n");

        if(frota==null || frota.isEmpty()) {
            sb.append("Sem veiculos!");
            return sb.toString();
        }

        List<IPassengers> l=new ArrayList<>();

        for (Veiculo v : frota)
            if(v instanceof IPassengers)
                l.add((IPassengers) v);

        Collections.sort(l, new NumeroPassageirosComparator());

        for(IPassengers a : l)
            sb.append(a.toString());

        return sb.toString();
    }

    //para ordenar criamos uma lista com o tipo de veiculos que queremos e depois percorremos essa lista.
    //organizado através do Collection sort.
}

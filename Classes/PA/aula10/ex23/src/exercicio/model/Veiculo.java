package exercicio.model;

import java.util.Objects;

public class Veiculo {
    private int ano;
    private String matricula;

    public Veiculo(int ano, String matricula) {
        this.ano = ano;
        this.matricula = matricula;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Veiculo)) //usar instanceof por causa das classes derivadas de veículo
            return false;

        Veiculo veiculo = (Veiculo) o;
        return veiculo.matricula.equalsIgnoreCase(((Veiculo) o).matricula);
    }

    @Override
    public int hashCode() {
        return matricula.hashCode();
    }

    @Override
    public String toString(){
        return "Matricula: " + matricula + " [" + ano + "]";
    }

}

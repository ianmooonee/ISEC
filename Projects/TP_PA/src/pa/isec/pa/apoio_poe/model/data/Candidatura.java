package pa.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Candidatura implements Serializable {
    static final long serialVersionUID=100L;
    private Aluno aluno;
    private ArrayList<Proposta>  propostas;


    public Candidatura(Aluno aluno, ArrayList<Proposta> propostas) {
        this.aluno = aluno;
        this.propostas = propostas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public ArrayList<Proposta> getPropostas() {
        return propostas;
    }

    public void setPropostas(ArrayList<Proposta> propostas) {
        this.propostas = propostas;
    }

    @Override
    public String toString() {
        return "\n\nAluno: " + getAluno().getId() + "\t"+getAluno().getNome() + "\nPropostas: " + getPropostas();
    }

}

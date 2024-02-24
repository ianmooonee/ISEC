package pa.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Aluno implements Comparable<Aluno>, Serializable {
    static final long serialVersionUID=100L;
    private final Long id;
    private String nome;
    private String email;
    private String curso;
    private String ramo;
    private double classif;
    private boolean estEProj;

    public Aluno(Long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
        this.ramo = ramo;
        this.classif = classif;
        this.estEProj = estEProj;
    }

    public boolean isEstEProj() {
        return estEProj;
    }

    public void setEstEProj(boolean estEProj) {
        this.estEProj = estEProj;
    }

    public double getClassif() {
        return classif;
    }

    public void setClassif(double classif) {
        this.classif = classif;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", email=" + email
                + ", curso="+curso+", ramo="+ramo+", classificacao="+classif+", estEProj="+estEProj+"]\n";
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Aluno))
            return false;
        Aluno a=(Aluno) o;

        return Objects.equals(id, a.id);
    }

    @Override
    public int hashCode(){
        return 100;
    }

    @Override
    public int compareTo(Aluno o) {
        if(this.classif>o.classif) return 1;
        else if(this.classif<o.classif) return -1;
        return 0;
    }
}

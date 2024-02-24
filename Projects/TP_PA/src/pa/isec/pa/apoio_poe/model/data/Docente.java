package pa.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Docente implements Serializable {
    static final long serialVersionUID=100L;
    private String email;
    private String nome;

    public Docente(String email, String nome) {
        this.email = email;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Docente [nome=" + nome + ", email=" + email+"]\n";
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Docente))
            return false;
        Docente d=(Docente) o;

        return Objects.equals(email, d.email);
    }

    @Override
    public int hashCode(){
        return 100;
    }
}

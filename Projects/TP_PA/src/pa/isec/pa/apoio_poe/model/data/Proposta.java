package pa.isec.pa.apoio_poe.model.data;

import java.io.Serializable;
import java.util.Objects;

public class Proposta  implements Serializable {
    static final long serialVersionUID=100L;
    private String codigo;
    private String titulo;
    private Long alunoAtrib;


    public Proposta(String codigo, String titulo, Long alunoAtrib) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.alunoAtrib = alunoAtrib;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getAlunoAtrib() {
        return alunoAtrib;
    }

    public void setAlunoAtrib(Long alunoAtrib) {
        this.alunoAtrib = alunoAtrib;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof Proposta))
            return false;
        Proposta p=(Proposta) o;

        return Objects.equals(codigo, p.codigo);
    }

    @Override
    public int hashCode(){
        return 100;
    }

}

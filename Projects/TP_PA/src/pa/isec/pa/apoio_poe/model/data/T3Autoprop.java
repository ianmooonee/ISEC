package pa.isec.pa.apoio_poe.model.data;

public class T3Autoprop extends Proposta {
    static final long serialVersionUID=100L;
    private Long alunoID;

    public T3Autoprop(String codigo, String titulo, Long alunoAtrib, Long alunoID) {
        super(codigo, titulo, alunoAtrib);
        this.alunoID = alunoID;
        this.setAlunoAtrib(alunoID);
    }

    public Long getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(long alunoID) {
        this.alunoID = alunoID;
    }

    @Override
    public String toString() {
        return "T3 [codigo="+ getCodigo()+",\ttitulo="+getTitulo()+",\talunoAtrib="+getAlunoAtrib()+",\talunoId="+getAlunoID()+"]\n";
    }
}

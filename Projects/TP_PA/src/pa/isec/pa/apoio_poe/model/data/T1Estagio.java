package pa.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;

public class T1Estagio extends Proposta {
    static final long serialVersionUID=100L;
    private String entidadeAcolh;
    private ArrayList<String> ramos;

    public T1Estagio(String codigo, ArrayList<String> ramos, String titulo, Long alunoAtrib, String entidadeAcolh) {
        super(codigo, titulo, alunoAtrib);
        this.entidadeAcolh = entidadeAcolh;
        this.ramos=ramos;
    }

    public String getEntidadeAcolh() {
        return entidadeAcolh;
    }

    public void setEntidadeAcolh(String entidadeAcolh) {
        this.entidadeAcolh = entidadeAcolh;
    }

    @Override
    public String toString() {
        return "T1 [codigo="+ getCodigo()+",\tramos="+getRamos()+",\ttitulo="+getTitulo()+",\talunoAtrib="+getAlunoAtrib()+",\tentidadeAcolhe="+getEntidadeAcolh()+"]\n";
    }

    public ArrayList<String> getRamos() {
        return ramos;
    }

    public void setRamos(ArrayList<String> ramos) {
        this.ramos = ramos;
    }
}

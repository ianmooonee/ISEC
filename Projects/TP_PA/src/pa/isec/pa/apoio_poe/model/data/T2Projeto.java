package pa.isec.pa.apoio_poe.model.data;

import java.util.ArrayList;

public class T2Projeto extends Proposta {
    static final long serialVersionUID=100L;
    private String docenteProp;
    private ArrayList<String> ramos;

    public T2Projeto(String codigo, ArrayList<String> ramos, String titulo, Long alunoAtrib, String docenteProp) {
        super(codigo,titulo, alunoAtrib);
        this.docenteProp = docenteProp;
        this.ramos=ramos;
    }

    public String getDocenteProp() {
        return docenteProp;
    }

    public void setDocenteProp(String docenteProp) {
        this.docenteProp = docenteProp;
    }


    public ArrayList<String> getRamos() {
        return ramos;
    }

    public void setRamos(ArrayList<String> ramos) {
        this.ramos = ramos;
    }

    @Override
    public String toString() {
        return "T2 [codigo="+ getCodigo()+",\tramos="+getRamos()+",\ttitulo="+getTitulo()+",\talunoAtrib="+getAlunoAtrib()+",\tdocenteProp="+ getDocenteProp()+"]\n";
    }
}
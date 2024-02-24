package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseGestaoAlunos extends StateAdapter {

    public FaseGestaoAlunos(Context context, Data data) {
        super(context, data);
        System.out.println("Gestao alunos");
    }

    @Override
    public String addAlunos(String csvname) {
        return data.addAlunos(csvname);
    }

    @Override
    public String addAluno(String linha) {
        return data.addAluno(linha);
    }

    @Override
    public String removeAluno(long id){return data.removeAluno(id);}

    @Override
    public String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj){return data.editAluno(id, nome, email, curso, ramo, classif, estEProj);}

    @Override
    public String exportaCSV() {
        return data.exportaCSVAlunos();
    }

    @Override
    public void previous(){
        changeState(State.FASECONFIGURACAO);
    }

    @Override
    public State getState() {
        return State.FASEGESTAOALUNOS;
    }

}

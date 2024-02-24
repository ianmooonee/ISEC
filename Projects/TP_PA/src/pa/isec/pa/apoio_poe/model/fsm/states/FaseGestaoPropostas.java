package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.*;

public class FaseGestaoPropostas extends StateAdapter {
    public FaseGestaoPropostas(Context context, Data data) {
        super(context, data);
    }

    @Override
    public boolean gestaoPropostas(String csvname) {
        return false;
    }

    @Override
    public String removeProposta(String codigo){return data.removeProposta(codigo);}

    @Override
    public String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos){return data.editT1(codigo, titulo, entidade, ramos);}

    @Override
    public String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos){return data.editT2(codigo, titulo, docente, ramos);}

    @Override
    public String editT3(String codigo, String titulo, long aluno){return data.editT3(codigo, titulo, aluno);}

    @Override
    public String addPropostas(String csvname) {
        return data.addPropostas(csvname);
    }

    @Override
    public String addProposta(String linha) {
        return data.addProposta(linha);
    }

    @Override
    public String exportaCSV() {
        return data.exportaCSVPropostas();
    }

    @Override
    public void previous(){
        changeState(State.FASECONFIGURACAO);
    }
    @Override
    public State getState() {
        return State.FASEGESTAOPROPOSTAS;
    }
}

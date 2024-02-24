package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.ArrayList;

public class FaseGestaoCandidaturas extends StateAdapter {
    public FaseGestaoCandidaturas(Context context, Data data) {
        super(context, data);
    }

    @Override
    public String addCandidaturas(String csvname) {
        return data.addCandidaturas(csvname);
    }

    @Override
    public String addCandidatura(String linha){
        return data.addCandidatura(linha);
    }

    @Override
    public String editCandidatura(long numero, ArrayList<Proposta> props){return data.editCandidatura(numero, props);}

    @Override
    public String removeCandidatura(long numero){
        return data.removeCandidatura(numero);
    }

    @Override
    public String exportaCSV() {
        return data.exportaCSVCandidaturas();
    }

    @Override
    public void previous(){
        changeState(State.FASEOPCOESCANDIDATURAS);
    }
    @Override
    public State getState() {
        return State.FASEGESTAOCANDIDATURAS;
    }
}
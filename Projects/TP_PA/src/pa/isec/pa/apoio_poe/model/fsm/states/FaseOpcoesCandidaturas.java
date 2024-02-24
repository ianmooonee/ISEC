package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseOpcoesCandidaturas extends StateAdapter {
    public FaseOpcoesCandidaturas(Context context, Data data) {
        super(context, data);
    }

    @Override
    public void goToGestaoCandidaturas(){
        changeState(State.FASEGESTAOCANDIDATURAS);
    }

    @Override
    public void goToListasPropostas(){
        changeState(State.FASELISTASPROPOSTAS);
    }

    @Override
    public void goToListasAlunos(){
        changeState(State.FASELISTASALUNOS);
    }

    @Override
    public void previous(){
        changeState(State.FASECONFIGURACAO);
    }

    @Override
    public void next(){
        changeState(State.FASEATRIBUICAOPROPOSTAS);
    }
    @Override
    public State getState() {
        return State.FASEOPCOESCANDIDATURAS;
    }
}

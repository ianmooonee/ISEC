package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseListasPropostas extends StateAdapter {
    public FaseListasPropostas(Context context, Data data) {
        super(context, data);
    }

    @Override
    public void previous(){
        changeState(State.FASEOPCOESCANDIDATURAS);
    }
    @Override
    public State getState() {
        return State.FASELISTASPROPOSTAS;
    }
}
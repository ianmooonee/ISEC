package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseListasOrientadores extends StateAdapter {
    public FaseListasOrientadores(Context context, Data data) {
        super(context, data);
    }

    @Override
    public String listasOrient(int i) {
        return data.listasOrient(i);
    }

    @Override
    public void previous(){
        changeState(State.FASEATRIBUICAOORIENTADORES);
    }
    @Override
    public State getState() {
        return State.FASELISTASORIENTADORES;
    }
}

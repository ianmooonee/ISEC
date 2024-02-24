package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseInicio extends StateAdapter {
    public FaseInicio(Context context, Data data) {
        super(context,data);
        System.out.println("Inicio");
    }

    @Override
    public void next(){
        changeState(State.FASECONFIGURACAO);
    }

    @Override
    public State getState() {
        return State.FASEINICIO;
    }
}

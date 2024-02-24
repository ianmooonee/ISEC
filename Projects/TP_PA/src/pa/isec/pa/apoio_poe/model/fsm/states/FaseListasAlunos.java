package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseListasAlunos extends StateAdapter {
    public FaseListasAlunos(Context context, Data data) {
        super(context, data);
        System.out.println("listas alunos");
    }

    @Override
    public void previous(){
        changeState(State.FASEOPCOESCANDIDATURAS);
    }
    @Override
    public State getState() {
        return State.FASELISTASALUNOS;
    }
}
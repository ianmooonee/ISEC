package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseConfiguracao extends StateAdapter {
    public FaseConfiguracao(Context context, Data data) {
        super(context, data);
        System.out.println("Config");
    }

    @Override
    public void next(){
        changeState(State.FASEOPCOESCANDIDATURAS);
    }

    @Override
    public void previous(){
        changeState(State.FASEINICIO);
    }

    @Override
    public void goToGestaoAlunos(){
        changeState(State.FASEGESTAOALUNOS);
    }

    @Override
    public void goToGestaoPropostas(){
        changeState(State.FASEGESTAOPROPOSTAS);
    }

    @Override
    public void goToGestaoDocentes(){
        changeState(State.FASEGESTAODOCENTES);
    }

    @Override
    public State getState() {
        return State.FASECONFIGURACAO;
    }


}

package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseAtribuicaoOrientadores extends StateAdapter {
    public FaseAtribuicaoOrientadores(Context context, Data data) {
        super(context, data);
    }

    @Override
    public String atribOrientAutom() {
        return data.atribOrientAutom();
    }

    @Override
    public void goToGestaoOrientadores(){
        changeState(State.FASEGESTAOORIENTADORES);
    }

    @Override
    public void goToListaOrientadores(){
        changeState(State.FASELISTASORIENTADORES);
    }

    @Override
    public void next(){
        changeState(State.FASECONSULTA);
    }

    @Override
    public void previous(){
        changeState(State.FASEATRIBUICAOPROPOSTAS);
    }
    @Override
    public State getState() {
        return State.FASEATRIBUICAOORIENTADORES;
    }
}

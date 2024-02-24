package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseGestaoOrientadores extends StateAdapter {
    public FaseGestaoOrientadores(Context context, Data data) {
        super(context, data);
    }

    @Override
    public String consultaOrient() {
        return data.consultaOrient();
    }

    @Override
    public String adicionaOrient(String d, String p) {
        return data.adicionaOrient(d, p);
    }

    @Override
    public String editOrientadores(String codigo, String nome, String email){return data.editOrientadores(codigo, nome, email);}

    @Override
    public String eliminarOrientador(String codigo){return data.eliminarOrientador(codigo);}

    @Override
    public String exportaCSV() {
        return data.exportaCSVOrientadores();
    }

    public void next(){
        changeState(State.FASEATRIBUICAOPROPOSTAS);
    }

    @Override
    public void previous(){
        changeState(State.FASEATRIBUICAOORIENTADORES);
    }

    @Override
    public State getState() {
        return State.FASEGESTAOORIENTADORES;
    }
}

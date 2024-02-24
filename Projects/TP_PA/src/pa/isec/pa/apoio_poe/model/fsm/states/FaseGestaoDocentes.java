package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

public class FaseGestaoDocentes extends StateAdapter {
    public FaseGestaoDocentes(Context context, Data data) {
        super(context, data);
    }

    @Override
    public boolean gestaoDocentes(String csvname) {
        return true;
    }

    @Override
    public String addDocentes(String csvname) {
        return data.addDocentes(csvname);
    }

    @Override
    public String addDocente(String linha) {
        return data.addDocente(linha);
    }

    @Override
    public String addDocente(String nome, String email){return data.addDocente(nome, email);}

    @Override
    public String removeDocente(String email){return data.removeDocente(email);}

    @Override
    public String editDocente(String email, String nome){return data.editDocente(email, nome);}

    @Override
    public String exportaCSV() {
        return data.exportaCSVDocentes();
    }

    @Override
    public void previous(){
        changeState(State.FASECONFIGURACAO);
    }
    @Override
    public State getState() {
        return State.FASEGESTAODOCENTES;
    }
}

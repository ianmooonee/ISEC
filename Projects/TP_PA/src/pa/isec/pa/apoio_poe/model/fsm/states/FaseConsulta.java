package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;

public class FaseConsulta extends StateAdapter {
    public FaseConsulta(Context context, Data data) {
        super(context, data);
    }

    @Override
    public String consulta(ListTypes id){
        return data.consulta(id);
    }

    @Override
    public State getState() {
        return State.FASECONSULTA;
    }
}
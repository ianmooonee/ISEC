package pa.isec.pa.apoio_poe.model.fsm.states;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;

import java.util.*;

public class FaseAtribuicaoPropostas extends StateAdapter {

    public FaseAtribuicaoPropostas(Context context, Data data) {
        super(context, data);
        System.out.println("Atrib propostas");
    }

    @Override
    public Map<Proposta,ArrayList<Aluno>> atribPropAutom() {
        return data.atribPropAutom();
    }

    @Override
    public String atribPropManual(String aluno, String prop){
        return data.atribPropManual(aluno, prop);
    }

    @Override
    public String removePropostaAtribuida(long numero){
        return data.removePropostaAtribuida(numero);
    }

    @Override
    public String exportaCSV() {
        return data.exportaCSVAtribProposta();
    }

    @Override
    public void next(){
        changeState(State.FASEATRIBUICAOORIENTADORES);
    }

    @Override
    public void previous(){
        changeState(State.FASEOPCOESCANDIDATURAS);
    }
    @Override
    public State getState() {
        return State.FASEATRIBUICAOPROPOSTAS;
    }
}

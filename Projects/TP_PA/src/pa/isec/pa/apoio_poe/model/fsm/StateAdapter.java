package pa.isec.pa.apoio_poe.model.fsm;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

abstract public class StateAdapter implements IState, Serializable {
    protected final Context context;
    protected Data data;
    public static final String COMMA_DELIMITER = ",";

    protected StateAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }
    protected void changeState(IState newState) { context.changeState(newState); }

    @Override
    public String addAlunos(String csvname) {
        return null;
    }

    @Override
    public String addAluno(String linha){
        return null;
    }

    @Override
    public String addDocente(String linha){return null;}

    @Override
    public String removeAluno(long id){return null;}

    @Override
    public String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj) {return null;}

    @Override
    public String addDocente(String nome, String email){return null;}

    @Override
    public String removeDocente(String email){return null;}

    @Override
    public String editDocente(String email, String nome){return null;}

    @Override
    public String removeProposta(String codigo){return null;}

    @Override
    public String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos){return null;}

    @Override
    public String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos){return null;}

    @Override
    public String editT3(String codigo, String titulo, long aluno){return null;}

    @Override
    public boolean gestaoAlunos(int nextState) {
        return false;
    }

    @Override
    public boolean gestaoDocentes(String csvname) {
        return false;
    }

    @Override
    public String addDocentes(String csvname) {
        return null;
    }

    @Override
    public boolean gestaoPropostas(String csvname) { return false; }

    @Override
    public String addPropostas(String csvname){return null;}

    @Override
    public String addProposta(String linha){return null;}

    @Override
    public boolean configuracao(int nextState){
        return false;
    }

    @Override
    public boolean opcoesCand(int nextState){
        return false;
    }

    @Override
    public boolean gestaoCandidaturas(String csvname) {
        return false;
    }

    @Override
    public String addCandidaturas(String csvname) {
        return null;
    }

    @Override
    public boolean atribOrientadores(int nextState) {
        return false;
    }

    @Override
    public String consulta(ListTypes id) {
        return null;
    }

    @Override
    public String atribOrientAutom(){return null;}

    @Override
    public String editCandidatura(long numero, ArrayList<Proposta> props){return null;}

    @Override
    public String editOrientadores(String codigo, String nome, String email){return null;}

    @Override
    public String eliminarOrientador(String codigo){return null;}

    @Override
    public Map<Proposta, ArrayList<Aluno>> atribPropAutom() {
        return null;
    }

    @Override
    public String atribPropManual(String aluno, String prop) {
        return null;
    }

    @Override
    public String addCandidatura(String linha){return null;}

    @Override
    public String removeCandidatura(long numero){return null;}

    @Override
    public String removePropostaAtribuida(long numero){return null;}

    @Override
    public String exportaCSV(){return null;}

    @Override
    public String consultaOrient() {
        return null;
    }

    @Override
    public String adicionaOrient(String d, String p) {
        return null;
    }

    @Override
    public String listasOrient(int i){return null;}

    @Override
    public void next(){}
    @Override
    public void previous(){}

    @Override
    public void goToGestaoAlunos(){}
    @Override
    public void goToGestaoPropostas(){}
    @Override
    public void goToGestaoDocentes(){}
    @Override
    public void goToGestaoCandidaturas(){}
    @Override
    public void goToListasAlunos(){}
    @Override
    public void goToListasPropostas(){}
    @Override
    public void goToGestaoOrientadores(){}
    @Override
    public void goToListaOrientadores(){}

    protected void changeState(State newState){
        context.changeState(newState.createState(context,data));
    }
}

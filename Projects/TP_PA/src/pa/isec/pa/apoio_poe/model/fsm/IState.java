package pa.isec.pa.apoio_poe.model.fsm;

import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;

import java.util.ArrayList;
import java.util.Map;

public interface IState{
    boolean gestaoAlunos(int nextState);
    String addAlunos(String csvname);
    boolean gestaoDocentes(String csvname);
    String addDocente(String linha);
    String addDocentes(String csvname);
    String addAluno(String linha);
    String removeAluno(long id);
    String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj);
    String addDocente(String nome, String email);
    String removeDocente(String email);
    String editDocente(String email, String nome);
    String removeProposta(String codigo);
    String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos);
    String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos);
    String editT3(String codigo, String titulo, long aluno);

    String editCandidatura(long numero, ArrayList<Proposta> props);

    String editOrientadores(String codigo, String nome, String email);

    String eliminarOrientador(String codigo);

    boolean gestaoPropostas(String csvname);
    String addPropostas(String csvname);
    String addProposta(String linha);

    boolean gestaoCandidaturas(String csvname);
    String addCandidaturas(String csvname);
    String addCandidatura(String linha);
    String removeCandidatura(long numero);

    boolean configuracao(int nextState);
    boolean opcoesCand(int nextState);

    Map<Proposta, ArrayList<Aluno>> atribPropAutom();
    String removePropostaAtribuida(long numero);
    String atribPropManual(String aluno, String prop);

    boolean atribOrientadores(int nextState);
    String atribOrientAutom();
    String consultaOrient();
    String adicionaOrient(String d, String p);

    String listasOrient(int i);
//    String atribOrientManual(String aluno, String prop);

    String consulta(ListTypes id);

    String exportaCSV();
    State getState();

    void next();
    void previous();

    void goToGestaoAlunos();
    void goToGestaoPropostas();
    void goToGestaoDocentes();
    void goToGestaoCandidaturas();
    void goToListasAlunos();
    void goToListasPropostas();
    void goToGestaoOrientadores();
    void goToListaOrientadores();
}

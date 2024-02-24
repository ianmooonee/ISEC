package pa.isec.pa.apoio_poe.model;

import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Docente;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.model.fsm.State;
import pa.isec.pa.apoio_poe.model.memento.CareTaker;
import pa.isec.pa.apoio_poe.model.memento.IMemento;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Map;

public class ManagerUI {
    private Context fsm;
    PropertyChangeSupport pcs;
    CareTaker careTaker;

    public ManagerUI(){
        fsm = new Context();
        pcs = new PropertyChangeSupport(this);
        careTaker = new CareTaker(fsm);
        careTaker = new CareTaker(fsm);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public State getState() {
        return fsm.getState();
    }

    public void next() {
        fsm.next();
        pcs.firePropertyChange(null,null,null);
    }

    public String editOrientadores(String codigo, String nome, String email, String novoEmail) {
        return fsm.editOrientadores(codigo, nome, email, novoEmail);
    }

    public String eliminarOrientador(String codigo) {
        return fsm.eliminarOrientador(codigo);
    }

    public String editCandidaturas(long numero, ArrayList<Proposta> props) {
        return fsm.editCandidaturas(numero, props);
    }

    public String addDocente(String linha) {
        var ret = fsm.addDocente(linha);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addPropostas(String csvname) {
        var ret= fsm.addPropostas(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addCandidatura(String linha) {
        return fsm.addCandidatura(linha);
    }

    public String removeCandidatura(long numero) {
        var ret = fsm.removeCandidatura(numero);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String removePropostaAtribuida(long numero) {
        return fsm.removePropostaAtribuida(numero);
    }

    public String addAluno(String linha) {
        careTaker.saveMementoCT();
        var ret = fsm.addAluno(linha);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public void previous() {
        fsm.previous();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToGestaoAlunos() {
        fsm.goToGestaoAlunos();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToGestaoPropostas() {
        fsm.goToGestaoPropostas();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToGestaoDocentes() {
        fsm.goToGestaoDocentes();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToGestaoCandidaturas() {
        fsm.goToGestaoCandidaturas();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToListasAlunos() {
        fsm.goToListasAlunos();
        pcs.firePropertyChange(null,null,null);
    }
    public void goToListasPropostas() {
        fsm.goToListasPropostas();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToGestaoOrientadores() {
        fsm.goToGestaoOrientadores();
        pcs.firePropertyChange(null,null,null);
    }

    public void goToListaOrientadores() {
        fsm.goToListaOrientadores();
        pcs.firePropertyChange(null,null,null);
    }

    public String consulta(ListTypes id) {
        var ret = fsm.consulta(id);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean atribOrientadores(int nextState) {
        var ret = fsm.atribOrientadores(nextState);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String listasOrient(int i) {
        var ret = fsm.listasOrient(i);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public Map<Proposta, ArrayList<Aluno>> atribPropAutom() {
        var ret = fsm.atribPropAutom();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String atribPropManual(String aluno, String prop) {
        var ret = fsm.atribPropManual(aluno,prop);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String atribOrientAutom() {
        var ret = fsm.atribOrientAutom();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String consultaOrient() {
        var ret = fsm.consultaOrient();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String adicionaOrient(String d, String p) {
        var ret = fsm.adicionaOrient(d,p);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean gestaoAlunos(int nextState) {
        var ret = fsm.gestaoAlunos(nextState);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public Map<Aluno,Proposta> getAtribuicoes(){return fsm.getAtribuicoes();}

    public boolean gestaoDocentes(String csvname) {
        var ret = fsm.gestaoDocentes(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean gestaoPropostas(String csvname) {
        var ret = fsm.gestaoPropostas(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addAlunos(String csvname) {
        var ret = fsm.addAlunos(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addDocentes(String csvname) {
        var ret = fsm.addDocentes(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addProposta(String linha) {
        var ret = fsm.addProposta(linha);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String addCandidaturas(String csvname) {
        var ret = fsm.addCandidaturas(csvname);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String removeAluno(long id){
        var ret = fsm.removeAluno(id);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String removeProposta(String codigo){
        var ret = fsm.removeProposta(codigo);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String removeDocente(String email){
        var ret = fsm.removeDocente(email);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj){
        var ret = fsm.editAluno(id, nome, email, curso, ramo, classif, estEProj);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String editDocente(String nome, String email){
        var ret = fsm.editDocente(nome, email);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos){
        var ret = fsm.editT1(codigo, titulo, entidade, ramos);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos){
        var ret = fsm.editT2(codigo, titulo, docente, ramos);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String editT3(String codigo, String titulo, long aluno){
        var ret = fsm.editT3(codigo, titulo, aluno);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String exportaCSV() {
        var ret = fsm.exportaCSV();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<Aluno> getAlunos() {
        return fsm.getAlunos();
    }

    public ArrayList<Docente> getDocentes() {
        return fsm.getDocentes();
    }

    public ArrayList<Proposta> getPropostas() {
        return fsm.getPropostas();
    }

    public ArrayList<Candidatura> getCandidaturas() {
        return fsm.getCandidaturas();
    }

    public boolean isConfigClosed() {
        return fsm.isConfigClosed();
    }

    public String setConfigClosed(boolean configClosed) {
        var ret = fsm.setConfigClosed(configClosed);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean isOpCandClosed() {
        return fsm.isOpCandClosed();
    }

    public String setOpCandClosed(boolean OpCandClosed) {
        var ret = fsm.setOpCandClosed(OpCandClosed);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean isAtribPropClosed() {
        return fsm.isAtribPropClosed();
    }

    public String setisAtribPropClosed(boolean isAtribPropClosed) {
        var ret = fsm.setisAtribPropClosed(isAtribPropClosed);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean canCloseAtribProp() {
        return fsm.canCloseAtribProp();
    }

    public boolean isAtribOrientClosed() {
        return fsm.isAtribOrientClosed();
    }

    public String setAtribOrientClosed(boolean atribOrientClosed) {
        var ret = fsm.setAtribOrientClosed(atribOrientClosed);
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String getRamosInfo() {
        return fsm.getRamosInfo();
    }

    public boolean verificaConfig() {
        return fsm.verificaConfig();
    }

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        return fsm.getAlunosComAutoproposta();
    }

    public ArrayList<Aluno> getAlunosComCandRegistada() {
        return fsm.getAlunosComCandRegistada();
    }

    public ArrayList<Aluno> getAlunosSemCandRegistada() {
        var ret = fsm.getAlunosSemCandRegistada();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }
    public ArrayList<Proposta> getPropostasByCandidatura(Aluno a){
        return fsm.getPropostasByCandidatura(a);
    }
    public Aluno getAlunoByID(Long id) {
        return fsm.getAlunoByID(id);
    }
    public ArrayList<Proposta> getAutoPropostas() {
        var ret = fsm.getAutoPropostas();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<Proposta> getPropostasDocentes() {
        var ret = fsm.getPropostasDocentes();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<Proposta> getPropostasComCand() {
        var ret = fsm.getPropostasComCand();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<Proposta> getPropostasSemCand() {
        var ret = fsm.getPropostasSemCand();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<String> getAlunosComPropostaAtribuida() {
        var ret = fsm.getAlunosComPropostaAtribuida();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public ArrayList<Aluno> getAlunosSemPropostaAtribuida() {
        var ret = fsm.getAlunosSemPropostaAtribuida();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public boolean isCandidaturasDone() {
        var ret = fsm.isCandidaturasDone();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public String showAtribuicoes() {
        var ret = fsm.showAtribuicoes();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public void load() {
        fsm = fsm.load();
        pcs.firePropertyChange(null,null,null);
    }

    public boolean save() {
        var ret = fsm.save();
        pcs.firePropertyChange(null,null,null);
        return ret;
    }

    public void undo(){
        careTaker.undo();
        pcs.firePropertyChange(null,null,null);
    }

    public void redo(){
        careTaker.redo();
        pcs.firePropertyChange(null,null,null);
    }


}


package pa.isec.pa.apoio_poe.model.fsm;

import pa.isec.pa.apoio_poe.model.Data;
import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.model.fsm.states.FaseInicio;
import pa.isec.pa.apoio_poe.model.memento.IMemento;
import pa.isec.pa.apoio_poe.model.memento.IOriginator;
import pa.isec.pa.apoio_poe.model.memento.Memento;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Context implements Serializable, IOriginator {
    static final long serialVersionUID=100L;
    static final String FILENAME = "old.dat";
    Data data;
    private IState state;

    public Context() {
        this.data=new Data();
        this.state = new FaseInicio(this,data);
    }


    //--------------states
    public State getState() {
        return state.getState();
    }

    void changeState(IState newState) {
        this.state = newState;
    }

    public void next(){
        state.next();
    }

    public void previous(){
        state.previous();
    }

    public void goToGestaoAlunos(){state.goToGestaoAlunos();}
    public void goToGestaoPropostas(){state.goToGestaoPropostas();}
    public void goToGestaoDocentes(){state.goToGestaoDocentes();}
    public void goToGestaoCandidaturas(){state.goToGestaoCandidaturas();}
    public void goToListasAlunos(){state.goToListasAlunos();}
    public void goToListasPropostas(){state.goToListasPropostas();}
    public void goToGestaoOrientadores(){state.goToGestaoOrientadores();}
    public void goToListaOrientadores(){state.goToListaOrientadores();}

    public String consulta(ListTypes id){save(); return state.consulta(id);}
    public boolean atribOrientadores(int nextState){save(); return state.atribOrientadores(nextState);}
    public String listasOrient(int i) {save();  return state.listasOrient(i);}
    public Map<Proposta,ArrayList<Aluno>> atribPropAutom() {save(); return state.atribPropAutom();}
    public String atribPropManual(String aluno, String prop) {
        save(); return state.atribPropManual(aluno,prop);
    }
    public String atribOrientAutom(){ save();
        String resultado;
        if(!isAtribOrientClosed()){
            resultado = state.atribOrientAutom();
        }else{
            resultado = "Esta fase encontra-se fechada, impossível realizar alterações";
        }
        return resultado;
    }
    public String editOrientadores(String codigo, String nome, String email, String novoEmail){
        save();
        String resultado;
        if(!isAtribOrientClosed()){
            resultado = state.editOrientadores(codigo, nome, email);
        }else{
            resultado = "Esta fase encontra-se fechada, impossível realizar alterações";
        }
        return resultado;
    }
    public String eliminarOrientador(String codigo){
        save();
        String resultado;
        if(!isAtribOrientClosed()){
            resultado = state.eliminarOrientador(codigo);
        }else{
            resultado = "Esta fase encontra-se fechada, impossível realizar alterações";
        }
        return resultado;
    }
    public String editCandidaturas(long numero, ArrayList<Proposta> props){ save();
        String resultado;
        if(!isOpCandClosed()){
            resultado = state.editCandidatura(numero, props);
        }else{
            resultado = "Esta fase encontra-se fechada, impossível realizar alterações";
        }
        return resultado;
    }
    public String consultaOrient(){save(); return state.consultaOrient();}
    public String adicionaOrient(String d, String p){save();
        String result;
        if(!isAtribOrientClosed()) {
            result = state.adicionaOrient(d,p);
        }else{
            result = "Esta fase encontra-se fechada, impossível realizar alterações";
        }
        return result;
    }
    public boolean gestaoAlunos(int nextState){
        save(); return state.gestaoAlunos(nextState);
    }
    public boolean gestaoDocentes(String csvname){
        save(); return state.gestaoDocentes(csvname);
    }
    public boolean gestaoPropostas(String csvname) { save(); return state.gestaoPropostas(csvname); }
    public String addAlunos(String csvname){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.addAlunos(csvname);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }
    public String addDocentes(String csvname){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.addDocentes(csvname);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }

    public String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos){ return state.editT1(codigo, titulo, entidade, ramos);}
    public String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos){ return state.editT2(codigo, titulo, docente, ramos);}
    public String editT3(String codigo, String titulo, long aluno){return state.editT3(codigo, titulo, aluno);}
    public String removeDocente(String email){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.removeDocente(email);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }

    public String editDocente(String email, String nome){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.editDocente(nome, email);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }

    public String addDocente(String linha){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.addDocente(linha);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }
    public String addPropostas(String csvname){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.addPropostas(csvname);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }
    public String addProposta(String linha){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.addProposta(linha);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }
    public String removeProposta(String codigo){
        save();
        String resultado;
        if(!isConfigClosed()) {
            resultado = state.removeProposta(codigo);
        }
        else{
            resultado = "Fase de Configuração encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }
    public String addCandidaturas(String csvname){
        save();
        String resultado;
        if(!isOpCandClosed()) {
           resultado =  state.addCandidaturas(csvname);
        }else{
            resultado =  "Fase de Opções de candidaturas encerrada. Impossível fazer alterações.";
        }
         return resultado;
    }
    public String addCandidatura(String linha){
        save();
        String resultado;
        if(!isOpCandClosed()) {
            resultado =  state.addCandidatura(linha);
        }else{
            resultado =  "Fase de Opções de candidaturas encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }

    public String removeCandidatura(long numero){
        save();
        String resultado;
        if(!isOpCandClosed()) {
            resultado =  state.removeCandidatura(numero);
        }else{
            resultado =  "Fase de Opções de candidaturas encerrada. Impossível fazer alterações.";
        }
        return resultado;
    }

    public String exportaCSV(){save(); return state.exportaCSV();}
    //------------------



    // from Data
    public ArrayList<Aluno> getAlunos(){
        return data.getAlunos();
    }
    public ArrayList<Docente> getDocentes(){
        return data.getDocentes();
    }
    public ArrayList<Proposta> getPropostas(){
        return data.getPropostas();
    }
    public ArrayList<Candidatura> getCandidaturas(){return data.getCandidaturas();}
    public boolean isConfigClosed() {return data.isConfigClosed();   }
    public String setConfigClosed(boolean configClosed) {
        String result;
        save();
        if(data.isConfigClosed())
            result =  "Fase já fechada.";
        else {
            if (verificaConfig()) {
                data.setConfigClosed(configClosed);
                result =  "Fase fechada!";
                next();
            } else result = "Impossível fechar fase. Apenas deverá ser " +
                    "permitido o fecho da fase se, para cada ramo, o número total de propostas for igual ou " +
                    "superior ao número de alunos.";
        }
        return result;
     }
    public boolean isOpCandClosed() {return data.isOpCandClosed();}
    public String setOpCandClosed(boolean OpCandClosed) {
        String result;
        save();
        if(!isConfigClosed()){
            result = "A fase anterior precisa de ser fechada primeiro.";
        }
        else {
            if (isOpCandClosed()) {
                result = "Fase já fechada.";
            } else {
                data.setOpCandClosed(OpCandClosed);
                result = "Fase fechada!";
                next();
            }
        }
        return result;
    }
    public boolean isAtribPropClosed() {return data.isAtribPropClosed();   }
    public String setisAtribPropClosed(boolean isAtribPropClosed) {
        String result;
        save();
        if(isCandidaturasDone()) {
            if (!isOpCandClosed()) {
                result = "A fase anterior precisa de ser fechada primeiro.";
            } else {
                if (isAtribPropClosed()) {
                    result = "Fase já fechada.";
                } else {
                    if(canCloseAtribProp()) {
                        data.setAtribPropClosed(isAtribPropClosed);
                        result = "Fase fechada!";
                        next();
                    }
                    else{
                        result = "Nem todos os alunos com candidaturas submetidas possuem um projeto atribuído!";
                    }
                }
            }
        }
        else
            result = "Existem alunos com candidaturas por atribuir.";
        return result;
    }
    public String removePropostaAtribuida(long numero){
        save();
        String resultado;
        if(!isAtribPropClosed()) {
            resultado =  state.removePropostaAtribuida(numero);
        }else{
            resultado = "Fase já fechada!";
        }
        return resultado;
    }
    public boolean canCloseAtribProp() {
       return data.canCloseAtribProp();
    }
    public boolean isAtribOrientClosed() {
        return data.isAtribOrientClosed();
    }
    public String setAtribOrientClosed(boolean atribOrientClosed) {
        String result;
        save();
        if(!isAtribOrientClosed()){
            data.setAtribOrientClosed(atribOrientClosed);
            result = "Fase fechada!";
            atribOrientadores(6);
        }else{
            result = "Fase já fechada!";
        }
        return result;
    }
    public String getRamosInfo() {return data.getRamosInfo();}
    public boolean verificaConfig(){
        return data.verificaConfig();
    }
    public Aluno getAlunoByID(Long id) {return data.getAlunoByID(id);}
    public ArrayList<Aluno> getAlunosComAutoproposta(){return data.getAlunosComAutoproposta();}
    public ArrayList<Aluno> getAlunosComCandRegistada(){return data.getAlunosComCandRegistada();}
    public ArrayList<Aluno> getAlunosSemCandRegistada(){return data.getAlunosSemCandRegistada();}
    public ArrayList<Proposta> getAutoPropostas(){return data.getAutoPropostas();}
    public ArrayList<Proposta> getPropostasDocentes(){return data.getPropostasDocentes();}
    public ArrayList<Proposta> getPropostasComCand(){return data.getPropostasComCand();}
    public ArrayList<Proposta> getPropostasSemCand(){return data.getPropostasSemCand();}
    public Map<Aluno,Proposta> getAtribuicoes(){return data.getAtribuicoes();}
    public ArrayList<Proposta> getPropostasByCandidatura(Aluno a){
        return data.getPropostasByCandidatura(a);
    }
    public ArrayList<String> getAlunosComPropostaAtribuida(){
        return data.getAtribuicoesPref();
    }
    public ArrayList<Aluno> getAlunosSemPropostaAtribuida(){
        return data.getAlunosSemPropostaAtribuida();
    }
    public boolean isCandidaturasDone() {
        return data.isCandidaturasDone();
    }
    public String showAtribuicoes(){return data.showAtribuicoes();}
    public String addAluno(String linha){return state.addAluno(linha);}
    public String removeAluno(long id){return state.removeAluno(id);}
    public String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj){
        return state.editAluno(id, nome, email, curso, ramo, classif, estEProj);
    }
    // =======================>

    public Context load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            return (Context) ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }
    public boolean save(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILENAME))){
            oos.writeObject(this);
        }catch(IOException e){
            return false;
        }

        return true;
    }

    @Override
    public IMemento saveMemento() {
        return new Memento(this);
    }

    @Override
    public void restore(IMemento memento) {
        Object obj = memento.getSnapshot();
        if (obj instanceof Context m) {
            this.data = m.data;
            this.state = getState().createState(this, data);
        }
    }
}

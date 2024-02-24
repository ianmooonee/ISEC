package pa.isec.pa.apoio_poe.model;

import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.model.fsm.StateAdapter;
import pa.isec.pa.apoio_poe.model.memento.IMemento;
import pa.isec.pa.apoio_poe.model.memento.IOriginator;
import pa.isec.pa.apoio_poe.model.memento.Memento;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;
import pa.isec.pa.apoio_poe.ui.utils.PAInput;
import pa.isec.pa.apoio_poe.ui.utils.Support;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data implements Serializable{
    static final long serialVersionUID=100L;
    private final ArrayList<Aluno> alunos;
    private final ArrayList<Docente> docentes;
    private final ArrayList<Proposta> propostas;
    private final ArrayList<Candidatura> candidaturas;
    private final HashMap<Aluno, Proposta> atribuicoes;
    private final HashMap<Proposta, Docente> atribOrient;


    private boolean isConfigClosed;
    private boolean isOpCandClosed;
    private boolean isAtribPropClosed;

    private boolean isAtribOrientClosed;

    public Data() {
        this.alunos = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.propostas = new ArrayList<>();
        this.candidaturas = new ArrayList<>();
        this.atribuicoes = new HashMap<>();
        this.atribOrient = new HashMap<>();
        this.isConfigClosed = false;
        this.isOpCandClosed = false;
        this.isAtribPropClosed = false;
        this.isAtribOrientClosed = false;
    }

    public ArrayList<String> getAtribuicoesPref() {
        int num;
        Aluno a;
        ArrayList<String> prefAluno=new ArrayList<>();

        for(Map.Entry<Aluno, Proposta> entry : atribuicoes.entrySet()){
            num=getNumPref(entry);
            a=entry.getKey();
            if(entry.getValue() instanceof T3Autoprop){
                num=1;
                prefAluno.add(a.toString()+" - <T3"+":"+entry.getValue().getCodigo()+"> Preferencia: "+num+ "\tAutoproposta\n");
            }
            else if(entry.getValue() instanceof T2Projeto){
                if(num==-1)
                    prefAluno.add(a.toString()+" - <T2"+":"+entry.getValue().getCodigo()+"> Preferencia: Pre Atribuido\n");
                else
                    prefAluno.add(a.toString()+" - <T2"+":"+entry.getValue().getCodigo()+"> Preferencia: "+num+"\n");
            }
            else{
                if(num==-1)
                    prefAluno.add(a.toString()+" - <T1"+":"+entry.getValue().getCodigo()+"> Preferencia: Pre Atribuido\n");
                else
                    prefAluno.add(a.toString()+" - <T1"+":"+entry.getValue().getCodigo()+"> Preferencia: "+num+"\n");
            }
        }

        return prefAluno;
    }

    public Set<Aluno> getAlunosComPropostaAtribuidaAsSet(){
        return atribuicoes.keySet();
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public ArrayList<Docente> getDocentes() {
        return docentes;
    }

    public ArrayList<Proposta> getPropostas() {
        return propostas;
    }

    public ArrayList<Candidatura> getCandidaturas() {
        return candidaturas;
    }
    public Map<Aluno,Proposta> getAtribuicoes(){return atribuicoes;}
    public Map<Proposta,Docente> getOrientadores(){return atribOrient;}

    public String listasOrient(int i) {
        switch(i){
            case 1 -> {
                return listaUm();
            }
            case 2 -> {
                return listaDois();
            }
            case 3 -> {
                return listaTres();
            }
            default -> {return null;}
        }
    }

    private String listaTres() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    private String listaDois() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Aluno, Proposta> entry : getAtribuicoes().entrySet()){
            sb.append("\nAluno ").append(entry.getKey().getId().toString()).append(" atribuido à Proposta ").append(entry.getValue());
        }
        return sb.toString();
    }

    private String listaUm() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Aluno, Proposta> entry : getAtribuicoes().entrySet()){
            sb.append("\nAluno ").append(entry.getKey().getId().toString()).append(" atribuido à Proposta ").append(entry.getValue()).append(" com orientador ").append(getOrientadorByAtrib(entry.getValue()));
        }
        return sb.toString();
    }

    public String atribOrientAutom() {
        StringBuilder sb = new StringBuilder();
        for(Proposta p:getPropostas()){
            if(p instanceof T2Projeto) {
                addAtribuicaoOrient(getDocenteByID(((T2Projeto) p).getDocenteProp()), p);
                sb.append("\n<Pré-atribuído> O docente ").append(((T2Projeto) p).getDocenteProp()).append(" ficou orientador da proposta ").append(p.getCodigo());
            }
        }
        return sb.toString();
    }

    public Map<Proposta,ArrayList<Aluno>> atribPropAutom() {
        ArrayList<Proposta> allPropostas = getPropostas();
        Map<Proposta, ArrayList<Aluno>> mapa = new HashMap<>();
        //atribui T3
        for(Proposta p : allPropostas) {
            if (p.getAlunoAtrib() != null) {
                addAtribuicao(getAlunoByID(p.getAlunoAtrib()), p);
            }
            else {
                if(isPropInCand(p)) {
                    ArrayList<Aluno> alunos = getArrayPref(p);
                    if (!alunos.isEmpty()) {
                        ArrayList<Aluno> sortedByClassif = sort(alunos);
                        ArrayList<Aluno> empates = alunosComClassif(sortedByClassif, p instanceof T1Estagio);
                        if (empates.size() > 1) {
                            mapa.put(p, empates);
                        }
                        addAtribuicao(empates.get(0), p);
                    }
                }
            }
        }
        return mapa;
    }

    public String removePropostaAtribuida(long numero){
        Aluno a=getAlunoByID(numero);

        if(atribuicoes.remove(a)!=null)
            return "Proposta removida";
        return "Proposta nao encontrada";
    }

    private static ArrayList<Aluno> alunosComClassif(ArrayList<Aluno> sortedList, boolean b) {
        ArrayList<Aluno> arr = new ArrayList<>();
        if(!b) {
            for (Aluno a : sortedList)
                if (a.getClassif() == sortedList.get(0).getClassif())
                    arr.add(a);
        }
        else{
            for(Aluno l : sortedList)
                if(l.isEstEProj())
                    if (l.getClassif() == sortedList.get(0).getClassif())
                        arr.add(l);
        }
        return arr;
    }

    public String atribPropManual(String aluno, String prop){
        for(Proposta p : getPropostas())
            if(p.getAlunoAtrib()!=null){
                addAtribuicao(getAlunoByID(p.getAlunoAtrib()),p);
            }

        StringBuilder sb = new StringBuilder();
        Aluno a = getAlunoByID(PAInput.readLong(aluno));
        if(a==null){
            sb.append("\nNúmero de aluno ").append(aluno).append(" inválido (não existente).");
            return sb.toString();
        }
        Proposta p = getPropostaByID(prop);
        if(p==null) {
            sb.append("\nProposta ").append(aluno).append(" inválida (não existente).");
            return sb.toString();
        }
        if(!isAlunoAtribuido(a)){
            addAtribuicao(a,p);
            sb.append("\nAluno ").append(aluno).append(" atribuido à proposta ").append(prop);
            return sb.toString();
        }
        else sb.append("\nAluno ").append(aluno).append(" já com proposta atribuída");

        if(addAtribuicao(a,p)!=null) sb.append("\n>>>Aluno ").append(aluno).append(" associado a proposta ").append(prop);

        return sb.toString();
    }

    public static ArrayList<Aluno> sort(ArrayList<Aluno> list)
    {
        Collections.sort(list);
        return list;
    }

    public String addAluno(String linha) {
        int count=0;
        StringBuilder str = new StringBuilder();

        String[] values=linha.split(StateAdapter.COMMA_DELIMITER);

        String result =validaLinhaAluno(values, count);

        if(!result.isEmpty())
            str.append(result);

        else {
            if(createAluno(values)==null)
                return str.append("Erro nos parametros").toString();
            addAluno(createAluno(values));

        }

        return str.toString();
    }

    public String addAlunos(String csvname) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvname))) {
            int count =0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(StateAdapter.COMMA_DELIMITER);
                count++;

                String result =validaLinhaAluno(values, count);
                if(!result.isEmpty()) str.append(result);

                else addAluno(createAluno(values));
            }
        }catch(IOException e) {
            str.append("Nome de csv inválido!");
            // e.printStackTrace();
        }
        return str.toString();
    }

    public String validaLinhaAluno(String[] metadata, int count) {
        StringBuilder str = new StringBuilder();
        if(metadata.length != 7)
            return "Insira apenas e so as informacoes necessarias para a adicao de um novo aluno";
        if(!metadata[6].equalsIgnoreCase("TRUE")&&!metadata[6].equalsIgnoreCase("FALSE"))
            str.append("\nValor de estagioBool inválido na linha ").append(count);
        if(!metadata[3].equals("LEI")&&!metadata[3].equals("LEI-PL"))
            str.append("\nValor de curso invalido na linha ").append(count);
        if(!metadata[4].equals("DA")&&!metadata[4].equals("SI")&&!metadata[4].equals("RAS"))
            str.append("\nValor de ramo inválido na linha ").append(count);
        try{
            if(Double.parseDouble(metadata[5])>1||Double.parseDouble(metadata[5])<0)
                str.append("\nValor de classificação inválido na linha ").append(count);
        } catch (NumberFormatException e) {
            str.append("\nValor de classificação inválido na linha ").append(count);
            //e.printStackTrace();
        }
        for(Aluno aluno : getAlunos()){
            if(aluno.getId().toString().equals(metadata[0])) str.append("\nValor de ID já existente na linha ").append(count);
        }
        return str.toString();
    }

    public String addPropostas(String csvname) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvname))) {
            int count = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(StateAdapter.COMMA_DELIMITER);
                count++;

                verificaTipoProposta(count, str, values);
            }
        } catch (IOException e) {
            str.append("Nome de csv inválido.");
            //e.printStackTrace();
        }

        return str.toString();
    }

    public String addProposta(String linha) {
        int count=0;
        StringBuilder str = new StringBuilder();

        String[] values=linha.split(StateAdapter.COMMA_DELIMITER);

        return verificaTipoProposta(count, str, values);
    }

    public String verificaTipoProposta(int count, StringBuilder str, String[] values) {
        String result =validaLinhaPropostas(values, count);

        if(!result.isEmpty())
            str.append(result);
        else {
            switch (values[0]) {
                case "T1" -> {
                    ArrayList<String> ramos = verificaRamos(values[2]);
                    if(!ramos.isEmpty()) addPropostas(createT1(values,ramos));
                    else str.append("Ramos inválidos, linha ").append(count).append("\n");
                }
                case "T2" -> {
                    ArrayList<String> ramos = verificaRamos(values[2]);
                    if(!ramos.isEmpty()) addPropostas(createT2(values,ramos));
                    else str.append("Ramos inválidos, linha ").append(count).append("\n");
                }
                case "T3" ->
                        addPropostas(createT3(values));
            }
        }
        return str.toString();
    }

    public ArrayList<String> verificaRamos(String metadata){
        ArrayList<String> ramos = new ArrayList<>();
        if(metadata.equals("DA") || metadata.equals("SI") || metadata.equals("RAS")) {
            ramos.add(metadata);
            return ramos;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(metadata, "|");
        while(stringTokenizer.hasMoreTokens()){
            String stok = stringTokenizer.nextToken();
            if(stok.equals("DA") ||stok.equals("SI") ||stok.equals("RAS"))
                ramos.add(stok);
        }
        Set<String> set  = new HashSet<>();
        for(String str : ramos) {
            boolean flagForDuplicate = set.add(str);
            if(!flagForDuplicate)
                return new ArrayList<>();//System.out.println(str + " is duplicate element");
        }
        return ramos;
    }

    public T1Estagio createT1(String[] metadata, ArrayList<String> ramos){
        if(metadata.length==6)
            return new T1Estagio(metadata[1], ramos, metadata[3],getAlunoByID( Long.parseLong(metadata[5]))!=null ? Long.parseLong(metadata[5]):null,metadata[4]);
        return new T1Estagio(metadata[1], ramos, metadata[3],null,metadata[4]);
    }
    public T2Projeto createT2(String[] metadata, ArrayList<String> ramos){
        if(metadata.length==6)
            return new T2Projeto(metadata[1], ramos, metadata[3], getAlunoByID( Long.parseLong(metadata[5]))!=null ? Long.parseLong(metadata[5]):null, metadata[4]);
        return new T2Projeto(metadata[1], ramos, metadata[3], null,metadata[4]);
    }
    public T3Autoprop createT3(String[] metadata){
        return new T3Autoprop(metadata[1],  metadata[2], Long.parseLong(metadata[3]), Long.parseLong(metadata[3]));
    }

    public String validaLinhaPropostas(String[] metadata, int count) { //falta verificar ramo e tratar casos como por ex: "T1,P031,DA|SI,Controlo de estacionamento,Rotundas.SA"
        StringBuilder str = new StringBuilder();

        if(metadata.length<2)
            return "Proposta invalida devido ao tamanho, linha "+count+"\n";
        for(Proposta p : getPropostas()){
            if(p.getCodigo().equals(metadata[1])){
                return "Codigo da proposta "+p.getCodigo()+" repetido na linha "+count+"\n";
            }
        }
        switch (metadata[0]) {
            case "T1" -> {
                if(metadata.length<5) {
                    str.append("Proposta com campos em falta, linha ").append(count).append("\n");
                    return str.toString();
                }
                if (metadata[3].isEmpty()) {
                    str.append("Proposta sem titulo, linha ").append(count).append("\n");
                    return str.toString();
                }
                if (metadata[4].isEmpty()) {
                    str.append("Proposta sem entidade acolhedora, linha ").append(count).append("\n");
                    return str.toString();
                }
            }
            case "T2" -> {
                if(metadata.length<5) {
                    str.append("Proposta com falta de campos, linha ").append(count).append("\n");
                    return str.toString();
                }
                if (metadata[3].isEmpty()) {
                    str.append("Proposta sem titulo, linha ").append(count).append("\n");
                    return str.toString();
                }
                if(getDocenteByID(metadata[4])== null) {
                    str.append("Docente atribuido não existe, linha ").append(count).append("\n");
                    return str.toString();
                }
                if (metadata.length > 5) {
                    try {
                        Long.parseLong(metadata[5]);
                    } catch (NumberFormatException e) {
                        str.append("Erro no numero de aluno na linha ").append(count).append("\n");
                    }
                }
            }
            case "T3" -> {
                if(metadata.length<4) {
                    str.append("Proposta com falta de campos, linha ").append(count).append("\n");
                    return str.toString();
                }
                if (metadata[2].isEmpty()) {
                    str.append("Proposta sem titulo");
                }
                for (Proposta p : getPropostas()) {
                    if (p instanceof T3Autoprop) {
                        if (p.getAlunoAtrib() == Long.parseLong(metadata[3])) {
                            str.append("Ja existe uma autoproposta com este aluno, linha ").append(count).append("\n");
                        }
                    }
                }
                for(Aluno a : getAlunos()){
                    if(a.getId()==Long.parseLong(metadata[3]))
                        return str.toString(); //return "";
                }
                str.append("T3 - Aluno nao encontrado, linha ").append(count).append("\n");
            }
            default ->
                    str.append(" Opcao de proposta nao encontrada, linha ").append(count).append("\n");

        }
        return str.toString();
    }

    public String addDocentes(String csvname) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvname))) {
            int count =0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(StateAdapter.COMMA_DELIMITER);
                count++;

                String result =validaLinhaDocentes(values, count);
                if(!result.isEmpty()) str.append(result);

                else addDocente(createDocente(values));
            }
        }catch(IOException e) {
            str.append("Nome de csv inválido.");
            // e.printStackTrace();
        }
        return str.toString();
    }

    public String addDocente(String linha) {
        int count=0;
        StringBuilder str = new StringBuilder();

        String[] values=linha.split(StateAdapter.COMMA_DELIMITER);

        String result =validaLinhaDocentes(values, count);

        if(!result.isEmpty())
            str.append(result);

        else
            addDocente(createDocente(values));

        return str.toString();
    }

    public Docente createDocente(String[] metadata){
        return new Docente(metadata[1],metadata[0]);
    }

    public String validaLinhaDocentes(String[] metadata, int count) {
        for(Docente docente: getDocentes()){
            if(docente.getEmail().equals(metadata[1])) return "Valor de email já existente na linha " + count;
        }
        return "";
    }

    public String addCandidaturas(String csvname) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvname))) {
            int count =0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(StateAdapter.COMMA_DELIMITER);
                count++;

                String result = validaLinhaCandidatura(values, count);
                if(!result.isEmpty()) str.append(result);

                else addCandidaturas(createCandidatura(values));
            }
        }catch(IOException e) {
            str.append("Nome de csv inválido.");
            e.printStackTrace();
        }
        return str.toString();
    }

    public String addCandidatura(String linha) {
        int count=0;
        StringBuilder str = new StringBuilder();

        String[] values=linha.split(StateAdapter.COMMA_DELIMITER);

        return validaLinhaCandidatura(values, count);
    }

    public String editCandidatura(long numero, ArrayList<Proposta> props){
        Aluno a=getAlunoByID(numero);

        if(a!=null){
            props.removeIf(p -> (
                    (!propostas.contains(p))
                            || (p instanceof T1Estagio && p.getAlunoAtrib() != null)
                            || (p instanceof T2Projeto && p.getAlunoAtrib() != null))
                            || (p instanceof T3Autoprop));

            for(Candidatura c : candidaturas){
                if(c.getAluno().equals(a)){
                    c.setPropostas(props);
                }
            }
            return "Candidatura alterada";
        }

        return "Erro ao encontrar candidatura do aluno";
    }

    public String removeCandidatura(long numero){
        Aluno a=getAlunoByID(numero);
        if(a!=null) {
            candidaturas.removeIf(c -> c.getAluno().equals(a));
            return "Candidatura removida";
        }
        return "Erro ao remover";
    }

    public Candidatura createCandidatura(String[] metadata){
        ArrayList<Proposta> arrayProp = new ArrayList<>();
        for(int i=1;i<metadata.length;i++){
            arrayProp.add(getPropostaByID(metadata[i]));
        }
        return new Candidatura(getAlunoByID(metadata[0]),arrayProp);
    }

    public String validaLinhaCandidatura(String[] metadata, int count) {
        StringBuilder str = new StringBuilder();
        if(metadata.length<2){
            str.append("\nNúmero de dados inválido na linha ").append(count);
            return str.toString();
        }

        //aluno existe?
        Aluno a =getAlunoByID(metadata[0]);
        if(a==null){
            str.append("\nAluno ").append(metadata[0]).append("não existe no sistema na linha ").append(count);
            return str.toString();
        }

        //codigo de proposta válido? proposta existe?
        int flag;
        for (int i = 1; i < metadata.length; i++) {
            flag=0;
            for(Proposta p : getPropostas()) {
                if (p.getCodigo().equals(metadata[i])) {
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                str.append("\nCódigo de proposta não encontrado no registo de propostas na linha ").append(count);
                return str.toString();
            }
        }

        //aluno é de T3/preatribuido?
        if(getAlunosPreAtribuidos().contains(a)) {
            str.append("\nAluno previamente atribuído numa proposta na linha ").append(count);
            return str.toString();
        }

        //aluno já submeteu candidatura?
        for(Candidatura c : getCandidaturas()){
            if(c.getAluno()!=null && c.getAluno().getId()==Long.parseLong(metadata[0])){
                str.append("\nCandidatura já submetida pelo aluno ").append(metadata[0]).append(" na linha ").append(count);
                return str.toString();
            }
        }

        //aluno já foi atribuído? ou já-se atribuiu?
        for(Proposta p : getPropostasComAluno()) {
            if (p.getAlunoAtrib().equals(Long.parseLong(metadata[0]))) {
                str.append("\nAluno já atribuído a uma proposta na linha ").append(count);
                return str.toString();
            }
            for (int i = 1; i < metadata.length; i++) {
                if (p.getCodigo().equals(metadata[i])) {
                    str.append("\nProposta escolhida foi previamente atribuída a um aluno. linha ").append(count);
                    // metadata = Support.removeTheElement(metadata,i);
                    return str.toString();
                }
            }
        }

        //proposta válida para o ramo do aluno?
        for (int i = 1; i < metadata.length; i++) {
            Proposta p = getPropostaByID(metadata[i]);
            if((( p instanceof T1Estagio) && !((T1Estagio) p).getRamos().contains(a.getRamo()))
                    ||(( p instanceof T2Projeto) && !((T2Projeto) p).getRamos().contains(a.getRamo())))
                str.append("\nProposta dada não corresponde ao ramo do aluno na linha ").append(count);
        }
        return str.toString();
    }

    public Aluno createAluno(String[] metadata){
        try{Long.parseLong(metadata[0]);}catch (Exception e){return null;}

        return new Aluno(
                Long.parseLong(metadata[0]),
                metadata[1],
                metadata[2],
                metadata[3],
                metadata[4],
                Double.parseDouble(metadata[5]),
                Boolean.parseBoolean(metadata[6]));
    }

    public String addDocente(String nome, String email){
        for(Docente d : docentes){
            if(d.getEmail().equals(email))
                return "O docente com este email ja existe";
        }

        docentes.add(new Docente(email, nome));
        return "Docente adicionado";
    }

    public String removeAluno(long id){
        for(Proposta p : propostas) {
            if (p.getAlunoAtrib() == id) {
                p.setAlunoAtrib(null);
            }
            if (p instanceof T3Autoprop && p.getAlunoAtrib() == id)
                propostas.remove(p);
        }

            if(alunos.removeIf(a -> a.getId() == id))
                return "Aluno removido";

        return "Aluno nao encontrado";
    }

    public String removeProposta(String codigo){
        if(propostas.removeIf(p -> p.getCodigo().equals(codigo)))
            return "Proposta removida";
        return "Erro a remover proposta";
    }

    public String removeDocente(String email){
        propostas.removeIf(p -> p instanceof T2Projeto);

        if(docentes.removeIf(d -> d.getEmail().equals(email)))
            return "Docente removido";

        return "Docente nao encontrado";
    }

    public String editAluno(long id, String nome, String email, String curso, String ramo, double classif, boolean estEProj){
        for(Aluno a : alunos)
            if(a.getId()==id) {
                a.setNome(nome);a.setEmail(email);a.setCurso(curso);a.setRamo(ramo);a.setClassif(classif);a.setEstEProj(estEProj);
                return "Aluno editado";
            }
        return "Aluno nao encontrado";
    }

    public String editT1(String codigo, String titulo, String entidade, ArrayList<String> ramos){
        for(Proposta p : propostas){
            if(p.getCodigo().equals(codigo)){
                p.setTitulo(titulo);((T1Estagio)p).setEntidadeAcolh(entidade);((T1Estagio)p).setRamos(ramos);
                return "Proposta editada";
            }
        }
        return "Erro ao editar";
    }

    public String editT2(String codigo, String titulo, String docente, ArrayList<String> ramos){
        for(Proposta p : propostas){
            if(p.getCodigo().equals(codigo)){
                p.setTitulo(titulo);((T2Projeto)p).setDocenteProp(docente);((T2Projeto)p).setRamos(ramos);
                return "Proposta editada";
            }
        }
        return "Erro ao editar";
    }

    public String editT3(String codigo, String titulo, long aluno){
        for(Proposta p : propostas){
            if(p.getCodigo().equals(codigo)){
                p.setTitulo(titulo);((T3Autoprop)p).setAlunoID(aluno);
                return "Proposta editada";
            }
        }
        return "Erro ao editar";
    }

    public String editDocente(String email, String nome){
        for(Docente d : docentes)
            if(Objects.equals(d.getEmail(), email)) {
                d.setNome(nome);
                return "Docente editado";
            }
        return "Docente nao encontrado";
    }

    public void addAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void addDocente(Docente docente) {
        docentes.add(docente);
    }

    public void addPropostas(Proposta proposta) {
        propostas.add(proposta);
    }

    public void addCandidaturas(Candidatura candidatura) {
        candidaturas.add(candidatura);
    }

    public Proposta addAtribuicao(Aluno a, Proposta p) {
        return atribuicoes.put(a, p);
    }

    public void addAtribuicaoOrient(Docente a, Proposta p) {
         atribOrient.put(p, a);
    }

    public ArrayList<Aluno> getAlunosEmDA() {
        return getAlunosEmRamo("DA");
    }

    public ArrayList<Aluno> getAlunosEmRAS() {
        return getAlunosEmRamo("RAS");
    }

    public ArrayList<Aluno> getAlunosEmSI() {
        return getAlunosEmRamo("SI");
    }

    public ArrayList<Aluno> getAlunosEmRamo(String ramo) {
        ArrayList<Aluno> tmp = new ArrayList<>();
        for (Aluno a : getAlunos()) {
            if (a.getRamo().equals(ramo)) tmp.add(a);
        }
        return tmp;
    }

    public ArrayList<Proposta> getPropostasEmDA() {
        return getPropostasEmRamo("DA");
    }

    public ArrayList<Proposta> getPropostasEmRAS() {
        return getPropostasEmRamo("RAS");
    }

    public ArrayList<Proposta> getPropostasEmSI() {
        return getPropostasEmRamo("SI");
    }

    public ArrayList<Proposta> getPropostasEmRamo(String ramo) {
        ArrayList<Proposta> tmp = new ArrayList<>();
        for (Proposta a : getPropostas()) {
            if (a instanceof T1Estagio && ((T1Estagio) a).getRamos().contains(ramo)) tmp.add(a);
            if (a instanceof T2Projeto && ((T2Projeto) a).getRamos().contains(ramo)) tmp.add(a);
            if (a instanceof T3Autoprop && getAlunoByID(((T3Autoprop) a).getAlunoID().toString()).getRamo().equals(ramo))
                tmp.add(a);
        }
        return tmp;
    }

    public ArrayList<Aluno> getAlunosComAutoproposta() {
        Long id_aluno;
        Aluno a;
        ArrayList<Aluno> alunosProp = new ArrayList<>();

        for (Proposta p : propostas) {
            if (p instanceof T3Autoprop) {
                id_aluno = p.getAlunoAtrib();
                a = getAlunoByID(id_aluno);
                if (a != null) {
                    alunosProp.add(a);
                }
            }
        }
        return alunosProp;
    }

    public ArrayList<Aluno> getAlunosAtribuidosT3(){
        ArrayList<Aluno> alunos = new ArrayList<>();

        for(Map.Entry<Aluno, Proposta> entry : atribuicoes.entrySet()){
            if(entry.getValue() instanceof T3Autoprop){
                alunos.add(entry.getKey());
            }
        }

        return alunos;
    }

    public ArrayList<Aluno> getAlunosComCandRegistada() {
        ArrayList<Aluno> alunosComCand = new ArrayList<>();

        for (Candidatura c : candidaturas) {
            alunosComCand.add(c.getAluno());
        }

        return alunosComCand;
    }

    public ArrayList<Aluno> getAlunosSemCandRegistada() {
        ArrayList<Aluno> alunosComCand;
        ArrayList<Aluno> alunosSemCand = new ArrayList<>();

        alunosComCand = getAlunosComCandRegistada();

        for (Aluno a : alunos) {
            if (!alunosComCand.contains(a)) {
                alunosSemCand.add(a);
            }
        }

        return alunosSemCand;
    }

    public ArrayList<Proposta> getPropostasComAluno() {
        ArrayList<Proposta> tmp = new ArrayList<>();
        for (Proposta a : getPropostas()) {
            if (a.getAlunoAtrib() != null) tmp.add(a);
        }
        return tmp;
    }

    public ArrayList<Proposta> getAutoPropostas() {
        ArrayList<Proposta> autoPropostas = new ArrayList<>();

        for (Proposta p : propostas) {
            if (p instanceof T3Autoprop)
                autoPropostas.add(p);
        }

        return autoPropostas;
    }

    public ArrayList<Proposta> getPropostasDocentes() {
        ArrayList<Proposta> propostasDocentes = new ArrayList<>();

        for (Proposta p : propostas) {
            if (p instanceof T2Projeto && p.getAlunoAtrib() != null)
                propostasDocentes.add(p);
        }

        return propostasDocentes;
    }

    public ArrayList<Proposta> getPropostasComCand() {
        ArrayList<Proposta> propostasComCand = new ArrayList<>();

        for (Candidatura c : candidaturas) {
            for (Proposta p : c.getPropostas()) {
                if (!propostasComCand.contains(p))
                    propostasComCand.add(p);
            }
        }

        return propostasComCand;
    }

    public ArrayList<Proposta> getPropostasSemCand() { //verificar se o numero de aluno é null??
        ArrayList<Proposta> propostasSemCand = new ArrayList<>();
        ArrayList<Proposta> propostasComCand = getPropostasComCand();

        for (Proposta p : propostas) {
            if (!propostasComCand.contains(p) && !propostasSemCand.contains(p))
                propostasSemCand.add(p);
        }

        return propostasSemCand;
    }

    public Proposta getPropostaByID(String metadata) {
        for (Proposta p : propostas) {
            if (p.getCodigo().equals(metadata))
                return p;
        }
        return null;
    }

    public Aluno getAlunoByID(String metadata) {
        for (Aluno a : alunos) {
            if (a.getId() == Long.parseLong(metadata))
                return a;
        }
        return null;
    }

    public Aluno getAlunoByID(Long id) {
        for (Aluno a : alunos) {
            if (a.getId().equals(id))
                return a;
        }
        return null;
    }

    public Docente getDocenteByID(String nome) {
        for (Docente d : docentes)
            if (d.getEmail().equals(nome))
                return d;
        return null;
    }

    public String getRamosInfo() {
        return "\nDA\nPropostas: " + getPropostasEmDA().size() +
                "\tAlunos: " + getAlunosEmDA().size() +
                "\n\nSI\nPropostas: " + getPropostasEmSI().size() +
                "\tAlunos: " + getAlunosEmSI().size() +
                "\n\nRAS\nPropostas: " + getPropostasEmRAS().size() +
                "\tAlunos: " + getAlunosEmRAS().size();
    }

    public ArrayList<Aluno> getAlunosAtribuidos() {
        ArrayList<Aluno> a = new ArrayList<>();
        for (Proposta p : propostas) {
            if ((p instanceof T1Estagio || p instanceof T2Projeto) && p.getAlunoAtrib() != null) {
                a.add(getAlunoByID(p.getAlunoAtrib().toString()));
            }
        }
        return a;
    }

    public ArrayList<Aluno> getAlunosNaoAtribuidos() {
        ArrayList<Aluno> atribuidos = getAlunosAtribuidos();
        ArrayList<Aluno> arr = new ArrayList<>();
        for (Aluno a : getAlunos()) {
            if (!atribuidos.contains(a))
                arr.add(a);
        }
        return arr;
    }

    public Candidatura getCandidaturaByAlunoID(Long id) {
        for (Candidatura c : getCandidaturas()) {
            if (c.getAluno().getId().equals(id))
                return c;
        }
        return null;
    }

    public ArrayList<Aluno> getAlunosMesmaPrefCand(Long aluno, String proposta, int preferencia) {
        ArrayList<Aluno> arr = new ArrayList<>();
        for (Candidatura c : candidaturas) {
            if (!c.getAluno().getId().equals(aluno) && c.getPropostas().get(preferencia).getCodigo().equals(proposta)) {
                arr.add(getAlunoByID(c.getAluno().toString()));
            }
        }
        return arr;
    }

    public ArrayList<Proposta> getPropostasEmT1() {
        ArrayList<Proposta> arr = new ArrayList<>();
        for (Proposta p : propostas) {
            if (p instanceof T1Estagio) {
                arr.add(p);
            }
        }
        return arr;
    }

    public ArrayList<Proposta> getPropostasEmT2() {
        ArrayList<Proposta> arr = new ArrayList<>();
        for (Proposta p : propostas) {
            if (p instanceof T2Projeto) {
                arr.add(p);
            }
        }
        return arr;
    }

    public ArrayList<Proposta> getPropostasEmT3() {
        ArrayList<Proposta> arr = new ArrayList<>();
        for (Proposta p : propostas) {
            if (p instanceof T3Autoprop) {
                arr.add(p);
            }
        }
        return arr;
    }

    public ArrayList<Aluno> getAlunosPropPorPreferencia(int ordemPref, String codigoProp) {
        ArrayList<Aluno> arr = new ArrayList<>();
        for (Candidatura c : candidaturas) {
            if (c.getPropostas().get(ordemPref).getCodigo().equals(codigoProp))
                arr.add(c.getAluno());
        }
        return arr;
    }

    public ArrayList<Aluno> getAlunosPreAtribuidos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        for (Proposta p : propostas) {
            if (p.getAlunoAtrib() != null)
                alunos.add(getAlunoByID(p.getAlunoAtrib()));
        }
        return alunos;

    }

    public Aluno getAlunoAtribuidoProp(Proposta p){
        for(Map.Entry<Aluno,Proposta> map : atribuicoes.entrySet()){
            if(map.getValue().equals(p)){
                return map.getKey();
            }
        }
        return null;
    }

    public ArrayList<Aluno> getArrayPref(Proposta p) {
        ArrayList<Aluno> alunos = new ArrayList<>();

        for (Candidatura c : candidaturas) {
            ArrayList<Proposta> prop = new ArrayList<>();
            if (!isAlunoAtribuido(c.getAluno())) {
                for (Proposta r : c.getPropostas()) {
                    if (!isPropostaAtribuida(r)) {
                        prop.add(r);
                    }
                }
                if (prop.get(0).equals(p))
                    alunos.add(c.getAluno());
            }
        }
        return alunos;
    }

    public int getNumPref(Map.Entry<Aluno,Proposta> entry){
        for(Candidatura c : candidaturas){
            if(c.getAluno().getId().equals(entry.getKey().getId()))
                return c.getPropostas().indexOf(entry.getValue());
        }
        return -1;
    }

    public ArrayList<Proposta> getPropostasByCandidatura(Aluno a){
        for(Candidatura c : candidaturas){
            if(c.getAluno().equals(a))
                return c.getPropostas();
        }
        return null;
    }

    public Docente getOrientadorByAtrib(Proposta p) {
        for(Map.Entry<Proposta,Docente> map : atribOrient.entrySet()){
            if(map.getKey().equals(p)){
                return map.getValue();
            }
        }
        return null;
    }

    public boolean isPropInCand(Proposta p) {
        for (Candidatura c : candidaturas) {
            if (c.getPropostas().contains(p))
                return true;
        }
        return false;
    }

    public boolean isPropAtribuidaADocente(Proposta p) {
        return atribOrient.containsKey(p);
    }

    public boolean isDocenteAtribuido(Docente p) {
        return atribOrient.containsValue(p);
    }

    public String showAtribuicoes() {
        StringBuilder sb = new StringBuilder();
        if (atribuicoes.isEmpty()) {
            sb.append("Sem atribuições");
            return sb.toString();
        }
        for (Map.Entry<Aluno, Proposta> entry : atribuicoes.entrySet())
            sb.append("\nAluno = ").append(entry.getKey()).append(", Proposta = ").append(entry.getValue());
        return sb.toString();
    }

    public boolean isAlunoAtribuido(Aluno a) {
        return atribuicoes.containsKey(a);
    }

    public boolean isPropostaAtribuida(Proposta p) {
        return atribuicoes.containsValue(p);
    }

    public boolean isCandidaturasDone() {
        for (Candidatura c : candidaturas) {
            if (!atribuicoes.containsKey(c.getAluno())) return false;
        }
        return true;
    }

    public String getListaAlunosPropostasAtribuidas(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ALUNO]:[PROPOSTA ATRIBUIDA]");
        for(Map.Entry<Aluno,Proposta> entry : atribuicoes.entrySet()){
            sb.append("\n[").append(entry.getKey().getId()).append("]:[").append(entry.getValue().getCodigo()).append("]");
        }
        return sb.toString();
    }

    public String getListaDeEstudantesSemPropAtribECand(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ALUNO]:[PROPOSTAS,...]");
        for(Aluno a : getAlunosNAtribuidos()){
            ArrayList<Proposta> cand = getPropostasByCandidatura(a);
            if(cand!=null) {
                sb.append("\n[").append(a.getId().toString()).append("]:");
                for (Proposta p : getPropostasByCandidatura(a)) {
                    sb.append(p).append(",");
                }
                sb.append("]");
            }
            else sb.append("\nO aluno").append(a.getId()).append(" não apresentou candidatura.");
        }
        return sb.toString();
    }

    public ArrayList<Aluno> getAlunosNAtribuidos(){
        ArrayList<Aluno> al = new ArrayList<>();
        for(Aluno a : alunos){
            if(!atribuicoes.containsKey(a)){
                al.add(a);
            }
        }
        return al;
    }

    public String adicionaOrient(String d, String p) {
        Docente docente = getDocenteByID(d); Proposta prop =getPropostaByID(p);
        if(docente==null) return "O e-mail que indicou não está presente no sistema.";
        if(prop==null) return "O código de proposta que indicou não está presente no sistema.";
        if(isPropAtribuidaADocente(prop)) return "Proposta já associada a um orientador";
        addAtribuicaoOrient(docente,prop);
        return "Docente "+docente.getEmail()+"ficou orientador da proposta "+prop;
    }

    public String consultaOrient() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[Proposta] : [Docente] : [Aluno]");
        for(Map.Entry<Proposta,Docente> map : getOrientadores().entrySet()){
            sb.append("\n[").append(map.getKey()).append("] : [").append(map.getValue()).append("] : [").append(getAlunoAtribuidoProp(map.getKey())).append("]");
        }
        return sb.toString();
    }

    public String getListaPropDisponiveis(){
        StringBuilder sb = new StringBuilder();
        for(Proposta p : propostas){
            if(!atribuicoes.containsValue(p))
                sb.append("\n").append(p.getCodigo());
        }
        return sb.toString();
    }

    public String getListaPropostasAtribuidas(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Aluno, Proposta> p : atribuicoes.entrySet()){
            sb.append("\n").append(p.getValue().getCodigo());
        }
        return sb.toString();
    }

    public ArrayList<Aluno> getAlunosSemPropostaAtribuida(){
        ArrayList<Aluno> alunosaux=new ArrayList<>();

        for(Aluno a : getAlunos()){
            if(!getAlunosComPropostaAtribuidaAsSet().contains(a)){
                alunosaux.add(a);
            }
        }

        return alunosaux;
    }

    public boolean verificaConfig(){
        return getPropostasEmDA().size() >= getAlunosEmDA().size()
                &&  getPropostasEmSI().size() >= getAlunosEmSI().size()
                && getPropostasEmRAS().size() >= getAlunosEmRAS().size();
    }

    /////////////fases
    public boolean isConfigClosed() {
        return isConfigClosed;
    }

    public void setConfigClosed(boolean configClosed) {
        isConfigClosed = configClosed;
    }

    public boolean isOpCandClosed() {
        return isOpCandClosed;
    }

    public void setOpCandClosed(boolean opCandClosed) {
        isOpCandClosed = opCandClosed;
    }

    public boolean isAtribPropClosed() {
        return isAtribPropClosed;
    }

    public void setAtribPropClosed(boolean atribPropClosed) {
        isAtribPropClosed = atribPropClosed;
    }

    public boolean canCloseAtribProp() {
        int count = 0;
        for (Candidatura c : candidaturas) {
            if (atribuicoes.containsKey(c.getAluno()))
                count++;
        }
        return count == candidaturas.size();
    }

    public boolean isAtribOrientClosed() {
        return isAtribOrientClosed;
    }

    public void setAtribOrientClosed(boolean atribOrientClosed) {
        isAtribOrientClosed = atribOrientClosed;
    }

    public String consulta(ListTypes id){
        switch (id){
            case EST_COM_PROP_ATRIB -> {
                return listaEstComPropAtrib();
            }
            case EST_SEM_PROP_ATRIB_E_OP_CAND -> {
                return listaEstSemPropAtribEOpCand();
            }
            case PROP_DISPONIVEIS -> {
                return listaPropDisponiveis();
            }
            case PROP_ATRIBUIDAS -> {
                return listaPropAtribuidas();
            }
            case EXPORTA_CSV -> {return exportaCSVOrientadores();}
            default -> {
                return null;
            }
        }
    }

    public String listaPropAtribuidas() {
        return getListaPropostasAtribuidas();
    }

    public String listaPropDisponiveis() {
        return getListaPropDisponiveis();
    }

    public String listaEstSemPropAtribEOpCand() {
        return getListaDeEstudantesSemPropAtribECand();
    }

    public String listaEstComPropAtrib() {
        return getListaAlunosPropostasAtribuidas();
    }

    public String exportaCSVAlunos() {
        List<List<String>> listaAlunos = new ArrayList<>();

        for(Aluno a : getAlunos()){
            List<String> aluno = new ArrayList<>();
            aluno.add(a.getId().toString());
            aluno.add(a.getNome());
            aluno.add(a.getEmail());
            aluno.add(a.getCurso());
            aluno.add(a.getRamo());
            aluno.add(Double.toString(a.getClassif()));
            aluno.add(Boolean.toString(a.isEstEProj()));
            listaAlunos.add(aluno);
            //  aluno.clear();
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(listaAlunos,"alunos_"+strDate+".csv");
    }

    public String exportaCSVCandidaturas() {
        List<List<String>> listaCand = new ArrayList<>();

        for(Candidatura a : getCandidaturas()){
            List<String> cand = new ArrayList<>();
            cand.add(a.getAluno().getId().toString());
            for(Proposta b : a.getPropostas()){
                cand.add(b.getCodigo());
            }
            listaCand.add(cand);
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(listaCand,"candidaturas_"+strDate+".csv");
    }

    public String exportaCSVDocentes() {
        List<List<String>> listaDocentes = new ArrayList<>();

        for(Docente a : getDocentes()){
            List<String> docente = new ArrayList<>();
            docente.add(a.getNome());
            docente.add(a.getEmail());
            listaDocentes.add(docente);
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(listaDocentes,"docentes_"+strDate+".csv");
    }

    public String editOrientadores(String codigo, String nome, String email){
        Proposta p=getPropostaByID(codigo);
        Docente d=getDocenteByID(nome);

        if(d!=null)
            editDocente(nome, email);
        else
            return "Docente nao existe";

        if(p!=null) {
            for (Proposta po : atribOrient.keySet()) {
                if(atribOrient.containsKey(po)){
                    atribOrient.replace(p, d);
                    return "Orientador editado";
                }
            }
        }
        return "Proposta nao encontrada";
    }

    public String eliminarOrientador(String codigo){
        Proposta p=getPropostaByID(codigo);

        if(p!=null) {
            for (Proposta po : atribOrient.keySet()) {
                if(atribOrient.containsKey(po)){
                    atribOrient.replace(p, null);
                    return "Orientador eliminado";
                }
            }
        }
        return "Proposta nao encontrada";

    }

    public String exportaCSVOrientadores() {
        List<List<String>> lista = new ArrayList<>();
        lista.add(new ArrayList<>(Collections.singleton("aluno ID, proposta atribuida, nºpreferencia, opcoes candidaturas, orientador")));
        for(Map.Entry<Aluno, Proposta> map : getAtribuicoes().entrySet()){
            List<String> row = new ArrayList<>();
            row.add(Long.toString(map.getKey().getId()));
            row.add(map.getValue().getCodigo());
            row.add(Integer.toString(getNumPref(map)));
            if(!isPreProposta(map.getValue())) {
                for (Proposta c : getPropostasByCandidatura(map.getKey()))
                    row.add(c.getCodigo());
            }
            Docente d = getOrientadorByAtrib(map.getValue());
            row.add(d==null? "null":d.getEmail());
            lista.add(row);
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(lista,"orientadores_"+strDate+".csv");
    }

    public String exportaCSVPropostas() {
        List<List<String>> listaPropostas = new ArrayList<>();

        for(Proposta a : getPropostas()){
            List<String> proposta = new ArrayList<>();
            if(a instanceof T1Estagio) {
                proposta.add("T1");
                proposta.add(a.getCodigo());
                StringBuilder ramos = new StringBuilder();
                ArrayList<String> ramosA =((T1Estagio) a).getRamos();
                ramosToString(a, proposta, ramos, ramosA);
                proposta.add(a.getTitulo());
                proposta.add(((T1Estagio) a).getEntidadeAcolh());
                if(a.getAlunoAtrib()!=null) proposta.add(a.getAlunoAtrib().toString());
            }
            if(a instanceof T2Projeto) {
                proposta.add("T2");
                proposta.add(a.getCodigo());
                StringBuilder ramos = new StringBuilder();
                ArrayList<String> ramosA =((T2Projeto) a).getRamos();
                ramosToString(a, proposta, ramos, ramosA);
                proposta.add(a.getTitulo());
                proposta.add(((T2Projeto) a).getDocenteProp());
                if(a.getAlunoAtrib()!=null) proposta.add(a.getAlunoAtrib().toString());
            }
            if(a instanceof T3Autoprop) {
                proposta.add("T3");
                proposta.add(a.getCodigo());
                proposta.add(a.getTitulo());
                proposta.add(((T3Autoprop) a).getAlunoID().toString());
            }
            listaPropostas.add(proposta);
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(listaPropostas,"propostas_"+strDate+".csv");
    }

    public void ramosToString(Proposta a, List<String> proposta, StringBuilder ramos, ArrayList<String> ramosA) {
        for(int i=0;i<ramosA.size();i++){
            if(i==ramosA.size()-1) ramos.append(ramosA.get(i));
            else ramos.append(ramosA.get(i)).append("|");
        }
        proposta.add(ramos.toString());
    }

    public String exportaCSVAtribProposta() {
        List<List<String>> lista = new ArrayList<>();
        for(Map.Entry<Aluno, Proposta> map : getAtribuicoes().entrySet()){
            List<String> row = new ArrayList<>();
            row.add(Long.toString(map.getKey().getId()));
            row.add(map.getValue().getCodigo());
            row.add(Integer.toString(getNumPref(map)));
            for(Proposta c : getPropostasByCandidatura(map.getKey()))
                row.add(c.getCodigo());
            lista.add(row);
        }
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd_hh-mm-ss");
        String strDate = dateFormat.format(date);
        return Support.generateCSV(lista,"atribuicoes_"+strDate+".csv");
    }


    public boolean isPreProposta(Proposta p) {
        return p instanceof T3Autoprop || p.getAlunoAtrib() != null;
    }
}

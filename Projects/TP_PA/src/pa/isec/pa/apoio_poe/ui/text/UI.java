package pa.isec.pa.apoio_poe.ui.text;

import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.ui.utils.ListTypes;
import pa.isec.pa.apoio_poe.ui.utils.PAInput;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class UI {
    ManagerUI managerUI;

    public UI(ManagerUI managerUI) {
        this.managerUI=managerUI;
    }

    public void start() {
        while(switch(managerUI.getState()){
            case FASEINICIO -> inicio();
            case FASECONFIGURACAO -> configuracao();
            case FASEGESTAOALUNOS -> gestaoAlunos();
            case FASEGESTAODOCENTES -> gestaoDocentes();
            case FASEGESTAOPROPOSTAS -> gestaoPropostas(); //passar parametro para saber para onde ir
            case FASEOPCOESCANDIDATURAS -> opcoesCand();
            case FASEGESTAOCANDIDATURAS -> gestaoCand();
            case FASEATRIBUICAOPROPOSTAS -> atribProp();
            case FASELISTASALUNOS -> listasalunos();
            case FASELISTASPROPOSTAS -> listasprop();
            case FASEATRIBUICAOORIENTADORES -> atribOrient();
            case FASEGESTAOORIENTADORES -> gestaoOrient();
            case FASELISTASORIENTADORES -> listasOrient();
            case FASECONSULTA -> consulta();
        }){
            System.out.println("\n========================================");
            System.out.println("Current state: " +managerUI.getState()); // (only for debug)
        }
    }

    private boolean listasprop() { return false;
    }

    private boolean listasalunos() {
        return false;
    }

    private boolean inicio() {
        switch (PAInput.chooseOption("Fase 0 – Inicio","Ultima sessão","fase 1 config")){
            case 1 -> managerUI.load();
            case 2 -> managerUI.next();
            default -> {return false;}
        }
        return true;
    }
    private boolean configuracao(){
        switch (PAInput.chooseOption("Fase 1 – Configuração","Gestão Alunos","Gestão Docentes","Gestão Propostas","Fechar Fase","Fase 2 – Opções de candidatura")){
            case 1 ->  managerUI.goToGestaoAlunos();
            case 2 ->  managerUI.goToGestaoDocentes();
            case 3 ->  managerUI.goToGestaoPropostas();
            case 4 -> {
                System.out.println(managerUI.getRamosInfo());
                System.out.println(managerUI.setConfigClosed(true));
            }
            case 5 -> managerUI.next();
            default -> {return false;}
        }
        return true;
    }
    private boolean gestaoAlunos() {
        switch (PAInput.chooseOption("Gestão de Alunos","Adicionar","Consultar","Editar","Eliminar", "Exportar para CSV", "Adiconar Manualmente","Undo","Redo","Menu Anterior")){
            case 1 -> {
                String csvname = PAInput.readString("Indique nome de CSV:\t", true);
                System.out.println(managerUI.addAlunos(csvname));
                System.out.println(managerUI.getAlunos());
            }
            case 2 -> System.out.println(managerUI.getAlunos());
            case 3 ->  System.out.println(managerUI.editAluno(PAInput.readLong("Insira o numero de aluno: "), PAInput.readString("Insira o nome: ", false),
                    PAInput.readString("Insira o email: ", true), PAInput.readString("Insira o curso: ", true),
                    PAInput.readString("Insira o ramo: ", true), PAInput.readDouble("Insira a classificacao: "), PAInput.readBoolean("Estagio e projeto: ")));
            case 4 -> System.out.println(managerUI.removeAluno(PAInput.readLong("Insira o numero de aluno a remover: ")));
            case 5 -> managerUI.exportaCSV();
            case 6 -> managerUI.addAluno(PAInput.readString("Insira os dados do aluno separados por virgulas: ", false));
            case 7 -> managerUI.undo();
            case 8 -> managerUI.redo();
            case 9 -> managerUI.previous();
            default -> {return false;}
        }
        return true;
    }
    private boolean gestaoDocentes() {
        switch (PAInput.chooseOption("Gestão de Docentes","Adicionar","Consultar","Editar","Eliminar","Adicionar manualmente","Exportar para CSV", "Menu Configuração")){
            case 1 -> {
                String csvname = PAInput.readString("Indique nome de CSV:\t", true);
                System.out.println(managerUI.addDocentes(csvname));
            }
            case 2 -> System.out.println(managerUI.getDocentes());
            case 3 -> managerUI.editDocente(PAInput.readString("Insira o email do docente a editar: ", true),PAInput.readString("Insira o novo nome: ", true));
            case 4 -> managerUI.removeDocente(PAInput.readString("Insira o email do docente a remover: ", true));
            case 5 -> System.out.println(managerUI.addDocente(PAInput.readString("Insira os dados do docente separados por virgulas: ", true)));
            case 6 -> managerUI.exportaCSV();
            case 7 -> managerUI.previous();
            default -> {return false; }
        }
        return true;
    }
    private boolean gestaoPropostas() {
        switch (PAInput.chooseOption("Gestão de Propostas","Adicionar","Consultar","Eliminar", "Adicionar manualmente","Exportar para CSV", "Menu Configuração")){
            case 1 -> {
                String csvname = PAInput.readString("Indique nome de CSV:\t", true);
                System.out.println(managerUI.addPropostas(csvname));
                System.out.println(managerUI.getPropostas());
            }
            case 2 -> {
                System.out.println(managerUI.getPropostas());
                managerUI.previous();
            }
            case 3 -> managerUI.removeProposta(PAInput.readString("Introduza o codigo:",true));
            case 4 -> System.out.println(managerUI.addProposta(PAInput.readString("Insira os dados da proposta separados por virgulas: ", false)));
            case 5 -> managerUI.exportaCSV();
            case 6 ->  managerUI.previous();
            default -> {return false; }
        }
        return true;
    }


    private boolean opcoesCand() {
        switch (PAInput.chooseOption("Fase 2 – Opções de candidatura","Gestão Candidaturas","Listas Alunos","Listas Propostas","Fechar Fase","Regressar à fase anterior","Fase 3 – Atribuição de propostas")){
            case 1 ->  managerUI.goToGestaoCandidaturas();
            case 2 ->{
                //talvez seja preciso verificar se o mesmo aluno não aparece repetido nos arrays
                //System.out.println(fsm.getAlunosComAutoproposta()); //com autoproposta
                //System.out.println(fsm.getAlunosComCandRegistada()); //com candidatura ja registada
                //System.out.println(fsm.getAlunosSemCandRegistada()); //sem candidatura ja registada
                //fsm.voltaOpcoesCand();
                switch (PAInput.chooseOption("Listagem de alunos", "Alunos com autoproposta", "Alunos com candidatura registada", "Alunos sem candidatura registada", "Menu Anterior")){
                    case 1 -> System.out.println(managerUI.getAlunosComAutoproposta());
                    case 2 -> System.out.println(managerUI.getAlunosComCandRegistada());
                    case 3 -> System.out.println(managerUI.getAlunosSemCandRegistada());
                    case 4 -> managerUI.previous();
                }
            }
            case 3 ->{
                //System.out.println(fsm.getAutoPropostas()); //autopropostas
                //System.out.println(fsm.getPropostasDocentes()); //propostas de docentes
                //System.out.println(fsm.getPropostasComCand()); //propostas com candidaturas
                //System.out.println(fsm.getPropostasSemCand()); //propostas sem candidaturas
                System.out.println("Filtros de 1 a 4 separados por virgulas: ");
                System.out.println("1 - Autopropostas");
                System.out.println("2 - Propostas de docentes");
                System.out.println("3 - Propostas com candidatura");
                System.out.println("4 - Propostas sem candidatura");
                filtrosCandidaturas();
                managerUI.previous();
            }
            case 4 -> {
                System.out.println(managerUI.getRamosInfo());
                System.out.println(managerUI.setOpCandClosed(true));
            }
            case 5 -> managerUI.previous();
            case 6 -> managerUI.next();
            default -> {return false;}
        }
        return true;
    }
    private boolean gestaoCand() {
        switch (PAInput.chooseOption("Gestão de Candidaturas","Adicionar","Consultar","Editar","Eliminar",  "Exportar para CSV","Menu Anterior")){
            case 1 -> {
                String csvname = PAInput.readString("Indique nome de CSV:\t", true);
                System.out.println(managerUI.addCandidaturas(csvname));
                System.out.println(managerUI.getCandidaturas());
            }
            case 2 ->  {
                System.out.println(managerUI.getCandidaturas());
                managerUI.previous();
            }
            case 3 -> {
                System.out.println("Apenas na segunda meta!");
            }
            case 4 -> System.out.println(managerUI.removeCandidatura(PAInput.readLong("Insira o numero de aluno para remover a candidatura: ")));
            case 5 -> managerUI.exportaCSV();
            case 6 -> managerUI.previous();
            default -> {return false; }
        }
        return true;
    }


    private boolean atribProp() {
        switch (PAInput.chooseOption("Fase 3 – Atribuição de propostas","Atribuição Automática","Atribuição Manual","Obtenção de Listas","Fechar Fase","Regressar à fase anterior","Fase 4 – Atribuição de orientadores")){
            case 1 -> {
                if(!managerUI.isAtribPropClosed()) {
                    Map<Proposta, ArrayList<Aluno>> result = managerUI.atribPropAutom();
                    if (result != null) {
                        for (Map.Entry<Proposta, ArrayList<Aluno>> e : result.entrySet()) {
                            int option;
                            do {
                                System.out.println("\nEmpate na proposta " + e.getKey());
                                System.out.println();
                                for (int i = 0; i < e.getValue().size(); i++) {
                                    System.out.printf("%3d - %s\n", i + 1, e.getValue().get(i));
                                }

                                System.out.print("\nOption: ");
                                option = PAInput.readInt("\nOption: ");
                            } while (option < 1 || option > e.getValue().size());
                            managerUI.atribPropManual(e.getValue().get(option-1).getId().toString(), e.getKey().getCodigo());
                        }
                    }
                    System.out.println(managerUI.showAtribuicoes());
                }else{
                    System.out.println("Esta fase encontra-se fechada, impossível realizar alterações");
                }
            }
            case 2 ->{
                if(!managerUI.isOpCandClosed()) {
                    System.out.println("É necessário fechar fase anterior primeiro.");
                    break;
                }
                if(!managerUI.isAtribPropClosed()){
                    System.out.println("Indique Número de Aluno:");
                    Scanner sc = new Scanner(System.in);
                    String naluno = sc.nextLine();
                    System.out.println("Indique Número de Aluno:");
                    System.out.println(managerUI.atribPropManual(naluno, sc.nextLine()));
                }else System.out.println("Esta fase encontra-se fechada, impossível realizar alterações");
            }
            case 3 ->{
                switch(PAInput.chooseOption("Mostrar alunos com: ","Autoproposta associada","Candidatura registada","Proposta atribuida","Sem proposta atribuida","sair")){
                    case 1 -> System.out.println(managerUI.getAlunosComAutoproposta());
                    case 2 -> System.out.println(managerUI.getAlunosComCandRegistada());
                    case 3 -> System.out.println(managerUI.getAlunosComPropostaAtribuida());
                    case 4 -> System.out.println(managerUI.getAlunosSemPropostaAtribuida());
                    case 5 -> managerUI.previous(); //cuidado com isto, falta implementar esta função e decidir para onde vai
                }
            }
            case 4 ->  System.out.println(managerUI.setisAtribPropClosed(true));
            case 5 -> managerUI.previous();
            case 6 -> managerUI.next();
            default -> {return false;}
        }
        return true;
    }

    private void filtrosCandidaturas(){
        int n;
        String line;
        String[] vec;
        Scanner sc = new Scanner(System.in);

        line=sc.nextLine();
        vec=line.split(",");

        for(String s : vec){
            try{
                n=Integer.parseInt(s);
                switch(n){
                    case 1 -> System.out.println("Autopropostas: \n"+managerUI.getAutoPropostas()); //autopropostas
                    case 2 -> System.out.println("Propostas de docente: \n"+managerUI.getPropostasDocentes()); //propostas de docentes
                    case 3 -> System.out.println("Propostas com candidaturas: \n"+managerUI.getPropostasComCand()); //propostas com candidaturas
                    case 4 -> System.out.println("Propostas sem candidaturas: \n"+managerUI.getPropostasSemCand()); //propostas sem candidaturas
                    default -> System.out.println("Introduza apenas opcoes validas.");
                }
            }catch(NumberFormatException nfe){
                System.out.format("%s e invalido!\n", s);
            }
        }
    }

    private boolean atribOrient() {
        switch (PAInput.chooseOption("Fase 4 – Atribuição de orientadores","Atribuição Automática","Gestão orientadores", "Obter listas","Fechar Fase","Regressar à fase anterior","Fase 5 – Consulta")){
            case 1 ->System.out.println(managerUI.atribOrientAutom());
            case 2 ->managerUI.goToGestaoOrientadores();
            case 3 ->managerUI.goToListaOrientadores();
            case 4 -> System.out.println(managerUI.setAtribOrientClosed(true));
            case 5 -> managerUI.previous();
            case 6 -> managerUI.next();
            //case 7 -> fsm.atribOrientadores(7);
            default -> {return false;}
        }
        return true;
    }
    private boolean gestaoOrient() {
        switch (PAInput.chooseOption("Gestão de Orientadores","Adicionar","Consultar","Editar","Eliminar", "Exportar para CSV", "Menu Anterior")){
            case 1 -> {
                String nomeDocente = PAInput.readString("\nIndique e-mail do docente:\t", true);
                String codigoProp = PAInput.readString("\nIndique codigo da proposta:\t", true);
                System.out.println(managerUI.adicionaOrient(nomeDocente, codigoProp));
            }
            case 2 -> System.out.println(managerUI.consultaOrient());
            case 3,4 -> System.out.println("Apenas na segunda meta!");
            case 5 -> managerUI.exportaCSV();
            case 6 -> managerUI.previous();
            default -> {return false;}
        }
        return true;
    }

    private boolean listasOrient() {
        switch (PAInput.chooseOption("Listas","Lista de estudantes com proposta atribuída e com orientador associado","lista de estudantes com proposta atribuída mas sem orientador associado","número de orientações por docente, em média, mínimo, máximo, e por docente especificado", "Menu Anterior")){
            case 1 -> managerUI.listasOrient(1);
            case 2 -> managerUI.listasOrient(2);
            case 3 -> managerUI.listasOrient(3);
            case 4 -> managerUI.previous();
            default -> {return false;}
        }
        return true;
    }


    private boolean consulta() {
        switch(PAInput.chooseOption("Fase 5 – Consulta", "Lista de estudantes com propostas atribuídas","Lista de estudantes sem propostas atribuídas e com opções de candidatura","Conjunto de propostas disponíveis","Conjunto de propostas atribuídas","Número de orientações por docente, em média, mínimo, máximo, e por docente especificado","Outros dados","Exportar CSV")){
            case 1 -> System.out.println(managerUI.consulta(ListTypes.EST_COM_PROP_ATRIB));
            case 2 -> System.out.println(managerUI.consulta(ListTypes.EST_SEM_PROP_ATRIB_E_OP_CAND));
            case 3 -> System.out.println(managerUI.consulta(ListTypes.PROP_DISPONIVEIS));
            case 4 -> System.out.println(managerUI.consulta(ListTypes.PROP_ATRIBUIDAS));
            case 5 -> System.out.println(managerUI.consulta(ListTypes.MEDIAS));
            case 6 -> System.out.println(managerUI.consulta(ListTypes.OUTROS_DADOS));
            case 7 -> System.out.println(managerUI.consulta(ListTypes.EXPORTA_CSV));
            default -> {return false;}
        }

        return true;
    }


}

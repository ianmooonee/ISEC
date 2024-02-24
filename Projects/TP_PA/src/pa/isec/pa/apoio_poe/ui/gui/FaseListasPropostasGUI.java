package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Filter;

public class FaseListasPropostasGUI extends AnchorPane {
    ManagerUI managerUI;
    Button back;
    TableView<Proposta> tb ;FilteredList<Proposta> filteredItems;
    ObjectProperty<Predicate<Proposta>> autop, pDoc, pCC, pSC;

    public FaseListasPropostasGUI(ManagerUI managerUI) {
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Fase 2 - Opções de Candidatura");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(71);
        mp.setLayoutY(29);

        Label label = new Label("Listas de Propostas");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        CheckBox autoprop = new CheckBox("Autopropostas de Alunos");
        autoprop.setLayoutX(831);
        autoprop.setLayoutY(17);

        CheckBox propDocentes = new CheckBox("Propostas de Docentes");
        propDocentes.setLayoutX(831);
        propDocentes.setLayoutY(49);

        CheckBox propComCand = new CheckBox("Propostas com Candidaturas");
        propComCand.setLayoutX(831);
        propComCand.setLayoutY(79);

        CheckBox propSemCand = new CheckBox("Propostas Sem Candidaturas");
        propSemCand.setLayoutX(831);
        propSemCand.setLayoutY(107);

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefHeight(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Pesquisa sem resultados."));

        TableColumn<Proposta, String> tipo = new TableColumn<>("Tipo");
        tipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClass().getSimpleName()));

        TableColumn<Proposta, String> codigo = new TableColumn<>("Código");
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Proposta, String> titulo = new TableColumn<>("Título");
        titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Proposta, String> alunoAtrib = new TableColumn<>("Aluno Atrib");
        alunoAtrib.setCellValueFactory(new PropertyValueFactory<>("alunoAtrib"));

        TableColumn<Proposta, String> ramos = new TableColumn<>("Ramo(s)");
        ramos.setCellValueFactory(cell -> {
            if(cell.getValue() instanceof T1Estagio)
                return new SimpleStringProperty(((T1Estagio) cell.getValue()).getRamos().toString());
            if(cell.getValue() instanceof T2Projeto)
                return new SimpleStringProperty(((T2Projeto) cell.getValue()).getRamos().toString());
            else return new SimpleStringProperty(managerUI.getAlunoByID(((T3Autoprop) cell.getValue()).getAlunoID()).getRamo());
        });

        TableColumn<Proposta, String> entidadeAcolh = new TableColumn<>("Entidade Acolhimento");
        entidadeAcolh.setCellValueFactory(cell -> {
            if(cell.getValue() instanceof T1Estagio)
                return new SimpleStringProperty(((T1Estagio) cell.getValue()).getEntidadeAcolh());
            else return new SimpleStringProperty();
        });

        TableColumn<Proposta, String> docenteProp = new TableColumn<>("Docente Proponente");
        docenteProp.setCellValueFactory(
                cell -> {
                    if(cell.getValue() instanceof T2Projeto)
                        return new SimpleStringProperty(((T2Projeto) cell.getValue()).getDocenteProp());
                    else return new SimpleStringProperty();
                }
        );


        tb.getColumns().addAll( tipo, codigo, titulo,ramos, alunoAtrib,entidadeAcolh, docenteProp);


        autop = new SimpleObjectProperty<>();
        pDoc = new SimpleObjectProperty<>();
        pCC = new SimpleObjectProperty<>();
        pSC = new SimpleObjectProperty<>();

        autop.bind(Bindings.createObjectBinding(()->
                prop -> prop.getClass().equals(T3Autoprop.class) && autoprop.isSelected(), autoprop.selectedProperty())
        );
        pDoc.bind(Bindings.createObjectBinding(()->
                prop -> prop.getClass().equals(T2Projeto.class) && propDocentes.isSelected(), propDocentes.selectedProperty())
        );
        pCC.bind(Bindings.createObjectBinding(()->
                prop -> managerUI.getPropostasComCand().contains(prop) && propComCand.isSelected(), propComCand.selectedProperty())
        );
        pSC.bind(Bindings.createObjectBinding(()->
                prop -> managerUI.getPropostasSemCand().contains(prop) && propSemCand.isSelected(), propSemCand.selectedProperty())
        );

        filteredItems = new FilteredList<>(FXCollections.observableArrayList(managerUI.getPropostas()));
        tb.setItems(filteredItems);

        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(
                ()-> autop.get().and(pDoc.get()).and(pCC.get()).and(pSC.get()),
                autop,pDoc,pCC,pSC
        ));



        // updateTable();


        back = new Button("Menú Anterior");
        back.prefHeight(25); back.prefWidth(94);
        back.setLayoutX(72);back.setLayoutY(464);

        this.getChildren().addAll(mp,label,tb,back,autoprop,propDocentes,propComCand,propSemCand);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> {
            update();
        });

        back.setOnAction(event -> {
            managerUI.previous();
        });

    }

    private void update() {
        if (managerUI.getState() != State.FASELISTASPROPOSTAS) {
            this.setVisible(false);

            return;
        }
        filteredItems = new FilteredList<>(FXCollections.observableArrayList(managerUI.getPropostas()));

        tb.setItems(filteredItems);tb.getItems().addAll(filteredItems);
        System.out.println(tb.getItems());
        filteredItems.predicateProperty().bind(Bindings.createObjectBinding(
                ()-> autop.get().and(pDoc.get()).and(pCC.get()).and(pSC.get()),
                autop,pDoc,pCC,pSC
        ));
        // updateTable();
        this.setVisible(true);

    }
   /* private void updateTable(){
        tb.getItems().clear();
        tb.getItems().addAll(managerUI.getPropostas());
    }*/

}

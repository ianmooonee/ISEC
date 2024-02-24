package pa.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Docente;
import pa.isec.pa.apoio_poe.model.data.T2Projeto;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.Optional;
import java.util.stream.Collectors;

public class FaseListasAlunosGUI extends AnchorPane {
    ManagerUI managerUI;
    Button  back;
    TableView<Aluno> tb ;
    ObservableList<Aluno> dataList;

    public FaseListasAlunosGUI(ManagerUI managerUI) {
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

        Label label = new Label("Listas de Alunos");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        Label label2 = new Label("Alunos: ");
        label2.setFont(Font.font("System", FontWeight.NORMAL, 15));
        label2.setLayoutX(732);
        label2.setLayoutY(97);

        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList("com autoproposta", "com candidatura já registada", "sem candidatura já registada"));
        comboBox.setLayoutX(799);
        comboBox.setLayoutY(95);

        dataList = FXCollections.observableArrayList();

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefHeight(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Pesquisa sem resultados."));

        TableColumn<Aluno, Long> id = new TableColumn<>("ID");id.setPrefWidth(70);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Aluno,String> nome = new TableColumn<>("Nome");nome.setPrefWidth(112);
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Aluno,String> email = new TableColumn<>("E-mail");email.setPrefWidth(166);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Aluno,String> curso = new TableColumn<>("Curso");curso.setPrefWidth(84);
        curso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        TableColumn<Aluno,String> ramo = new TableColumn<>("Ramo");ramo.setPrefWidth(90);
        ramo.setCellValueFactory(new PropertyValueFactory<>("ramo"));
        TableColumn<Aluno,Double> classif = new TableColumn<>("Classificação"); classif.setPrefWidth(92);
        classif.setCellValueFactory(new PropertyValueFactory<>("classif"));
        TableColumn<Aluno,Boolean> pe = new TableColumn<>("Possibilidade Estagios");pe.setPrefWidth(166);
        pe.setCellValueFactory(new PropertyValueFactory<>("estEProj"));


        tb.getColumns().addAll(id, nome, email, curso, ramo, classif, pe);
        updateTable();


        FilteredList<Aluno> filteredData = new FilteredList<>(dataList, b -> true);
        comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aluno -> {
                if(newValue == null || newValue.isEmpty())
                    return true;
                switch (comboBox.getValue()) {
                    case "com autoproposta":
                        for (Aluno b : managerUI.getAlunosComAutoproposta())
                            if (b.equals(aluno)) return true;
                        break;
                    case "com candidatura já registada":
                        for (Aluno b : managerUI.getAlunosComCandRegistada())
                            if (b.equals(aluno)) return true;
                        break;
                    case "sem candidatura já registada":
                        for (Aluno b : managerUI.getAlunosSemCandRegistada())
                            if (b.equals(aluno)) return true;
                        break;
                }
                return false;
            });
        });
        SortedList<Aluno> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tb.comparatorProperty());
        tb.setItems(sortedData);


        //addButtonsToTable();
       //

        back = new Button("Menú Anterior");
        back.prefHeight(25); back.prefWidth(94);
        back.setLayoutX(72);back.setLayoutY(464);

        this.getChildren().addAll(mp,label,tb,back,label2,comboBox);
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
        if (managerUI.getState() != State.FASELISTASALUNOS) {
            this.setVisible(false);

            return;
        }
        updateTable();
        this.setVisible(true);

    }
    private void updateTable(){
       dataList.clear();
        dataList.addAll(managerUI.getAlunos());
    }


}



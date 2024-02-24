package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.Optional;

public class FaseGAlunosGUI extends AnchorPane {
    ManagerUI managerUI;
    Button  back, add, exportar, addMan, undo, redo;
    TableView tb ;


    public FaseGAlunosGUI(ManagerUI managerUI) {
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Fase 1 - Configuração");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(71);
        mp.setLayoutY(29);

        Label label = new Label("Gestão Alunos");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefHeight(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Ainda não existem alunos."));

        /*TableColumn<Long, Aluno> id = new TableColumn<>("ID"); id.setMinWidth(10); id.setPrefWidth(70);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<String, Aluno> nome = new TableColumn<>("Nome"); nome.setMinWidth(10); nome.setPrefWidth(112);
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<String, Aluno> email = new TableColumn<>("E-mail"); email.setMinWidth(10); email.setPrefWidth(100);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<String, Aluno> curso = new TableColumn<>("Curso"); curso.setMinWidth(10); curso.setPrefWidth(84);
        curso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        TableColumn<String, Aluno> ramo = new TableColumn<>("Ramo"); ramo.setMinWidth(10);ramo.setPrefWidth(90);
        ramo.setCellValueFactory(new PropertyValueFactory<>("ramo"));
        TableColumn<Double, Aluno> classif = new TableColumn<>("Classificação"); classif.setMinWidth(10); classif.setPrefWidth(92);
        classif.setCellValueFactory(new PropertyValueFactory<>("classif"));
        TableColumn<Boolean, Aluno> pe = new TableColumn<>("Possibilidade Estagios"); pe.setMinWidth(10); pe.setPrefWidth(166);
        pe.setCellValueFactory(new PropertyValueFactory<>("estEProj"));*/
        //TableColumn column = new TableColumn<>(""); column.setMinWidth(2); column.setPrefWidth(239); column.setMaxWidth(5000);

        TableColumn<Long, Aluno> id = new TableColumn<>("ID");id.setPrefWidth(70);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<String, Aluno> nome = new TableColumn<>("Nome");nome.setPrefWidth(112);
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<String, Aluno> email = new TableColumn<>("E-mail");email.setPrefWidth(166);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<String, Aluno> curso = new TableColumn<>("Curso");curso.setPrefWidth(84);
        curso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        TableColumn<String, Aluno> ramo = new TableColumn<>("Ramo");ramo.setPrefWidth(90);
        ramo.setCellValueFactory(new PropertyValueFactory<>("ramo"));
        TableColumn<Double, Aluno> classif = new TableColumn<>("Classificação"); classif.setPrefWidth(92);
        classif.setCellValueFactory(new PropertyValueFactory<>("classif"));
        TableColumn<Boolean, Aluno> pe = new TableColumn<>("Possibilidade Estagios");pe.setPrefWidth(166);
        pe.setCellValueFactory(new PropertyValueFactory<>("estEProj"));


        tb.getColumns().addAll(id, nome, email, curso, ramo, classif, pe);
        addButtonsToTable();
        updateTable();

        back = new Button("Menú Anterior");
        back.prefHeight(25); back.prefWidth(94);
        back.setLayoutX(72);back.setLayoutY(464);

        exportar = new Button("Exportar CSV");
        exportar.setLayoutX(909);exportar.setLayoutY(464);

        add = new Button("Importar csv");
        add.setLayoutX(978);add.setLayoutY(95);
        addMan = new Button("Add Manual");
        addMan.setLayoutX(880);addMan.setLayoutY(95);
        undo = new Button("Undo");
        undo.setLayoutX(800);undo.setLayoutY(95);
        redo = new Button("Redo");
        redo.setLayoutX(750);redo.setLayoutY(95);


        this.getChildren().addAll(mp, label, tb, back, add, addMan, undo, redo, exportar);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> {
            update();
        });

        undo.setOnAction(event -> managerUI.undo());

        redo.setOnAction(event -> managerUI.redo());

        add.setOnAction(event -> {
            //Creating a dialog
            if(!managerUI.isConfigClosed()){
                Dialog<String> dialog = new Dialog<>();

                dialog.setDialogPane(new addCSVPane<>(managerUI, Aluno.class));

                dialog.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inserção Interdita");
                alert.setHeaderText("Impossível adicionar mais alunos.");
                alert.setContentText("A fase de configuração já está fechada.");

                alert.showAndWait();
            }
        });

        addMan.setOnAction(event -> {
            if(!managerUI.isConfigClosed()){
                Dialog<String> dialog = new Dialog<>();

                dialog.setDialogPane(new addAlunoMan(managerUI));

                dialog.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inserção Interdita");
                alert.setHeaderText("Impossível adicionar mais alunos.");
                alert.setContentText("A fase de configuração já está fechada.");

                alert.showAndWait();
            }
        });

        back.setOnAction(event -> {
            managerUI.previous();
        });

        exportar.setOnAction(event ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação gravada com sucesso.");
            alert.setHeaderText(null);
            alert.setContentText(managerUI.exportaCSV());

            alert.showAndWait();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASEGESTAOALUNOS) {
            this.setVisible(false);

            return;
        }
        updateTable();
        this.setVisible(true);

    }
    private void updateTable(){
        tb.getItems().clear();
        for (Aluno emp : managerUI.getAlunos()){
            tb.getItems().add(emp);
        }
    }

    private void addButtonsToTable() {
        TableColumn<Aluno, Void> colBtn = new TableColumn<>("Edit");

        Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Aluno, Void> call(final TableColumn<Aluno, Void> param) {
                return new TableCell<>() {

                    private final Button edit = new Button("Editar");

                    {
                        edit.setOnAction((ActionEvent event) -> {
                            Aluno data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Dialog<String> dialog = new Dialog<>();

                            dialog.setDialogPane(new editAlunoPane(managerUI,data));

                            dialog.showAndWait();
                        });
                    }

                    private final Button delete = new Button("Eliminar");
                    {
                        delete.setOnAction((ActionEvent event) -> {
                            Aluno data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Certificação");
                            alert.setHeaderText("Tem a certeza que pretende remover " + data.getId());
                            alert.setContentText(data.toString());
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                System.out.println("Remover aluno " + data.getId());
                                System.out.println(managerUI.removeAluno(data.getId()));
                            } else {
                                alert.close();
                            }
                        });
                    }

                    HBox pane = new HBox(edit, delete);
                    {
                        HBox.setMargin(pane, new Insets(0, 0, 0, 50));
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        };
        colBtn.setPrefWidth(166);
        colBtn.setCellFactory(cellFactory);

        tb.getColumns().add(colBtn);

    }
}

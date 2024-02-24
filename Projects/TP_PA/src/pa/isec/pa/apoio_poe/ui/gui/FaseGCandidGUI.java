package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
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
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.data.T2Projeto;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class FaseGCandidGUI extends AnchorPane {
    ManagerUI managerUI;
    Button  back, add, exportar;
    TableView tb ;


    public FaseGCandidGUI(ManagerUI managerUI) {
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

        Label label = new Label("Gestão Candidaturas");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefHeight(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Ainda não existem candidaturas."));

        TableColumn<Candidatura,Long> candidatura = new TableColumn<>("Aluno");candidatura.setPrefWidth(100);
        candidatura.setCellValueFactory(f -> new ReadOnlyObjectWrapper(f.getValue().getAluno().getId()));
        TableColumn<Candidatura,String> prop = new TableColumn<>("Propostas");prop.setPrefWidth(112);
        prop.setCellValueFactory(f -> new ReadOnlyObjectWrapper(f.getValue().getPropostas().stream().map(Proposta::getCodigo).collect(Collectors.toList())));


        tb.getColumns().addAll(candidatura, prop);
        addButtonsToTable();
        updateTable();

        back = new Button("Menú Anterior");
        back.prefHeight(25); back.prefWidth(94);
        back.setLayoutX(72);back.setLayoutY(464);

        exportar = new Button("Exportar CSV");
        exportar.setLayoutX(909);exportar.setLayoutY(464);

        add = new Button("Importar csv");
        add.setLayoutX(978);add.setLayoutY(95);

        this.getChildren().addAll(mp,label,tb,back,add, exportar);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> {
            update();
        });

        add.setOnAction(event -> {
            //Creating a dialog
            if(!managerUI.isOpCandClosed()){
                Dialog<String> dialog = new Dialog<>();

                dialog.setDialogPane(new addCSVPane<>(managerUI, Candidatura.class));

                dialog.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inserção Interdita");
                alert.setHeaderText("Impossível adicionar mais candidaturas.");
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
        if (managerUI.getState() != State.FASEGESTAOCANDIDATURAS) {
            this.setVisible(false);

            return;
        }
        updateTable();
        this.setVisible(true);

    }
    private void updateTable(){
        tb.getItems().clear();
        for (Candidatura emp : managerUI.getCandidaturas()){
            tb.getItems().add(emp);
        }
    }

    private void addButtonsToTable() {
        TableColumn<Candidatura, Void> colBtn = new TableColumn<>("Edit");

        Callback<TableColumn<Candidatura, Void>, TableCell<Candidatura, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Candidatura, Void> call(final TableColumn<Candidatura, Void> param) {
                return new TableCell<>() {

                    private final Button edit = new Button("Editar");

                    {
                        edit.setOnAction((ActionEvent event) -> {
                            Candidatura data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Dialog<String> dialog = new Dialog<>();

                            dialog.setDialogPane(new editCandidaturaPane(managerUI,data));

                            dialog.showAndWait();
                        });
                    }

                    private final Button delete = new Button("Eliminar");

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            Candidatura data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Certificação");
                            alert.setHeaderText("Tem a certeza que pretende remover " + data.toString());
                            alert.setContentText(data.toString());
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                System.out.println("Remover candidatura " + data.toString());
                                System.out.println(managerUI.removeCandidatura(data.getAluno().getId()));
                            } else {
                                alert.close();
                            }
                            //alert.showAndWait();
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

        tb.getColumns().add((TableColumn)colBtn);

    }
}

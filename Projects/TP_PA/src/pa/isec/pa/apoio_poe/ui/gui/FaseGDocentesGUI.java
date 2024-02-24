package pa.isec.pa.apoio_poe.ui.gui;

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
import pa.isec.pa.apoio_poe.model.data.Docente;
import pa.isec.pa.apoio_poe.model.data.Docente;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.Optional;

public class FaseGDocentesGUI extends AnchorPane {
    ManagerUI managerUI;
    Button  back, add, addMan, exportar;
    TableView tb ;

    public FaseGDocentesGUI(ManagerUI managerUI) {
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

        Label label = new Label("Gestão Docentes");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefWidth(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Ainda não existem docentes."));

        TableColumn<String, Docente> nome = new TableColumn<>("Nome");nome.setPrefWidth(112);
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<String, Docente> email = new TableColumn<>("E-mail");email.setPrefWidth(166);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tb.getColumns().addAll( nome, email);
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

        this.getChildren().addAll(mp,label,tb,back,add,addMan, exportar);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> {
            update();
        });

        add.setOnAction(event -> {
            //Creating a dialog
            if(!managerUI.isConfigClosed()) {
                Dialog<String> dialog = new Dialog<String>();

                dialog.setDialogPane(new addCSVPane<>(managerUI, Docente.class));

                dialog.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inserção Interdita");
                alert.setHeaderText("Impossível adicionar mais docentes.");
                alert.setContentText("A fase de configuração já está fechada.");

                alert.showAndWait();
            }

        });

        addMan.setOnAction(event -> {
            if(!managerUI.isConfigClosed()){
                Dialog<String> dialog = new Dialog<>();

                dialog.setDialogPane(new addDocenteMan(managerUI));

                dialog.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inserção Interdita");
                alert.setHeaderText("Impossível adicionar mais docentes.");
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
        if (managerUI.getState() != State.FASEGESTAODOCENTES) {
            this.setVisible(false);

            return;
        }
        updateTable();
        this.setVisible(true);

    }
    private void updateTable(){
        tb.getItems().clear();
        for (Docente emp : managerUI.getDocentes()){
            tb.getItems().add(emp);
        }
    }

    private void addButtonsToTable() {
        TableColumn<Docente, Void> colBtn = new TableColumn<>("Edit");

        Callback<TableColumn<Docente, Void>, TableCell<Docente, Void>> cellFactory = new Callback<TableColumn<Docente, Void>, TableCell<Docente, Void>>() {
            @Override
            public TableCell<Docente, Void> call(final TableColumn<Docente, Void> param) {
                final TableCell<Docente, Void> cell = new TableCell<Docente, Void>() {

                    private final Button edit = new Button("Editar");

                    {
                        edit.setOnAction((ActionEvent event) -> {
                            Docente data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Dialog<String> dialog = new Dialog<String>();

                            dialog.setDialogPane(new editDocentePane(managerUI,data));

                            dialog.showAndWait();
                        });
                    }

                    private final Button delete = new Button("Eliminar");

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            Docente data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Certificação");
                            alert.setHeaderText("Tem a certeza que pretende remover " + data.getEmail());
                            alert.setContentText(data.toString());
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                System.out.println("Remover docente " + data.getEmail());
                                System.out.println(managerUI.removeDocente(data.getEmail()));
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
                return cell;
            }
        };
        colBtn.setPrefWidth(166);
        colBtn.setCellFactory(cellFactory);

        tb.getColumns().add((TableColumn)colBtn);

    }
}

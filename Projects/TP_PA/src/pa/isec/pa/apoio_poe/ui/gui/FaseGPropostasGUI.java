package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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
import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.Optional;

public class FaseGPropostasGUI extends AnchorPane {
    ManagerUI managerUI;
    Button  back, add, addMan, exportar;
    TableView tb;

    public FaseGPropostasGUI(ManagerUI managerUI) {
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

        Label label = new Label("Gestão Propostas");
        label.setFont(Font.font("System", FontWeight.BLACK, 27));
        label.setLayoutX(431);
        label.setLayoutY(68);

        tb = new TableView<>();
        tb.prefWidth(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Ainda não existem propostas."));

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

        this.getChildren().addAll(mp,label,tb,back,add,addMan,exportar);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> {
            update();
        });

        add.setOnAction(event -> {
            //Creating a dialog
                Dialog<String> dialog = new Dialog<String>();

                dialog.setDialogPane(new addCSVPane<>(managerUI, Proposta.class));

                dialog.showAndWait();
        });

        addMan.setOnAction(event -> {
                Dialog<String> dialog = new Dialog<>();

                dialog.setDialogPane(new addPropManual(managerUI));

                dialog.showAndWait();
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
        if (managerUI.getState() != State.FASEGESTAOPROPOSTAS) {
            this.setVisible(false);

            return;
        }
        updateTable();
        this.setVisible(true);

    }
    private void updateTable(){
        tb.getItems().clear();
        for (Proposta emp : managerUI.getPropostas()){
            tb.getItems().add(emp);
        }
    }

    private void addButtonsToTable() {
        TableColumn<Proposta, Void> colBtn = new TableColumn<>("Edit");

        Callback<TableColumn<Proposta, Void>, TableCell<Proposta, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Proposta, Void> call(final TableColumn<Proposta, Void> param) {
                return new TableCell<Proposta, Void>() {

                    private final Button edit = new Button("Editar");

                    {
                        edit.setOnAction((ActionEvent event) -> {
                            Proposta data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Dialog<String> dialog = new Dialog<String>();

                            dialog.setDialogPane(new editPropostaPane(managerUI,data));

                            dialog.showAndWait();
                        });
                    }

                    private final Button delete = new Button("Eliminar");

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            Proposta data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Certificação");
                            alert.setHeaderText("Tem a certeza que pretende remover " + data.getCodigo());
                            alert.setContentText(data.toString());
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                System.out.println("Remover proposta " + data.getCodigo());
                                System.out.println(managerUI.removeProposta(data.getCodigo()));
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

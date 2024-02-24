package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.data.T1Estagio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class editCandidaturaPane extends DialogPane {
    ManagerUI managerUI;
    Button  add;
    Button cancel;
    Candidatura a;
    TableView tb ; Integer count = 0;

    public editCandidaturaPane(ManagerUI managerUI, Candidatura a){
        this.managerUI= managerUI;
        this.a = a;
        createViews();
        registerHandlers();
    }

    private void createViews() {
        this.prefWidth(787); this.prefWidth(521);

        AnchorPane ap = new AnchorPane();
        Label l = new Label("Editar: ");
        l.setFont(Font.font("System", FontWeight.BLACK, 26));
        l.setLayoutX(48); l.setLayoutY(39);

        Label candidaturaLabel = new Label("Candidatura do aluno " + a.getAluno().getId());
        candidaturaLabel.setFont(Font.font("System",  26));
        candidaturaLabel.setLayoutX(158); candidaturaLabel.setLayoutY(39);


        tb = new TableView<>();
        tb.setLayoutX(289); tb.setLayoutY(168);
        tb.setPlaceholder(new Label("Ainda não existem candidaturas."));

        List numbers = Arrays.asList(1,2,3,4,5,6);
        TableColumn<Integer, String> pref = new TableColumn<>("Preferência");
        pref.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().toString()));
        TableColumn<Proposta, String> prop = new TableColumn<>("Propostas");
        /*prop.setCellFactory(new Callback<TableColumn<Candidatura, String>, TableCell<Candidatura, String>>() {
            @Override
            public TableCell<Candidatura, String> call(TableColumn<Candidatura, String> stringIntegerTableColumn) {
                return new TableCell<>() {

                    @Override
                    protected void updateItem(Candidatura item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(item.getPropostas().get(this.getIndex()).toString());
                            count+=1;
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });*/
        prop.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getCodigo()));
       tb.getColumns().addAll(pref, prop);


        updateTable();

        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(439);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        add = new Button("Guardar");
        add.setLayoutX(680); add.setLayoutY(437);

        ap.getChildren().addAll(tb,l,candidaturaLabel,cancel,add);

        //this.getButtonTypes().add(ButtonType.CLOSE);
        this.setContent(ap);

    }
    private void registerHandlers() {

        add.setOnAction(event -> {
            System.out.println("Candidatura editado ");
        });

        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

    }

    private void updateTable(){
        tb.getItems().clear();

        for (Proposta emp : managerUI.getPropostasByCandidatura(a.getAluno())){
            tb.getItems().add(emp);
        }
    }

}
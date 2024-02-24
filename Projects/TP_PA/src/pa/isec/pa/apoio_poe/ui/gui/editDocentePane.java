package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Docente;

public class editDocentePane extends DialogPane {
    ManagerUI managerUI;
    Button add;
    TextField tfnome;
    Button cancel;
    Docente a;

    public editDocentePane(ManagerUI managerUI, Docente a){
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

        Label docenteLabel = new Label("Docente " + a.getEmail());
        docenteLabel.setFont(Font.font("System",  26));
        docenteLabel.setLayoutX(158); docenteLabel.setLayoutY(39);

        Label nomeL = new Label("Nome:");
        nomeL.setFont(Font.font("System",  20));
        nomeL.setLayoutX(64); nomeL.setLayoutY(166);

        tfnome = new TextField();
        tfnome.setLayoutX(289); tfnome.setLayoutY(168);
        tfnome.setPromptText(a.getNome());


        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(439);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        add = new Button("Guardar");
        add.setLayoutX(680); add.setLayoutY(437);

        ap.getChildren().addAll(l,docenteLabel,nomeL, tfnome,cancel,add);

        //this.getButtonTypes().add(ButtonType.CLOSE);
        this.setContent(ap);

    }
    private void registerHandlers() {

        add.setOnAction(event -> {
            System.out.println("Docente editado ");
            System.out.println(managerUI.editDocente(tfnome.getText().isEmpty()?a.getNome():tfnome.getText(),a.getEmail() ));
            this.getScene().getWindow().hide();
        });

        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

    }

}
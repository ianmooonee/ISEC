package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Candidatura;
import pa.isec.pa.apoio_poe.model.data.Docente;
import pa.isec.pa.apoio_poe.model.data.Proposta;

import java.io.File;
import java.nio.file.Paths;

public class addCSVPane<T> extends DialogPane {
    ManagerUI managerUI;
    Button browse , add;
    FileChooser fileChooser;
    TextField tf;
    Button cancel;
    File selectedFile;
    Class<T> c;
    public addCSVPane(ManagerUI managerUI,Class<T> c){
        this.managerUI= managerUI;
        createViews();
        registerHandlers();
        this.c = c;
    }

    private void createViews() {
        this.prefWidth(678); this.prefWidth(216);
        fileChooser = new FileChooser();
        AnchorPane ap = new AnchorPane();
        Label l = new Label("Localização CSV:");
        l.setFont(Font.font("System", FontWeight.BLACK, 18));
        l.setLayoutX(65); l.setLayoutY(59);
        cancel = new Button("Cancelar");
        cancel.setLayoutX(65); cancel.setLayoutY(134);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);
        add = new Button("Adicionar Informação");
        add.setLayoutX(522); add.setLayoutY(134);
        tf = new TextField();
        tf.setPromptText("Localização");
        tf.setLayoutX(217); tf.setLayoutY(60);
        browse = new Button("Browse");
        browse.setLayoutX(374); browse.setLayoutY(60);
        browse.setUnderline(true); browse.setTextFill(Color.BLUE);
        browse.setStyle("-fx-background-color:  transparent;");
        ap.getChildren().addAll(l,cancel,add,tf,browse);

        //this.getButtonTypes().add(ButtonType.CLOSE);
        this.setContent(ap);

    }
    private void registerHandlers() {
        browse.setOnAction(event -> {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files","*.csv"));
            fileChooser.setInitialDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));
            selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            System.out.println(selectedFile.getAbsolutePath());
            tf.setText("\"" + selectedFile.getAbsolutePath()+"\"");
        });

        add.setOnAction(event -> {
            if(!tf.getText().isEmpty()){
                System.out.println(selectedFile.getAbsolutePath());
                String errors ;
                if (Aluno.class.equals(c)) {
                    errors = managerUI.addAlunos(selectedFile.getAbsolutePath());
                } else if (Docente.class.equals(c)) {
                    errors = managerUI.addDocentes(selectedFile.getAbsolutePath());
                } else if (Proposta.class.equals(c)) {
                    errors = managerUI.addPropostas(selectedFile.getAbsolutePath());
                }  else
                    errors = managerUI.addCandidaturas(selectedFile.getAbsolutePath());

                if(!errors.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erros de inserção");
                    alert.setHeaderText("Erros de inserção");
                    alert.setContentText("Existem os seguintes problemas no ficheiro CSV:");

                    TextArea textArea = new TextArea(errors);
                    textArea.setEditable(false);
                    textArea.setWrapText(true);

                    textArea.setMaxWidth(Double.MAX_VALUE);
                    textArea.setMaxHeight(Double.MAX_VALUE);
                    GridPane.setVgrow(textArea, Priority.ALWAYS);
                    GridPane.setHgrow(textArea, Priority.ALWAYS);

                    GridPane expContent = new GridPane();
                    expContent.setMaxWidth(Double.MAX_VALUE);
                    expContent.add(textArea, 0, 1);

                    // Set expandable Exception into the dialog pane.
                    alert.getDialogPane().setExpandableContent(expContent);
                    alert.getDialogPane().setExpanded(true);
                    alert.showAndWait();
                    alert.close();
                }

                this.getScene().getWindow().hide();
                if (Aluno.class.equals(c)) {
                    System.out.println(managerUI.getAlunos());
                } else if (Docente.class.equals(c)) {
                    System.out.println(managerUI.getDocentes());
                } else if (Proposta.class.equals(c)) {
                    System.out.println(managerUI.getPropostas());
                } else if (Candidatura.class.equals(c)) {
                    System.out.println(managerUI.getCandidaturas());
                }  else {
                    throw new IllegalStateException("Unexpected value: " + c);
                }
            }
        });

        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

    }

}

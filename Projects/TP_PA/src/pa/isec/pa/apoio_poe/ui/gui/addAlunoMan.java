package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;

public class addAlunoMan extends DialogPane {
    ManagerUI managerUI;
    Button cancel, guardar;
    TextField id,nome,email, classif;
    RadioButton estProj;
    ComboBox<String> ramoC,cursoC;

    public addAlunoMan(ManagerUI managerUI){
        this.managerUI= managerUI;
        createViews();
        registerHandlers();
    }

    private void createViews() {
        AnchorPane ap = new AnchorPane();
        Label l = new Label("Adicionar: ");
        l.setFont(Font.font("System", FontWeight.BLACK, 26));
        l.setLayoutX(48); l.setLayoutY(39);

        int Y = 166;

        Label idL = new Label("ID:");
        idL.setFont(Font.font("System",  20));
        idL.setLayoutX(64); idL.setLayoutY(Y);
        id = new TextField();
        id.setLayoutX(289); id.setLayoutY(Y); Y+=70;
        id.setPromptText("123456789");

        Label nomeL = new Label("Nome:");
        nomeL.setFont(Font.font("System",  20));
        nomeL.setLayoutX(64); nomeL.setLayoutY(Y);
        nome = new TextField();
        nome.setLayoutX(289); nome.setLayoutY(Y); Y+=70;
        nome.setPromptText("Abilio");

        Label emailL = new Label("E-Mail:");
        emailL.setFont(Font.font("System",  20));
        emailL.setLayoutX(64); emailL.setLayoutY(Y);
        email = new TextField();
        email.setLayoutX(289); email.setLayoutY(Y); Y+=70;
        email.setPromptText("teste@asd.pt");

        Label cursoL = new Label("Curso:");
        cursoL.setFont(Font.font("System",  20));
        cursoL.setLayoutX(64); cursoL.setLayoutY(Y);
        cursoC = new ComboBox<>();
        cursoC.getItems().add("LEI");
        cursoC.getItems().add("LEI-PL");
        cursoC.setValue("LEI");
        cursoC.setLayoutX(289); cursoC.setLayoutY(Y); Y+=70;

        Label ramoL = new Label("Ramo:");
        ramoL.setFont(Font.font("System",  20));
        ramoL.setLayoutX(64); ramoL.setLayoutY(Y);

        ramoC = new ComboBox<>();
        ramoC.getItems().add("SI");
        ramoC.getItems().add("DA");
        ramoC.getItems().add("RAS");
        ramoC.setValue("RAS");
        ramoC.setLayoutX(289); ramoC.setLayoutY(Y); Y+=70;

        Label peL = new Label("Possibilidade Estágios:");
        peL.setFont(Font.font("System",  20));
        peL.setLayoutX(68); peL.setLayoutY(Y);

        estProj = new RadioButton();
        estProj.setLayoutX(289); estProj.setLayoutY(Y); Y+=70;

        Label classifL = new Label("Classificação:");
        classifL.setFont(Font.font("System",  20));
        classifL.setLayoutX(64); classifL.setLayoutY(Y);

        classif = new TextField();
        classif.setLayoutX(289); classif.setLayoutY(Y); Y+=70;
        classif.setPromptText("0");

        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(Y);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        guardar = new Button("Guardar");
        guardar.setLayoutX(450); guardar.setLayoutY(Y);

        ap.getChildren().addAll(l,idL,id,nomeL, nome,emailL,cursoL,cursoC,email,ramoL,ramoC,classifL,classif,peL,estProj,cancel,guardar);
        this.setContent(ap);
    }

    private void registerHandlers() {
        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

        guardar.setOnAction(event -> {
            String errors;
            errors=managerUI.addAluno((id.getText().isEmpty() ? id.getPromptText() : id.getText()) + "," +
                            (nome.getText().isEmpty() ? nome.getPromptText() : nome.getText()) + ","+
                            (email.getText().isEmpty() ? email.getPromptText() : email.getText()) + ","+
                            (cursoC.getValue()) + "," +
                            (ramoC.getValue()) + ","+
                            (classif.getText().isEmpty() ? classif.getPromptText() : classif.getText()) + "," +
                            (estProj.isSelected()) + ","
                                                    );
            if(!errors.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erros de inserção");
                alert.setHeaderText("Erros de inserção");

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
        });
    }
}

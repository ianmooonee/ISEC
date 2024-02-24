package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;

public class addPropManual extends DialogPane {
    ManagerUI managerUI;
    Button cancel, guardar;
    TextField codigo, titulo, alunoAtrib; //Proposta
    TextField entidadeAcolh, ramosT1; //T1
    TextField docenteProp, ramosT2; //T2
    TextField alunoID; //T3

    public addPropManual(ManagerUI managerUI){
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

        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(Y);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        guardar = new Button("Guardar");
        guardar.setLayoutX(450); guardar.setLayoutY(Y);

        ap.getChildren().addAll(l,cancel);
        this.setContent(ap);
    }

    private void registerHandlers() {
        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

        /*guardar.setOnAction(event -> {
            String errors;
            errors=managerUI.addProposta();
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
        });*/
    }
}


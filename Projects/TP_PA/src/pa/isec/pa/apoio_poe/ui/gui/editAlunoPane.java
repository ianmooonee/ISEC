package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;

import java.io.File;
import java.nio.file.Paths;

public class editAlunoPane extends DialogPane {
    ManagerUI managerUI;
    Button  add;
    TextField classifTf,tfnome,tfemail;
    Button cancel;
    Aluno a;
    RadioButton rb;ComboBox<String> ramoC,cursoC;

    public editAlunoPane(ManagerUI managerUI, Aluno a){
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

        Label alunoLabel = new Label("Aluno " + a.getId());
        alunoLabel.setFont(Font.font("System",  26));
        alunoLabel.setLayoutX(158); alunoLabel.setLayoutY(39);

        int Y = 166;

        Label nomeL = new Label("Nome:");
        nomeL.setFont(Font.font("System",  20));
        nomeL.setLayoutX(64); nomeL.setLayoutY(Y);

        tfnome = new TextField();
        tfnome.setLayoutX(289); tfnome.setLayoutY(Y); Y+=70;
        tfnome.setPromptText(a.getNome());

        Label emailL = new Label("E-Mail:");
        emailL.setFont(Font.font("System",  20));
        emailL.setLayoutX(64); emailL.setLayoutY(Y);

        tfemail = new TextField();
        tfemail.setLayoutX(289); tfemail.setLayoutY(Y); Y+=70;
        tfemail.setPromptText(a.getEmail());

        Label cursoL = new Label("Curso:");
        cursoL.setFont(Font.font("System",  20));
        cursoL.setLayoutX(64); cursoL.setLayoutY(Y);

        cursoC = new ComboBox<>();
        cursoC.getItems().add("LEI");
        cursoC.getItems().add("LEI-PL");
        cursoC.setValue(a.getCurso());
        cursoC.setLayoutX(289); cursoC.setLayoutY(Y); Y+=70;

        Label ramoL = new Label("Ramo:");
        ramoL.setFont(Font.font("System",  20));
        ramoL.setLayoutX(64); ramoL.setLayoutY(Y);

        ramoC = new ComboBox<>();
        ramoC.getItems().add("SI");
        ramoC.getItems().add("DA");
        ramoC.getItems().add("RAS");
        ramoC.setValue(a.getRamo());
        ramoC.setLayoutX(289); ramoC.setLayoutY(Y); Y+=70;

        Label classifL = new Label("Classificação:");
        classifL.setFont(Font.font("System",  20));
        classifL.setLayoutX(64); classifL.setLayoutY(Y);

        classifTf = new TextField();
        classifTf.setLayoutX(289); classifTf.setLayoutY(Y); Y+=70;
        classifTf.setPromptText(a.getEmail());

        Label peL = new Label("Possibilidade Estágios:");
        peL.setFont(Font.font("System",  20));
        peL.setLayoutX(68); peL.setLayoutY(Y);

        rb = new RadioButton();
        rb.setSelected(a.isEstEProj());
        rb.setLayoutX(289); rb.setLayoutY(Y);

        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(439);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        add = new Button("Guardar");
        add.setLayoutX(680); add.setLayoutY(437);

        ap.getChildren().addAll(l,alunoLabel,nomeL, tfnome,emailL,cursoL,cursoC,tfemail,ramoL,ramoC,classifL,classifTf,peL,rb,cancel,add);

        //this.getButtonTypes().add(ButtonType.CLOSE);
        this.setContent(ap);

    }
    private void registerHandlers() {


        add.setOnAction(event -> {
            System.out.println("Aluno editado ");
            System.out.println(managerUI.editAluno(a.getId(),
                    tfnome.getText().isEmpty() ?   a.getNome() :  tfnome.getText(),
                    tfemail.getText().isEmpty() ?   a.getEmail() :  tfemail.getText(),
                    cursoC.getValue().isEmpty() ?   a.getCurso() :  cursoC.getValue(),
                    ramoC.getValue().isEmpty() ?   a.getRamo() :  ramoC.getValue(),
                    classifTf.getText().isEmpty() ? a.getClassif() : Double.parseDouble(classifTf.getText()),
                    rb.isSelected() ));
            this.getScene().getWindow().hide();
        });

        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

    }

}
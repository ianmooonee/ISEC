package pa.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.*;
import pa.isec.pa.apoio_poe.model.data.Proposta;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class editPropostaPane extends DialogPane {
    ManagerUI managerUI;
    Button  add;
    TextField tftitulo;
    Button cancel;
    Proposta a;
    Class<?> type;ComboBox<Long> calunoAtrib;ComboBox<String> cdocenteProp;
    TextField tfentidadeAcolhimento;

    public editPropostaPane(ManagerUI managerUI, Proposta a){
        this.managerUI= managerUI;
        this.a = a;
        this.type = a.getClass();
        createViews();
        registerHandlers();
    }

    private void createViews() {
        this.prefWidth(787); this.prefWidth(521);
        int Y = 116;

        AnchorPane ap = new AnchorPane();
        Label l = new Label("Editar: ");
        l.setFont(Font.font("System", FontWeight.BLACK, 26));
        l.setLayoutX(48); l.setLayoutY(39);

        Label propostaLabel = new Label("Proposta " + a.getCodigo());
        propostaLabel.setFont(Font.font("System",  26));
        propostaLabel.setLayoutX(158); propostaLabel.setLayoutY(39);

        Label tituloL = new Label("Titulo:");
        tituloL.setFont(Font.font("System",  20));
        tituloL.setLayoutX(64); tituloL.setLayoutY(Y);

        tftitulo = new TextField();
        tftitulo.setLayoutX(295); tftitulo.setLayoutY(Y);Y+=50;
        tftitulo.setPromptText(a.getTitulo());

        Label alunoAtrib = new Label("Aluno Atribuído:");
        alunoAtrib.setFont(Font.font("System",  20));
        alunoAtrib.setLayoutX(64); alunoAtrib.setLayoutY(Y);

        calunoAtrib = new ComboBox<>(FXCollections.observableArrayList(managerUI.getAlunosSemPropostaAtribuida().stream().map(Aluno::getId).collect(Collectors.toList())));
        calunoAtrib.setValue(type.equals(T3Autoprop.class)? ((T3Autoprop)a).getAlunoID():a.getAlunoAtrib());
        calunoAtrib.setLayoutX(295); calunoAtrib.setLayoutY(Y);Y+=50;

        ap.getChildren().addAll(l,propostaLabel,tituloL, tftitulo,alunoAtrib,calunoAtrib);


        if(type.equals(T1Estagio.class)){
            Label entidadeAcolhimento = new Label("Entidade De Acolhimento:");
            entidadeAcolhimento.setFont(Font.font("System",  20));
            entidadeAcolhimento.setLayoutX(64); entidadeAcolhimento.setLayoutY(Y);

            tfentidadeAcolhimento = new TextField();
            tfentidadeAcolhimento.setLayoutX(295); tfentidadeAcolhimento.setLayoutY(Y); Y+=50;
            tfentidadeAcolhimento.setPromptText(((T1Estagio) a).getEntidadeAcolh());

            ap.getChildren().addAll(entidadeAcolhimento,tfentidadeAcolhimento);

        }

        if(type.equals(T2Projeto.class)){
            Label docenteProp = new Label("Docente Proponente:");
            docenteProp.setFont(Font.font("System",  20));
            docenteProp.setLayoutX(64); docenteProp.setLayoutY(Y);

            cdocenteProp= new ComboBox<>(FXCollections.observableArrayList(managerUI.getDocentes().stream().map(Docente::getEmail).collect(Collectors.toList())));
            cdocenteProp.setValue(((T2Projeto)a).getDocenteProp());
            cdocenteProp.setLayoutX(295); cdocenteProp.setLayoutY(Y); Y+=50;
            ap.getChildren().addAll(docenteProp,cdocenteProp);
        }


        cancel = new Button("Cancelar");
        cancel.setLayoutX(60); cancel.setLayoutY(439);
        cancel.setStyle("-fx-background-color:  #e85353;");
        cancel.setCancelButton(true);

        add = new Button("Guardar");
        add.setLayoutX(680); add.setLayoutY(437);

        ap.getChildren().addAll(cancel,add);

        //this.getButtonTypes().add(ButtonType.CLOSE);
        this.setContent(ap);

    }
    private void registerHandlers() {

        add.setOnAction(event -> {
            if(type.equals(T1Estagio.class)) System.out.println(managerUI.editT1(
                    a.getCodigo(),
                    tftitulo.getText().isEmpty()? a.getTitulo():tftitulo.getText(),
                    tfentidadeAcolhimento.getText().isEmpty() ? ((T1Estagio)a).getEntidadeAcolh(): tfentidadeAcolhimento.getText(),
                    ((T1Estagio)a).getRamos()));
            if(type.equals(T2Projeto.class)) System.out.println(managerUI.editT2(a.getCodigo(),a.getTitulo(), ((T2Projeto)a).getDocenteProp(), ((T2Projeto)a).getRamos()));
            if(type.equals(T3Autoprop.class)) System.out.println(managerUI.editT3(a.getCodigo(),a.getTitulo(), ((T3Autoprop)a).getAlunoID()));
            this.getScene().getWindow().hide();
        });

        cancel.setOnAction(event -> {
            this.getScene().getWindow().hide();
        });

    }



}
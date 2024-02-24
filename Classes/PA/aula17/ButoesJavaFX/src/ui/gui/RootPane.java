package ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class RootPane extends BorderPane{
    //Dados
    protected int nBlue, nGreen, nOther;

    Button blueButton, greenButton, otherButton;
    TextField colorField;
    Label info;

    public RootPane(){
        nBlue=nGreen=nOther=0;

        createViews();
        registerHandlers();
        update();
    }

    public void createViews(){
        blueButton=new Button("Blue");
        greenButton=new Button("Green");
        otherButton=new Button("Change");
        colorField=new TextField();
        info=new Label();
        HBox hbox=new HBox();
        hbox.getChildren().addAll(blueButton, greenButton, colorField, otherButton);
        this.setTop(hbox);
        this.setBottom(info);
    }

    public void registerHandlers(){

    }

    public void update(){
        info.setText("Blue: "+nBlue+"\tGreen: "+nGreen+"\tOther: "+nOther);
    }
}

package ui.gui;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.DrawingManager;
import model.Figure;
import ui.gui.resources.CSSManager;
import ui.gui.resources.ImageManager;

import javax.swing.text.html.ImageView;

public class DrawingToolBar extends ToolBar {
    private static final int TOGGLE_SIZE=40;
    private static final int TOGGLE_IMG_SIZE=TOGGLE_SIZE-10;

    DrawingManager drawing; //modelo observável. No tp é o contexto observável, as vistas acedem a isso em vez de ao contexto diretamente.

    ToggleButton btnRed, btnGreen, btnBlue, btnRandom;
    ToggleButton btnLine, btnRect, btnOval;

    Button btnClear;
    Rectangle rectOtherColor;
    ToggleGroup colorsGroup, figuresGroup;
    ColorPicker colorPicker;

    public DrawingToolBar(DrawingManager drawing){
        this.drawing=drawing;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        CSSManager.applyCSS(this, "mystyles.css");

        Rectangle rectR = new Rectangle(0,0,TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        rectR.setFill(Color.color(1,0,0));

        Rectangle rectG = new Rectangle(0,0,TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        rectG.setFill(Color.color(0,1,0));

        Rectangle rectB = new Rectangle(0,0,TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        rectB.setFill(Color.color(0,0,1));

        rectOtherColor = new Rectangle(0,0,TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        rectOtherColor.setFill(Color.MAGENTA);

        btnRed=new ToggleButton(null, rectR);
        btnRed.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        btnGreen=new ToggleButton(null, rectG);
        btnGreen.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        btnBlue=new ToggleButton(null, rectB);
        btnBlue.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        btnRandom=new ToggleButton("Random", rectOtherColor);
        btnRandom.setPrefHeight(TOGGLE_SIZE);
        //CSSManager.applyCSS(btnRandom, "mystyles.css");
        btnRandom.setId("specialButton");

        colorPicker=new ColorPicker();
        colorPicker.setPrefHeight(TOGGLE_SIZE);

        Line shapeLine=new Line(0,0, TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        shapeLine.setFill(Color.INDIGO);

        Rectangle shapeRect=new Rectangle(0,0, TOGGLE_IMG_SIZE, TOGGLE_IMG_SIZE);
        shapeRect.setFill(Color.INDIGO);

        Ellipse shapeOval=new Ellipse(TOGGLE_SIZE/2,TOGGLE_SIZE/2, TOGGLE_IMG_SIZE/2, TOGGLE_IMG_SIZE/2);
        shapeOval.setFill(Color.INDIGO);

        btnLine=new ToggleButton(null, shapeLine);
        btnLine.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        btnRect=new ToggleButton(null, shapeRect);
        btnRect.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        btnOval=new ToggleButton(null, shapeOval);
        btnOval.setPrefSize(TOGGLE_SIZE, TOGGLE_SIZE);

        //btnClear=new Button("Clear", ImageManager.getImageView("Clear-icon.png", TOGGLE_IMG_SIZE));
        btnClear=new Button(null, ImageManager.getImageView("Clear-icon.png", TOGGLE_IMG_SIZE));
        btnClear.setPrefHeight(TOGGLE_SIZE);

        colorsGroup=new ToggleGroup();
        btnRed.setToggleGroup(colorsGroup);
        btnBlue.setToggleGroup(colorsGroup);
        btnGreen.setToggleGroup(colorsGroup);
        btnRandom.setToggleGroup(colorsGroup);

        figuresGroup=new ToggleGroup();
        btnLine.setToggleGroup(figuresGroup);
        btnRect.setToggleGroup(figuresGroup);
        btnOval.setToggleGroup(figuresGroup);

        this.getItems().addAll(btnRed, btnGreen, btnBlue, btnRandom, colorPicker, new Separator(), btnLine, btnRect, btnOval, new Separator(), btnClear);
    }

    private void registerHandlers(){
        drawing.addPropertyChangeListener(DrawingManager.PROP_TOOLS, evt -> {
            update();
        });

        btnRed.setOnAction(actionEvent -> {
            drawing.setRGB(1,0,0);
            //update(); //estes updates não podem estar no TP
        });

        btnGreen.setOnAction(actionEvent -> {
            drawing.setRGB(0,1,0);
            //update();
        });

        btnBlue.setOnAction(actionEvent -> {
            drawing.setRGB(0,0,1);
            //update();
        });

        btnRandom.setOnAction(actionEvent -> {
            drawing.setRGB(Math.random(),Math.random(),Math.random());
            //update();
        });

        colorPicker.setOnAction(actionEvent -> {
            //btnRandom=setSelecter(true);
            Color color=colorPicker.getValue();
            drawing.setRGB(color.getRed(), color.getGreen(), color.getBlue());
            //update();
        });

        btnClear.setOnAction(actionEvent -> {
            drawing.init();
        });

        btnRandom.fire(); //clicar no botão. Ao iniciar é escolhida uma cor aleatória

        btnLine.setOnAction(actionEvent -> drawing.setCurrentType(Figure.FigureType.LINE));
        btnRect.setOnAction(actionEvent -> drawing.setCurrentType(Figure.FigureType.RECTANGLE));
        btnOval.setOnAction(actionEvent -> drawing.setCurrentType(Figure.FigureType.OVAL));

        btnLine.fire();
    }

    private void update(){
        Color color=Color.color(drawing.getR(), drawing.getG(), drawing.getB());

        if(Color.color(1,0,0).equals(color))
            btnRed.setSelected(true);
        else if(Color.color(0,1,0).equals(color))
            btnGreen.setSelected(true);
        else
            btnBlue.setSelected(true);

        rectOtherColor.setFill(color);
        colorPicker.setValue(color);

        btnLine.setSelected(drawing.getCurrentType()== Figure.FigureType.LINE);
        btnRect.setSelected(drawing.getCurrentType()== Figure.FigureType.RECTANGLE);
        btnOval.setSelected(drawing.getCurrentType()== Figure.FigureType.OVAL);
    }
}

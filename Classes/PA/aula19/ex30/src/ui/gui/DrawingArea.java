package ui.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.Drawing;
import model.DrawingManager;
import model.Figure;
import ui.gui.resources.FontManager;
import ui.gui.resources.ImageManager;

public class DrawingArea extends Canvas {
    DrawingManager drawing;

    public DrawingArea(DrawingManager drawing){
        //super(400,400); //tamanho fixo
        this.drawing=drawing;

        //createViews();
        registHandlers();
        update();
    }

    private void registHandlers(){
        drawing.addPropertyChangeListener(DrawingManager.PROP_FIGURES, evt -> update());

        this.setOnMousePressed(mouseEvent -> {
            /*if(mouseEvent.isControlDown()){
                drawing.setCurrentType(Figure.FigureType.RECTANGLE);
            }
            else if(mouseEvent.isAltDown()){
                drawing.setCurrentType(Figure.FigureType.OVAL);
            }
            else{
                drawing.setCurrentType(Figure.FigureType.LINE);
            }*/

            //drawing.setRGB(Math.random(),Math.random(),Math.random());

            drawing.createFigure(mouseEvent.getX(), mouseEvent.getY());

            //update(); //desaparece por causa do propertyChange. O modelo é que avisa as listas para atualizarem.
        });

        this.setOnMouseDragged(mouseEvent -> {
            drawing.updateCurrentFigure(mouseEvent.getX(), mouseEvent.getY());
            //update();
        });

        this.setOnMouseReleased(mouseEvent -> {
            drawing.finishCurrentFigure(mouseEvent.getX(),mouseEvent.getY());
            //update();
        });
    }

    private void update(){
        GraphicsContext gc=this.getGraphicsContext2D();

        clearScreen(gc);

        setBackground(gc);

        for(Figure figure:drawing.getList()) //getList cria uma lista nova com clones
            drawFigure(gc,figure);

        drawFigure(gc,drawing.getCurrentFigure());
    }

    private void clearScreen(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGOLDENRODYELLOW);
        gc.fillRect(0,0,getWidth(),getHeight());
    }

    private void drawFigure(GraphicsContext gc, Figure figure){
        if(figure==null)
            return;

        Color color=Color.color(figure.getR(),figure.getG(),figure.getB());
        gc.setFill(color);
        gc.setStroke(color.darker());
        gc.setLineWidth(5);

        switch(figure.getType()){
            case LINE -> gc.strokeLine(figure.getX1(), figure.getY1(),figure.getX2(),figure.getY2());

            case RECTANGLE -> {
                gc.fillRect(figure.getX1(), figure.getY1(),figure.getWidth(),figure.getHeight());
                gc.strokeRect(figure.getX1(), figure.getY1(),figure.getWidth(),figure.getHeight());
            }

            case OVAL -> {
                gc.fillOval(figure.getX1(), figure.getY1(),figure.getWidth(),figure.getHeight());
                gc.strokeOval(figure.getX1(), figure.getY1(),figure.getWidth(),figure.getHeight());
            }
        }
    }

    private void setBackground(GraphicsContext gc){
        gc.drawImage(ImageManager.getImage("background.jpeg"),0,0,getWidth(),getHeight());

        /*for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                gc.drawImage(ImageManager.getImage("background.jpeg"),i*getWidth()/3,j*getHeight()/3,getWidth()/3,getHeight()/3);*/

        gc.setFont(FontManager.loadFont("greatvibes.otf", 52));
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        gc.fillText("Programação Avançada",150,50);
        gc.strokeText("Programação Avançada",150,100);

        gc.fillText("Programação Avançada", 150, 150);
        gc.strokeText("Programação Avançada", 150, 150);

        Image imageE=ImageManager.getExternalImage("https://logodownload.org/wp-content/uploads/2017/04/java-logo-12.png");
        gc.drawImage(imageE, 10,10, imageE.getWidth()/3, imageE.getHeight()/3);
    }
    public void updateSize(double w, double h){
        setWidth(w);
        setHeight(h);
        update();
    }
}

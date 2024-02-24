package ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Drawing;
import model.DrawingManager;

public class MainJFX extends Application {
    DrawingManager drawing;

    @Override
    public void init() throws Exception {
        super.init();
        drawing = new DrawingManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        configureStage(stage,-1,-1);
        configureStage(new Stage(),0, stage.getY());
        configureListStage(stage.getX()+stage.getWidth(),stage.getHeight());
    }

    public void configureStage(Stage stage, double x, double y){
        RootPane root = new RootPane(drawing);
        Scene scene = new Scene(root,700,400);
        stage.setScene(scene);
        stage.setTitle("Drawing@PA");
        if(x>=0 && y>=0){
            stage.setX(x);
            stage.setY(y);
        }
        stage.setOnCloseRequest(evt -> Platform.exit()); //fecha ambas as janelas
        stage.show();
    }

    public void configureListStage(double x, double y){
        Stage stage=new Stage();
        ListPane root=new ListPane(drawing);
        Scene scene=new Scene(root, 700,400);
        stage.setScene(scene);
        stage.setTitle("Drawing@PA");
        if(x>=0 && y>=0){
            stage.setX(x);
            stage.setY(y);
        }
        //stage.setOnCloseRequest(evt -> Platform.exit()); //fecha ambas as janelas
        stage.show();
    }
}

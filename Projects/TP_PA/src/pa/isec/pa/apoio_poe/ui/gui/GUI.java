package pa.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pa.isec.pa.apoio_poe.model.ManagerUI;

public class GUI extends Application {
    ManagerUI managerUI;

    @Override
    public void init() throws Exception {
        super.init();
        managerUI = new ManagerUI(); // here or in the constructor
    }

    @Override
    public void start(Stage stage) throws Exception {
        configureStage(stage);
        configureStage(new Stage());
   }

    public void configureStage(Stage stage){
        RootPane root = new RootPane(managerUI);
        Scene scene = new Scene(root,1076,640);
        stage.setScene(scene);
        stage.setTitle("Apoio POE");
        stage.setMinWidth(700);
        stage.setMinHeight(400);
        stage.setOnCloseRequest(evt -> Platform.exit());
        stage.show();
    }
}

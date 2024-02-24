package pt.isec.pa.calculator.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pt.isec.pa.calculator.model.CalculatorManager;

public class MainJFX extends Application {
    CalculatorManager model;

    @Override
    public void init() throws Exception {
        super.init();
        model = new CalculatorManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/calculator_pane.fxml"));
        Parent root=loader.load();

        Scene scene=new Scene(root);
        scene.setUserData(model);
        stage.setScene(scene);
        stage.setTitle("Calculator@PA");
        stage.show();
    }
}

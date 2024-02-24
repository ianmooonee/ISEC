package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;

import java.io.File;

public class Header extends AnchorPane {
    ManagerUI manager;
    Label label;
    public Header(ManagerUI manager){
        this.manager=manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        label = new Label("Apoio POE");
        label.setFont(Font.font("System", FontWeight.BOLD, 29));
        this.prefHeight(200); this.prefWidth(200);

        this.getChildren().addAll(label);
    }

    private void registerHandlers(){

    }

    private void update(){

    }
}

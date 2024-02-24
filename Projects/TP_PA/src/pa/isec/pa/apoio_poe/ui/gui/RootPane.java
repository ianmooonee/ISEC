package pa.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pa.isec.pa.apoio_poe.model.ManagerUI;

public class RootPane extends BorderPane {
    ManagerUI managerUI;
    MainPane mainPane;

    public RootPane(ManagerUI managerUI) {
        this.managerUI=managerUI;
        createViews();
        registerHandlers();
        //update();
    }

    private void createViews() {
        //this.setTop(new DrawingToolBar(drawing));
        VBox vb = new VBox(new Header(managerUI));
        vb.setPadding(new Insets(10, 5, 5, 10));
        vb.setStyle("-fx-background-color: #2e97d5;");
        this.setTop(vb);

        mainPane=new MainPane(managerUI);
        this.setCenter(mainPane);
    }

    private void registerHandlers() {
    }

    private void update() {
    }
}

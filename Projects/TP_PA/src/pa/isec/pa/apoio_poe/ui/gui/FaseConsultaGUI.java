package pa.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

public class FaseConsultaGUI extends AnchorPane {
    ManagerUI managerUI;
    Button terminar;


    public FaseConsultaGUI(ManagerUI managerUI) {
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Fase 5 -Consulta");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        terminar = new Button("Terminar");
        terminar.prefHeight(25); terminar.prefWidth(94);
        terminar.setLayoutX(850); terminar.setLayoutY(462);

        this.getChildren().addAll(mp, terminar);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> update());

        terminar.setOnAction(event -> {
            Platform.exit();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASECONSULTA) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

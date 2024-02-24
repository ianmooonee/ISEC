package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

public class FaseGestaoOrientadoresGUI extends AnchorPane {
    ManagerUI managerUI;
    Button back;


    public FaseGestaoOrientadoresGUI(ManagerUI managerUI) {
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Gestao de orientadores");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        back = new Button("Menú Anterior");
        back.prefHeight(25); back.prefWidth(94);
        back.setLayoutX(72);back.setLayoutY(464);

        this.getChildren().addAll(mp, back);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> update());

        back.setOnAction(event -> {
            managerUI.previous();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASEGESTAOORIENTADORES) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

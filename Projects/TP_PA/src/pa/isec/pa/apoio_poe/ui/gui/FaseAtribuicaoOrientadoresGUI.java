package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

public class FaseAtribuicaoOrientadoresGUI extends AnchorPane {
    ManagerUI managerUI;
    Button back, fase5, gOrientadores, lOrientadores;

    public FaseAtribuicaoOrientadoresGUI(ManagerUI managerUI){
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Fase 4 - Atribuicao de orientadores");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        gOrientadores = new Button("Gestao Orientadores");
        gOrientadores.setLayoutX(236); gOrientadores.setLayoutY(165);
        lOrientadores = new Button("Listas Orientadores");
        lOrientadores.setLayoutX(236); lOrientadores.setLayoutY(224);
        back = new Button("<< Atribuicao automatica");
        back.setLayoutX(81); back.setLayoutY(462);
        fase5 = new Button(">> Fase 5 - Consulta");
        fase5.setLayoutX(850); fase5.setLayoutY(462);

        this.getChildren().addAll(mp, gOrientadores, lOrientadores, back, fase5);
    }

    private void registerHandlers(){
        managerUI.addPropertyChangeListener(evt -> update());

        back.setOnAction( event -> {
            managerUI.previous();
        });

        gOrientadores.setOnAction( event -> {
            managerUI.goToGestaoOrientadores();
        });
        lOrientadores.setOnAction( event -> {
            managerUI.goToListaOrientadores();
        });

        fase5.setOnAction( event -> {
            managerUI.next();
        });
    }

    private void update(){
        if (managerUI.getState() != State.FASEATRIBUICAOORIENTADORES) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}

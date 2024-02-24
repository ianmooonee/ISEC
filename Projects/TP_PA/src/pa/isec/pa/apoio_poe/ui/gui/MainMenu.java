package pa.isec.pa.apoio_poe.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenu extends AnchorPane {
    ManagerUI managerUI;
    Button ultimaSessao, config;

    public MainMenu(ManagerUI managerUI){
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Menú Principal");
        mp.setFont(Font.font("System",FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        ultimaSessao = new Button("Continuar Última Sessão");
        ultimaSessao.setLayoutX(144); ultimaSessao.setLayoutY(172);
        config = new Button("Iniciar Fase 1 - Configuração");
        config.setLayoutX(144); config.setLayoutY(231);
        this.getChildren().addAll(mp,ultimaSessao,config);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt ->  update() );

        ultimaSessao.setOnAction( event -> {
            managerUI.load();
        });
        config.setOnAction( event -> {
            managerUI.next();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASEINICIO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }


}

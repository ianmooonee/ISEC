package pa.isec.pa.apoio_poe.ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

public class FaseOpCandGUI  extends AnchorPane {
    ManagerUI managerUI;
    Button gCand, lAlunos, lProp, fecharFase, back, fase3;

    public FaseOpCandGUI(ManagerUI managerUI){
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        Label mp = new Label("Fase 2 - Opções de Candidatura");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        gCand = new Button("Gestão Candidaturas");
        gCand.setLayoutX(236); gCand.setLayoutY(165);
        lAlunos = new Button("Listas Alunos");
        lAlunos.setLayoutX(236); lAlunos.setLayoutY(224);
        lProp = new Button("Listas Propostas");
        lProp.setLayoutX(236); lProp.setLayoutY(279);
        fecharFase = new Button("Fechar Fase");
        fecharFase.setLayoutX(236); fecharFase.setLayoutY(331);
        back = new Button("<< Fase 1 - Configuração");
        back.setLayoutX(81); back.setLayoutY(462);
        fase3 = new Button(">> Fase 3 - Atribuição de Propostas");
        fase3.setLayoutX(850); fase3.setLayoutY(462);

        this.getChildren().addAll(mp,gCand, lAlunos, lProp, fecharFase, back, fase3);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> { update(); });

        gCand.setOnAction( event -> {
            managerUI.goToGestaoCandidaturas();
        });
        lAlunos.setOnAction( event -> {
            managerUI.goToListasAlunos();
        });
        lProp.setOnAction( event -> {
            managerUI.goToListasPropostas();
        });
        fecharFase.setOnAction( event -> {
            System.out.println(managerUI.getRamosInfo());
            System.out.println(managerUI.setOpCandClosed(true));
        });
        back.setOnAction( event -> {
            managerUI.previous();
        });
        fase3.setOnAction(event -> {
            managerUI.next();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASEOPCOESCANDIDATURAS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }


}

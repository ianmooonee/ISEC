package pa.isec.pa.apoio_poe.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FaseConfigGUI extends AnchorPane{
    ManagerUI managerUI;
    Button gAlunos, gDocentes, gPropostas, fecharFase, back, fase2;

    public FaseConfigGUI(ManagerUI managerUI){
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        Label mp = new Label("Fase 1 - Configuração");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        gAlunos = new Button("Gestão Alunos");
        gAlunos.setLayoutX(236); gAlunos.setLayoutY(165);
        gDocentes = new Button("Gestão Docentes");
        gDocentes.setLayoutX(236); gDocentes.setLayoutY(224);
        gPropostas = new Button("Gestão Propostas");
        gPropostas.setLayoutX(236); gPropostas.setLayoutY(279);
        fecharFase = new Button("Fechar Fase");
        fecharFase.setLayoutX(236); fecharFase.setLayoutY(331);
        back = new Button("<< Menú Principal");
        back.setLayoutX(81); back.setLayoutY(462);
        fase2 = new Button(">> Fase 2 - Opções de Candidatura");
        fase2.setLayoutX(850); fase2.setLayoutY(462);

        this.getChildren().addAll(mp,gAlunos, gDocentes, gPropostas, fecharFase, back, fase2);
    }

    private void registerHandlers() {
        managerUI.addPropertyChangeListener(evt -> { update(); });

        gAlunos.setOnAction( event -> {
            managerUI.goToGestaoAlunos();
        });
        gDocentes.setOnAction( event -> {
            managerUI.goToGestaoDocentes();
        });
        gPropostas.setOnAction( event -> {
            managerUI.goToGestaoPropostas();
        });
        fecharFase.setOnAction( event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fechar Fase");
            alert.setHeaderText(managerUI.setConfigClosed(true));
            alert.setContentText(managerUI.getRamosInfo());

            alert.showAndWait();
        });
        back.setOnAction( event -> {
            managerUI.previous();
        });
        fase2.setOnAction( event -> {
            managerUI.next();
        });
    }

    private void update() {
        if (managerUI.getState() != State.FASECONFIGURACAO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }


}

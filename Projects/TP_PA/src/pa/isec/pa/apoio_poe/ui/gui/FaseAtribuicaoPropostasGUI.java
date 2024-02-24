package pa.isec.pa.apoio_poe.ui.gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.data.Aluno;
import pa.isec.pa.apoio_poe.model.data.Proposta;
import pa.isec.pa.apoio_poe.model.fsm.State;

import java.util.Map;

public class FaseAtribuicaoPropostasGUI extends AnchorPane {
    ManagerUI managerUI;
    Button back, fase4, atribAuto;
    TableView tb ;

    public FaseAtribuicaoPropostasGUI(ManagerUI managerUI){
        this.managerUI = managerUI;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        Label mp = new Label("Fase 3 - Atribuicao de candidaturas");
        mp.setFont(Font.font("System", FontWeight.BLACK, 27));
        mp.setLayoutX(81);mp.setLayoutY(71);

        tb = new TableView<>();
        tb.prefHeight(318);tb.prefHeight(931);
        tb.setLayoutX(72); tb.setLayoutY(128);
        tb.setPlaceholder(new Label("Ainda não existem assossiações."));



        addButtonsToTable();
        updateTable();

        atribAuto = new Button("Atribuicao automatica");
        atribAuto.setLayoutX(920); atribAuto.setLayoutY(95);
        back = new Button("<< Gestao de candidaturas");
        back.setLayoutX(81); back.setLayoutY(462);
        fase4 = new Button(">> Fase 4 - Atribuicao de orientadores");
        fase4.setLayoutX(850); fase4.setLayoutY(462);

        this.getChildren().addAll(mp, tb,atribAuto, back, fase4);
    }

    private void registerHandlers(){
        managerUI.addPropertyChangeListener(evt -> update());

        atribAuto.setOnAction(event -> {
            System.out.println(managerUI.atribPropAutom());
        });

        back.setOnAction( event -> {
            managerUI.previous();
        });

        fase4.setOnAction( event -> {
            managerUI.next();
        });
    }

    private void updateTable(){
        tb.getItems().clear();
        for (Map.Entry<Aluno, Proposta> entry : managerUI.getAtribuicoes().entrySet()){
            tb.getItems().addAll(entry);
        }
    }

    private void update(){
        if (managerUI.getState() != State.FASEATRIBUICAOPROPOSTAS) {
            this.setVisible(false);
            return;
        }
        updateTable();
        this.setVisible(true);
    }

    private void addButtonsToTable() {
    }
}

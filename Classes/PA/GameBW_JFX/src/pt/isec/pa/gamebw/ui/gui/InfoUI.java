package pt.isec.pa.gamebw.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.gamebw.model.GameBWManager;
import pt.isec.pa.gamebw.model.fsm.GameBWState;

public class InfoUI extends BorderPane {
    GameBWManager gameBWManager;
    Button btnStart, btnExit;
    Label lbWBWon, lbWBOut, lbBBOut;

    public InfoUI(GameBWManager gameBWManager){
        this.gameBWManager=gameBWManager;

        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        lbWBWon=new Label();
        lbBBOut=new Label();
        lbWBOut=new Label();
        lbWBWon.setId("resultlabel");
        lbBBOut.setId("resultlabel");
        lbWBOut.setId("resultlabel");

        VBox vbInfo=new VBox(lbWBWon, lbWBOut, lbBBOut);
        vbInfo.setPadding(new Insets(10));
        vbInfo.setSpacing(10);
        vbInfo.setBackground(new Background(new BackgroundFill(Color.color(1,1,1,0.5), CornerRadii.EMPTY, Insets.EMPTY)));

        btnStart=new Button("Start");
        btnStart.setMinWidth(100);
        btnExit=new Button("Exit");
        btnExit.setMinWidth(100);

        HBox hBox=new HBox(btnStart, btnExit);
        hBox.setSpacing(20);

        VBox vBox=new VBox(vbInfo, hBox);
        vBox.setFillWidth(false);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        this.setCenter(vBox);
    }
    private void registerHandlers() {
        gameBWManager.addPropertyChangeListener(evt -> update());

        btnStart.setOnAction(event -> {
            gameBWManager.start();
        });

        btnExit.setOnAction(event -> {
            Platform.exit();
        });
    }
    private void update(){
        if(gameBWManager.getState()!= GameBWState.INFO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        lbWBWon.setText("White Balls Won: " + gameBWManager.getNrWhiteBallsWon());
        lbWBOut.setText("White Balls Won: " + gameBWManager.getNrWhiteBallsOut());
        lbBBOut.setText("White Balls Won: " + gameBWManager.getNrBlackBallsOut());
    }
}


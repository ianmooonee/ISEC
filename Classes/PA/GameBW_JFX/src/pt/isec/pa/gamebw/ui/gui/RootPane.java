package pt.isec.pa.gamebw.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import pt.isec.pa.gamebw.model.GameBWManager;
import pt.isec.pa.gamebw.ui.gui.resources.CSSManager;
import pt.isec.pa.gamebw.ui.gui.resources.ImageManager;

public class RootPane extends BorderPane {
    GameBWManager gameBWManager;

    public RootPane(GameBWManager gameBWManager) {
        this.gameBWManager = gameBWManager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        StackPane stackPane = new StackPane(
                new BeginUI(gameBWManager),
                new WaitBetUI(gameBWManager),
                new LostWaitDecisionUI(gameBWManager),
                new InfoUI(gameBWManager)
        );
        stackPane.setBackground(new Background(new BackgroundImage(
                ImageManager.getImage("background.png"),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,true,false)
        )));
        this.setCenter(stackPane);

        this.setRight(
                new BallsWon(gameBWManager)
        );
        this.setBottom(
                new BallsOut(gameBWManager)
        );

    }

    private void registerHandlers() {
    }

    private void update() {
    }
}

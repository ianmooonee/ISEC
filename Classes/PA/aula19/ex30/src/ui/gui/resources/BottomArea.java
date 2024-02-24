package ui.gui.resources;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.HBox;
import model.DrawingManager;

import java.util.List;

public class BottomArea extends HBox {
    private static final int BUTTON_SIZE=40;
    private static final int BUTTON_IMG_SIZE=BUTTON_SIZE-10;
    private static final int BUTTON_SPACING=10;

    private static final String PLAY_ICON="isec.jpg";
    private static final String STOP_ICON="Clear-icon.png";

    DrawingManager drawing; //contexto observável (onde estão os firePropertyChange)

    Button btnPlaySound1, btnPlaySound2, btnStopSound, btnInfo;

    public BottomArea(DrawingManager drawing){
        this.drawing=drawing;

        createViews();
        registerHandlers();
        //update();
    }

    private void createViews() {
        CSSManager.applyCSS(this, "mystyle.css");

        btnPlaySound1=new Button("Play 1");
        btnPlaySound1.setPrefHeight(BUTTON_SIZE);

        btnPlaySound2=new Button("", ImageManager.getImageView(PLAY_ICON, BUTTON_IMG_SIZE));
        btnPlaySound2.setPrefHeight(BUTTON_SIZE);

        btnStopSound=new Button("", ImageManager.getImageView(STOP_ICON, BUTTON_IMG_SIZE));
        btnStopSound.setPrefHeight(BUTTON_SIZE);

        btnInfo=new Button("Info");
        btnInfo.setPrefHeight(BUTTON_SIZE);
        btnInfo.setId("specialButton");

        setAlignment(Pos.CENTER);
        setSpacing(BUTTON_SPACING);
        setPrefWidth(Integer.MAX_VALUE);

        getChildren().addAll(btnPlaySound1, btnPlaySound2, btnStopSound, btnInfo);

    }

    private void registerHandlers() {
        btnInfo.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);

            alert.setTitle("Info");
            alert.setContentText(drawing.getList().toString());
            alert.setHeaderText(drawing.getList().size()+" figure(s)");
            alert.showAndWait();
            /*alert.showAndWait().ifPresent(response -> {
                System.out.println(response);
            });*/
        });

        btnPlaySound1.setOnAction(event -> {
            //TextInputDialog tid=new TextInputDialog();
            /*
            * tid.showAndWait().ifPresent(response -> SoundManager.play(response));
            */
        });

        btnPlaySound2.setOnAction(event -> {
            List<String> lst=List.of("chicken.mp3", "music.mp3", "muttley.mp3");
            ChoiceDialog<String> cd=new ChoiceDialog<>(lst.get(0), lst);
            cd.setTitle("Play");
            cd.setContentText("Options");
            cd.setHeaderText("Select a option");
            cd.showAndWait().ifPresent(SoundManager::play);
            //cd.showAndWait().ifPresent(response -> SoundManager.play(response));
        });

        btnStopSound.setOnAction(event -> {
            SoundManager.stop();
        });
    }
}

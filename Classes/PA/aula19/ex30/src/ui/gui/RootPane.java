package ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.gui.resources.BottomArea;
import model.DrawingManager;

public class RootPane extends BorderPane {
    DrawingManager drawing;
    DrawingArea area;

    Pane areaPane;

    public RootPane(DrawingManager drawing) {
        this.drawing=drawing;
        createViews();
        registerHandlers();
        //update();
    }

    private void createViews() {
        //this.setTop(new DrawingToolBar(drawing));
        this.setTop(new VBox(new AppMenu(drawing), new DrawingToolBar(drawing)));

        area=new DrawingArea(drawing);
        areaPane=new Pane(area);
        this.setCenter(areaPane);
        //this.setCenter(area);

        this.setBottom(new BottomArea(drawing));
    }

    private void registerHandlers() {
        this.widthProperty().addListener(observable -> {
            area.updateSize(getWidth(),getHeight());
        });

        this.heightProperty().addListener(observable -> {
            area.updateSize(getWidth(),getHeight());
        });
    }

    private void update() {
    }
}

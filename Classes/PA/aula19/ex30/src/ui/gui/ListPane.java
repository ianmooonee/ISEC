package ui.gui;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import model.DrawingManager;
import model.Figure;

public class ListPane extends ListView<Figure> {
    DrawingManager drawing;

    MenuItem delete, changeColor;
    public ListPane(DrawingManager drawing) {
        this.drawing=drawing;

        createViews();
        registHandlers();
        update();
    }

    private void createViews() {
        delete=new MenuItem("Delete");
        changeColor=new MenuItem("Change Color");

        this.setContextMenu(new ContextMenu(delete, changeColor));
    }
    public void registHandlers(){
        drawing.addPropertyChangeListener(DrawingManager.PROP_FIGURES, evt -> update());

        this.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount()==2){
                drawing.remove(this.getSelectionModel().getSelectedIndex());
            }
        });

        delete.setOnAction(e -> {
            drawing.remove(this.getSelectionModel().getSelectedIndex());
        });

        changeColor.setOnAction(e -> {
            //TODO
        });
    }

    private void update(){
        this.getItems().clear();
        this.getItems().addAll(drawing.getList());
    }
}

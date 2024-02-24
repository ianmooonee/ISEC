package ui.gui;

import javafx.application.Platform;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import model.DrawingManager;
import ui.gui.resources.ImageManager;

import java.awt.*;
import java.io.File;

public class AppMenu extends MenuBar {
    private static final int TOGGLE_SIZE=40;
    private static final int TOGGLE_IMG_SIZE=TOGGLE_SIZE-10;
    DrawingManager drawing;
    Menu mnFile;
    MenuItem mnNew, mnOpen, mnSave, mnExit;

    Menu mnEdit;
    MenuItem mnUndo, mnRedo;

    public AppMenu(DrawingManager drawing){
        this.drawing=drawing;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews(){
        mnFile=new Menu("File");
        mnNew=new MenuItem("_New", ImageManager.getImageView("Clear-icon.png", TOGGLE_IMG_SIZE));
        mnOpen=new MenuItem("_Open");
        mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        mnSave=new MenuItem("_Save");
        mnExit=new MenuItem("_Exit");
        mnFile.getItems().addAll(mnNew, mnOpen, mnSave, new SeparatorMenuItem(), mnExit);

        mnEdit=new Menu("Edit");
        mnUndo=new MenuItem("_Undo");
        mnRedo=new MenuItem("_Redo");
        mnEdit.getItems().addAll(mnUndo, mnRedo);

        this.getMenus().addAll(mnFile, mnEdit);
    }

    private void registerHandlers(){
        mnNew.setOnAction(e -> {
            drawing.init();
        });

        mnOpen.setOnAction(e -> {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("File Open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Drawing (*.dat)", "*.dat"), new FileChooser.ExtensionFilter("All", "*.*"));

            File hFile=fileChooser.showOpenDialog(this.getScene().getWindow()); //bloquear a janela

            if(hFile!=null)
                drawing.load(hFile);
        });

        mnSave.setOnAction(e -> {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("File Save...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Drawing (*.dat)", "*.dat"), new FileChooser.ExtensionFilter("All", "*.*"));

            File hFile=fileChooser.showSaveDialog(this.getScene().getWindow()); //bloquear a janela

            if(hFile!=null)
                drawing.save(hFile);
        });

        mnExit.setOnAction(e -> {
            Platform.exit();
        });

        mnUndo.setOnAction(e -> {
            //TODO
        });

        mnRedo.setOnAction(e -> {
            //TODO
        });
    }

    private void update(){
        mnUndo.setDisable(true); //mudar true para !drawing.hasUndo()
        mnRedo.setDisable(true); //mudar true para !drawing.hasRedo()
    }
}

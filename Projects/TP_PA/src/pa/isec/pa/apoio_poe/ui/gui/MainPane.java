package pa.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.states.FaseAtribuicaoPropostas;
import pa.isec.pa.apoio_poe.model.fsm.states.FaseListasOrientadores;

public class MainPane extends AnchorPane {
    ManagerUI manager;
    MainMenu mainMenu;
    FaseConfigGUI faseConfigGUI;
    FaseGAlunosGUI faseGAlunosGUI;
    FaseGDocentesGUI faseGDocentesGUI;
    FaseGPropostasGUI faseGPropostasGUI;
    FaseOpCandGUI faseOpCandGUI;
    FaseGCandidGUI faseGCandidGUI;
    FaseListasAlunosGUI faseListasAlunosGUI;
    FaseListasPropostasGUI faseListasPropostasGUI;
    FaseAtribuicaoPropostasGUI faseAtribuicaoPropostasGUI;
    FaseAtribuicaoOrientadoresGUI faseAtribuicaoOrientadoresGUI;
    FaseGestaoOrientadoresGUI faseGestaoOrientadoresGUI;
    FaseListasOrientadoresGUI faseListasOrientadoresGUI;
    FaseConsultaGUI faseConsultaGUI;
    public MainPane(ManagerUI manager){
        this.manager=manager;
        createViews();
        setup();
        registHandlers();
        update();
    }

    private void createViews(){

        Separator sep = new Separator();
        sep.setPadding(new Insets(5,5,5,5));
        sep.prefWidthProperty().bind(this.widthProperty());
        this.getChildren().addAll(sep);

    }

    private void setup(){
        mainMenu = new MainMenu(manager);
        faseConfigGUI = new FaseConfigGUI(manager);
        faseGAlunosGUI = new FaseGAlunosGUI(manager);
        faseGDocentesGUI = new FaseGDocentesGUI(manager);
        faseGPropostasGUI = new FaseGPropostasGUI(manager);
        faseOpCandGUI = new FaseOpCandGUI(manager);
        faseGCandidGUI = new FaseGCandidGUI(manager);
        faseListasAlunosGUI = new FaseListasAlunosGUI(manager);
        faseListasPropostasGUI = new FaseListasPropostasGUI(manager);
        faseAtribuicaoPropostasGUI = new FaseAtribuicaoPropostasGUI(manager);
        faseAtribuicaoOrientadoresGUI = new FaseAtribuicaoOrientadoresGUI(manager);
        faseGestaoOrientadoresGUI = new FaseGestaoOrientadoresGUI(manager);
        faseListasOrientadoresGUI = new FaseListasOrientadoresGUI(manager);
        faseConsultaGUI = new FaseConsultaGUI(manager);
        //getChildren().addAll(mainMenu,faseConfigUI);
        StackPane stack = new StackPane(mainMenu, faseConfigGUI,faseGAlunosGUI,faseGDocentesGUI,faseGPropostasGUI,faseOpCandGUI, faseGCandidGUI, faseAtribuicaoPropostasGUI, faseAtribuicaoOrientadoresGUI, faseGestaoOrientadoresGUI, faseListasOrientadoresGUI, faseConsultaGUI);
        this.getChildren().add(stack);
    }

    private void registHandlers(){

    }

    private void update(){

    }
}

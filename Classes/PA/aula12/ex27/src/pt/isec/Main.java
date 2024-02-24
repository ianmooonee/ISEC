package pt.isec;

import pt.isec.model.fsm.GameBWContext;
import pt.isec.ui.GameBWUI;

public class Main {
    public static void main(String[] args) {
        GameBWContext fsm = new GameBWContext();
        GameBWUI ui = new GameBWUI(fsm);
        ui.start();
    }
}

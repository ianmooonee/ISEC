package exercicio;

import exercicio.model.fsm.ElevatorContext;
import exercicio.ui.ElevatorUI;

public class Main {

    public static void main(String[] args) {
        ElevatorContext fsm=new ElevatorContext();
        ElevatorUI ui=new ElevatorUI(fsm);
        ui.start();
    }
}

package exercicio.ui;

import exercicio.model.data.Elevator;
import exercicio.model.fsm.ElevatorContext;
import exercicio.model.utils.PAInput;

public class ElevatorUI {
    ElevatorContext fsm;

    public ElevatorUI(ElevatorContext fsm) {
        this.fsm = fsm;
    }

    boolean finish = false;

    public void start() {
        int op;

        while (!finish) {
            switch (fsm.getState()) {
                case GROUND_FLOOR -> groundFloorUI();
                case FIRST_FLOOR -> firstFloorUI();
                case SECOND_FLOOR -> secondFloorUI();
                case UNDER_MAINTENANCE -> underMaintenanceUI(); //b)
            }
        }
    }

    public void groundFloorUI() {
        System.out.println("Ground Floor");

        if ( 1 == PAInput.chooseOption("Elevator","Up","Quit")) {
            fsm.up();
        } else {
            finish = true;
        }
    }

    public void firstFloorUI() {
        System.out.println("1st Floor");

        switch (PAInput.chooseOption("Elevator","Up","Down","Quit")) {
            case 1 -> fsm.up();
            case 2 -> fsm.down();
            default -> finish = true;
        }
    }

    public void secondFloorUI() {
        System.out.println("2nd Floor");

        if (PAInput.chooseOption("Elevator", "Down", "Quit") == 1) {
            fsm.down();
        } else {
            finish = true;
        }
    }

    // b)
    public void underMaintenanceUI() {
        System.out.println("Elevator current floor: "+fsm.getCurrentFloor());
        System.out.println("**** Elevator under maintenance ****");

        if (PAInput.chooseOption("Elevator", "Use Security Key", "Quit") == 1) {

            String key = PAInput.readString("Security key: ",false);

            if (!fsm.safetyKey(key)) {
                System.out.println("The key was not accepted.");
            }

        } else {
            finish = true;
        }
    }
}

/*public class ElevatorUI {
    ElevatorContext fsm;

    public ElevatorUI(ElevatorContext fsm){
        this.fsm=fsm;
    }

    public void start(){
        int op;
        do{
            System.out.println("Elevator current floor: "+fsm.getCurrentFloor());
            op= PAInput.chooseOption("Elevator", "Up", "Down", "Quit");
            switch (op){
                case 1 -> fsm.up();
                case 2 -> fsm.down();
            }
        }while(op>0 && op<3);
    }*/

    /*boolean finish=false;
    public void start2(){
        while(!finish) {
            switch(fsm.getState()){
                case GROUND_FLOOR -> groundFloorUI();
                case FIRST_FLOOR -> firstFloorUI();
                case SECOND_FLOOR -> secondFloorUI();
            }
        }
    }

    public void groundFloorUI(){
        System.out.println("Ground Floor");
        if(1==PAInput.chooseOption("Elevator", "UP", "QUIT")){
            fsm.up();
        }
        else{
            finish=true;
        }
    }*/

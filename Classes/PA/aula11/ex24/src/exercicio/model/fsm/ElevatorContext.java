package exercicio.model.fsm;

import exercicio.model.data.Elevator;

public class ElevatorContext {
    IElevatorState state;
    Elevator elevator;

    public ElevatorContext(){
        elevator=new Elevator(0);
        state=new GroundFloorState(this, elevator);
    }

    public ElevatorState getState(){
        return state.getState();
    }

    void changeState(IElevatorState newState){
        this.state=newState;
    }

    public int getCurrentFloor(){
        return elevator.getCurrentFloor();
    }

    public boolean up(){
        return state.up();
    }

    public boolean down(){
        return state.down();
    }

    public boolean safetyKey(String key){
        return state.safetyKey(key);
    }

    public boolean isUnderMaintenance(){
        return elevator.isUnderMaintenance();
    }
}

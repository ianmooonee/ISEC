package exercicio.model.fsm;

import exercicio.model.data.Elevator;

public class SecondFloorState extends ElevatorStateAdapter{
    SecondFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(2);
    }

    public boolean down(){
        if(Math.random()>0.7) {
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new FirstFloorState(context, elevator));
        return true;
    }

    @Override
    public ElevatorState getState() {
        return ElevatorState.SECOND_FLOOR;
    }
}

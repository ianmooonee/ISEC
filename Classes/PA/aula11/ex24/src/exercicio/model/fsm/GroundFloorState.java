package exercicio.model.fsm;

import exercicio.model.data.Elevator;

public class GroundFloorState extends ElevatorStateAdapter{
    GroundFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(0);
    }

    @Override
    public boolean up(){
        //context.changeState(new FirstFloorState(context, elevator)); //alternativa
        if(Math.random()>0.9) {
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new FirstFloorState(context, elevator));
        return true;
    }

    @Override
    public ElevatorState getState() {
        return ElevatorState.GROUND_FLOOR;
    }
}

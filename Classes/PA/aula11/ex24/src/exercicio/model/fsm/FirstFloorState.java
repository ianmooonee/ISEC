package exercicio.model.fsm;

import exercicio.model.data.Elevator;

public class FirstFloorState extends ElevatorStateAdapter{
    FirstFloorState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setCurrentFloor(1);
    }

    @Override
    public boolean up(){
        if(Math.random()>0.8) {
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new SecondFloorState(context, elevator));
        return true;
    }

    public boolean down(){
        if(Math.random()>0.8) {
            elevator.setUnderMaintenance(true);
            changeState(new UnderMaintenanceState(context, elevator));
            return true;
        }

        changeState(new GroundFloorState(context, elevator));
        return true;
    }

    @Override
    public ElevatorState getState() {
        return ElevatorState.FIRST_FLOOR;
    }
}

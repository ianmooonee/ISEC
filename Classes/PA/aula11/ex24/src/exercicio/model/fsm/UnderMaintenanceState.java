package exercicio.model.fsm;

import exercicio.model.data.Elevator;

import java.util.Objects;

public class UnderMaintenanceState extends ElevatorStateAdapter{
    UnderMaintenanceState(ElevatorContext context, Elevator elevator){
        super(context, elevator);
        elevator.setUnderMaintenance(true);
    }

    @Override
    public boolean safetyKey(String key){
        if(!key.equals(elevator.getSafetyKey()))
            return false;

        elevator.setUnderMaintenance(false);

        changeState(switch (elevator.getCurrentFloor()){
            case 2 -> new SecondFloorState(context, elevator);
            case 1 -> new FirstFloorState(context, elevator);
            default -> new GroundFloorState(context, elevator);
        });
        return true;
    }

    @Override
    public ElevatorState getState() {
        return ElevatorState.UNDER_MAINTENANCE;
    }
}

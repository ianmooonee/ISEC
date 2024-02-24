package exercicio.model.fsm;

import exercicio.model.data.Elevator;

abstract public class ElevatorStateAdapter implements IElevatorState{
    protected Elevator elevator;
    protected ElevatorContext context;

    protected ElevatorStateAdapter(ElevatorContext context, Elevator elevator){
        this.context=context;
        this.elevator=elevator;
    }

    protected void changeState(IElevatorState newState){
        context.changeState(newState);
    }

    @Override
    public boolean up(){
        return false;
    }

    @Override
    public boolean down(){
        return false;
    }

    @Override
    public boolean safetyKey(String key){
        return false;
    }
}

package exercicio.model.fsm;

public interface IElevatorState {
    boolean up();
    boolean down();
    boolean safetyKey(String key);

    ElevatorState getState();
}

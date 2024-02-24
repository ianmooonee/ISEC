package exercicio.model.data;

public class Elevator {
    private int currentFloor;
    private String safetyKey=null;
    private boolean underMaintenance;

    public Elevator(int currentFloor){
        this.currentFloor=currentFloor;
        this.underMaintenance=false;
        this.safetyKey="9999";
    }

    public String getSafetyKey() {
        return safetyKey;
    }

    public void setSafetyKey(String safetyKey) {
        this.safetyKey = safetyKey;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public String toString(){
        return "Elevador  no piso "+currentFloor+".";
    }
}

package exercicio.model;

public class Ligeiro extends Veiculo implements IPassengers{
    private int maxPass;

    public int getMaxPas() {
        return maxPass;
    }

    public void setMaxPas(int maxPas) {
        this.maxPass = maxPas;
    }

    public Ligeiro(int ano, String matricula, int maxPass) {
        super(ano, matricula);
        this.maxPass=maxPass;
    }

    @Override
    public int getNumberPassengers() {
        return this.getMaxPas();
    }

    @Override
    public String toString(){
        return super.toString() + " MaxPass: " + maxPass;
    }
}

package exercicio.model;

public class PesadoPassageiros extends Veiculo implements IMaxLoad, IPassengers{
    private int cargaMax;
    private int maxPass;

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getMaxPass() {
        return maxPass;
    }

    public void setMaxPass(int maxPass) {
        this.maxPass = maxPass;
    }

    public PesadoPassageiros(int ano, String matricula, int maxPass, int cargaMax) {
        super(ano, matricula);
        this.cargaMax=cargaMax;
        this.maxPass=maxPass;
    }

    @Override
    public int getMaxWeight() {
        return this.getCargaMax();
    }

    @Override
    public int getNumberPassengers() {
        return this.getMaxPass();
    }

    @Override
    public String toString(){
        return super.toString() + " MaxPass: " + maxPass + " CargaMax: " + cargaMax;
    }
}

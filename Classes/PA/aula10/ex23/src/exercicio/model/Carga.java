package exercicio.model;

public class Carga extends Veiculo implements IMaxLoad{
    private int cargaMax;

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public Carga(int ano, String matricula, int cargaMax) {
        super(ano, matricula);
        this.cargaMax=cargaMax;
    }

    @Override
    public int getMaxWeight() {
        return this.cargaMax;
    }

    @Override
    public String toString(){
        return super.toString() + " CargaMax: " + cargaMax;
    }
}

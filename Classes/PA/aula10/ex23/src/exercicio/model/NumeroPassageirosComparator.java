package exercicio.model;

import java.util.Comparator;

public class NumeroPassageirosComparator implements Comparator<IPassengers>{
    @Override
    public int compare(IPassengers o1, IPassengers o2) {
        return o1.getNumberPassengers()-o2.getNumberPassengers();
    }
}

package exercicio.model;

import java.util.Comparator;

public class CargaMaxReverseComparator implements Comparator<IMaxLoad> {
    @Override
    public int compare(IMaxLoad o1, IMaxLoad o2) {
        return o2.getMaxWeight()- o1.getMaxWeight();
    }
}
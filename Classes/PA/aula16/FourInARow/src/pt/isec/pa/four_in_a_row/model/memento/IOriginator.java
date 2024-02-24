package pt.isec.pa.four_in_a_row.model.memento;

import pt.isec.pa.four_in_a_row.model.FourInARowData;

import java.util.ArrayDeque;
import java.util.Deque;

public interface IOriginator {
    IMemento save();
    void restore(IMemento memento);
}

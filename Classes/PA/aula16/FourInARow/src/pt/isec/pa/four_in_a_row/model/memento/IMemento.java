package pt.isec.pa.four_in_a_row.model.memento;

public interface IMemento {
    default Object getSnapshot() { return null; }
}

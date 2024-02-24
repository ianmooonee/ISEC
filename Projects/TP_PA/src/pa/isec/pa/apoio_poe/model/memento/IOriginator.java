package pa.isec.pa.apoio_poe.model.memento;

public interface IOriginator {
    IMemento saveMemento();
    void restore(IMemento memento);
}

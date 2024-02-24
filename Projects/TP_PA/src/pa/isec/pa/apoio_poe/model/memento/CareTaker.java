package pa.isec.pa.apoio_poe.model.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class CareTaker {
    private final IOriginator originator;
    Deque<IMemento> history;
    Deque<IMemento> redoHist;

    public CareTaker(IOriginator originator){
        this.originator = originator;
        history = new ArrayDeque<>();
        redoHist = new ArrayDeque<>();
    }

    public void saveMementoCT() {
        redoHist.clear();
        history.push(originator.saveMemento());
    }

    public void undo() {
        if (history.isEmpty())
            return;
        redoHist.push(originator.saveMemento());
        originator.restore(history.pop());
    }

    public void redo() {
        if (redoHist.isEmpty())
            return;
        history.push(originator.saveMemento());
        originator.restore(redoHist.pop());
    }

    public void reset() {
        history.clear();
        redoHist.clear();
    }

    public boolean hasUndo() {
        return !history.isEmpty();
    }

    public boolean hasRedo() {
        return !redoHist.isEmpty();
    }

}

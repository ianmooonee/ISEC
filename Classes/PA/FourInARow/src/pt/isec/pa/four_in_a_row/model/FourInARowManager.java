package pt.isec.pa.four_in_a_row.model;

import pt.isec.pa.four_in_a_row.model.data.FourInARowData;
import pt.isec.pa.four_in_a_row.model.data.Piece;
import pt.isec.pa.four_in_a_row.model.memento.CareTaker;
import pt.isec.pa.four_in_a_row.model.memento.IMemento;

public class FourInARowManager {
    FourInARowData game;
    CareTaker careTaker;

    public FourInARowManager() {
        game = new FourInARowData();
        careTaker = new CareTaker(game);
    }

    public void init() {
        game.init();
    }

    public Piece getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public boolean play(int column) {
        switch (column) {
            case -1 -> {
                undo();
                return true;
            }
            case -2 -> {
                redo();
                return true;
            }
            default -> {
                return game.play(column);
            }
        }
    }

    @Override
    public String toString() {
        return game.toString();
    }

    public boolean isFull() {
        return game.isFull();
    }

    public Piece checkWinner() {
        return game.checkWinner();
    }

    public IMemento save() {
        return game.save();
    }

    public void restore(IMemento memento) {
        game.restore(memento);
    }

    public void undo(){
        careTaker.undo();
    }

    public void redo(){
        careTaker.redo();
    }

    public void hasUndo(){
        careTaker.hasUndo();
    }

    public void hasRedo(){
        careTaker.hasRedo();
    }
}

package pt.isec.pa.four_in_a_row.model;

import pt.isec.pa.four_in_a_row.model.memento.CareTaker;
import pt.isec.pa.four_in_a_row.model.memento.IMemento;
import pt.isec.pa.four_in_a_row.model.memento.IOriginator;
import pt.isec.pa.four_in_a_row.model.memento.Memento;

import java.io.Serializable;

public class FourInARowManager implements Serializable, IOriginator {
    FourInARowData game;
    CareTaker careTaker;

    public FourInARowManager(){
        game=new FourInARowData();
        careTaker=new CareTaker(game);
    }

    public void init(){
        game.init();
        careTaker.reset();
    }

    public Piece getCurrentPlayer() {
        return game.currentPlayer;
    }

    public boolean play(int column){
        careTaker.save();
        return game.play(column);
    }

    public boolean isFull() {
        return game.isFull();
    }

    public Piece checkWinner() {
        return game.checkWinner();
    }

    @Override
    public String toString() {
        return game.toString();
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

    @Override
    public IMemento save() {
        return game.save();
    }

    @Override
    public void restore(IMemento memento) {
        game.restore(memento);
    }
}

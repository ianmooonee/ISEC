package model.command;

public interface ICommand {
    boolean execute();
    boolean undo();
}

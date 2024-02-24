package model.command;

import model.data.ShoppingList;

public class RemoveProductCommand extends CommandAdapter {
    private final String nome;
    private final double qt;

    public RemoveProductCommand(ShoppingList receiver, String nome, double qt) {
        super(receiver);
        this.nome=nome;
        this.qt=qt;
    }

    @Override
    public boolean execute() {
        return receiver.removeProduct(nome, qt);
    }

    @Override
    public boolean undo() {
        return receiver.addProduct(nome, qt);
    }
}

package model.command;

import model.data.ShoppingList;

public class AddProductCommand  extends CommandAdapter{
    private final String nome;
    private final double qt;

    public AddProductCommand(ShoppingList receiver, String nome, double qt) {
        super(receiver);
        this.nome=nome;
        this.qt=qt;
    }

    @Override
    public boolean execute() {
        return receiver.addProduct(nome, qt);
    }

    @Override
    public boolean undo() {
        return receiver.removeProduct(nome, qt);
    }
}

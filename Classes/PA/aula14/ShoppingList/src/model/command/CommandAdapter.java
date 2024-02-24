package model.command;

import model.data.ShoppingList;

abstract class CommandAdapter implements ICommand {
    protected ShoppingList receiver;
    protected CommandAdapter(ShoppingList receiver) {
        this.receiver = receiver;
    }

}

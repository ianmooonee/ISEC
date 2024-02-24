import model.ShoppingListManager;
import ui.ShoppingListUI;

public class Main {
    public static void main(String[] args) {
        ShoppingListManager sm = new ShoppingListManager();
        ShoppingListUI ui = new ShoppingListUI(sm);
        ui.start();
    }
}
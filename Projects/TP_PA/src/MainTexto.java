import pa.isec.pa.apoio_poe.model.ManagerUI;
import pa.isec.pa.apoio_poe.model.fsm.Context;
import pa.isec.pa.apoio_poe.ui.text.UI;

public class MainTexto {
    public static void main(String[] args) {
        ManagerUI managerUI=new ManagerUI();
        UI ui = new UI(managerUI);
        ui.start();
    }

}

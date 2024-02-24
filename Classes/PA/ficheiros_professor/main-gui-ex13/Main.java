package pa.aula7.ex13;

import pa.aula7.ex13.model.LibraryWithList;
import pa.aula7.ex13.ui.LibraryUI;

public class Main {
    public static void main(String[] args) {
        LibraryWithList lib = new LibraryWithList("DEIS-ISEC - ArrayList");
        LibraryUI libUi = new LibraryUI(lib);
        libUi.start();
    }
}
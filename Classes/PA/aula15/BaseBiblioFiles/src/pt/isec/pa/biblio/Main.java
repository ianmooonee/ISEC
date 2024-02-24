package pt.isec.pa.biblio;

import pt.isec.pa.biblio.model.library.Library;
import pt.isec.pa.biblio.model.library.LibraryManager;
import pt.isec.pa.biblio.ui.LibraryUI;

public class Main {
    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager(new Library("DEIS-ISEC"));
        LibraryUI libui = new LibraryUI(lib);
        libui.start();
    }
}
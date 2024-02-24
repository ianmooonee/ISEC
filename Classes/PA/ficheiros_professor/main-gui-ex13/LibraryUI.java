package pa.aula7.ex13.ui;

import pa.aula7.ex13.model.Book;
import pa.aula7.ex13.model.ILibrary;
import pa.aula7.ex13.model.LibraryWithList;
import pa.utils.PAInput;

import java.util.ArrayList;

public class LibraryUI {
    ILibrary lib;

    public LibraryUI(ILibrary lib) {
        this.lib = lib;
    }

    void addBook() {
        String title = PAInput.readString("Book title: ",false);
        String author;
        ArrayList<String> authors = new ArrayList<>();

        do {
            author = PAInput.readString("Name of one author [\'exit\' to finish]: ",false);
            if (author!=null && !author.equalsIgnoreCase("exit"))
                authors.add(author);
        } while (!author.equalsIgnoreCase("exit"));

        if (authors.isEmpty())
            authors.add("Unknown author");

        lib.addBook(title,authors);
    }

    void findBook() {
        int bookId = PAInput.readInt("ID of the book to search: ");

        Book book = lib.getBook(bookId);

        if (book == null)
            System.out.println("Book not found");
        else
            System.out.println("Book found: " + book);
    }

    void removeBook() {
        int codigo = PAInput.readInt("ID of the book to remove: ");

        if(!lib.removeBook(codigo))
            System.out.println("Book not found");
        else
            System.out.println("Book deleted");
    }

    public void start() {
        while (true) {
            switch (PAInput.chooseOption("Library Manager - " + lib.getName(),
                    "Add new book","Search book","Remove book","Show books",
                    "Quit")) {
                case 1:
                    addBook();
                    break;
                case 2:
                    findBook();
                    break;
                case 3:
                    removeBook();
                    break;
                case 4:
                    System.out.println(lib.toString());
                    break;
                case 5:
                    return;
            }
        }
    }

}

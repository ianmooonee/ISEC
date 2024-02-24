package pt.isec.pa.biblio.model.library;

import pt.isec.pa.biblio.model.books.Book;
import pt.isec.pa.biblio.model.books.OldBook;
import pt.isec.pa.biblio.model.books.RecentBook;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    static final String FILENAME = "biblio.dat";
    Library lib;

    //proxy
    public LibraryManager(Library lib) {
        this.lib = lib;
    }

    public String getName() {
        return lib.getName();
    }

    public void setName(String name) {
        lib.setName(name);
    }

    public int addBook(Book book) {
        return lib.addBook(book);
    }

    public Book findBook(int id) {
        return lib.findBook(id);
    }

    public Book findBook2(int id) {
        return lib.findBook2(id);
    }

    public boolean removeBook(int id) {
        return lib.removeBook(id);
    }

    @Override
    public String toString() {
        return lib.toString();
    }

    public String toStringOtherOrder() {
        return lib.toStringOtherOrder();
    }
    //end proxy

    public boolean addBookFromFile(String filename){
        try(Scanner sc=new Scanner(new FileReader(filename))) {
                String type = sc.nextLine();
                String title = sc.nextLine();
                String strAuthors = sc.nextLine();
                List<String> authors = Arrays.asList(strAuthors.split(":"));

                if(authors.size()<1 || type.isBlank() || title.isBlank())
                    return false;

                switch (type) {
                    case "Old" -> {
                        int nrcopies = sc.nextInt();
                        lib.addBook(new OldBook(title, authors, nrcopies));
                    }
                    case "Recent" -> {
                        String isbn = sc.nextLine();
                        double cost=sc.nextDouble();
                        lib.addBook(new RecentBook(title, authors, isbn, cost));
                    }
                }
        }catch (Exception e){
            System.err.println("Error adding book from file;");
            return false;
        }
     return true;
    }

    public void exportToText(String filename) throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("Library name: " + lib.getName());
            for (Book b : lib.books) {
                pw.println(b.toString());
            }
        } catch (IOException e){
            System.err.println("Erro no acesso ao ficheiro.");
            throw e; //vai lançar a exceção da UI
        }
    }

    public boolean load(){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILENAME))){
            lib=(Library) ois.readObject();
        }catch(Exception e){
            System.err.println("Erro a ler data!");
            return false;
        }

        return true;
    }

    public boolean save(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILENAME))){
            oos.writeObject(lib);
            oos.flush();
        }catch(IOException e){
            System.err.println("Erro a guardar dados!");
            return false;
        }

        return true;
    }
}

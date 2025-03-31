package HW.eShop;
import java.util.ArrayList;

public abstract class Book {
    protected static int isbn;
    protected static String title;
    protected static double price;
    protected static String author;

    static ArrayList<Book> books = new ArrayList<Book>();


    public Book(int isbn, String title, double price, String author) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
    }


    public abstract void addBook();

    public static void displayProducts(){

        for (Book book : books){
            System.out.println(book);
        }
    }

    public String toString() {
        return  isbn + "\t" + title + "\t" + price + "\t" + author + "\t";
    }


}

package HW.eShop;

import java.util.Scanner;

public class Printed extends Book {
    protected String type; //hardcover or paperback
    protected int pages;
    protected String publisher;
    protected int availableCopies;


    public Printed(int isbn, String title, double price, String author,
                   String type, int pages, String publisher, int availableCopies) {
        super(isbn, title, price, author);
        this.type = type;
        this.pages = pages;
        this.publisher = publisher;
        this.availableCopies = availableCopies;

    }

    @Override
    public void addBook() {
        Scanner sc = new Scanner(System.in);
        isbn++;
        System.out.println("Enter Book Title: ");
        title = sc.nextLine();
        System.out.println("Enter Book Price: ");
        price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter Book Author: ");
        author = sc.nextLine();
        System.out.println("Enter Book Type: ");
        type = sc.nextLine();
        System.out.println("Enter Book Pages: ");
        pages = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Book Publisher: ");
        publisher = sc.nextLine();
        System.out.println("Enter Book Available Copies: ");
        availableCopies = sc.nextInt();

        books.add(this);
    }

    public String toString() {
        return super.toString() + type + "\t" + pages + "\t" + publisher + "\t" + availableCopies;
    }

}













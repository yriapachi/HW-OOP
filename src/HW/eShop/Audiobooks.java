package HW.eShop;

import java.util.Scanner;

public class Audiobooks extends Book {
    protected static double duration;
    protected static String narrator;

    public Audiobooks(int isbn, String title, double price, String author,
                      double duration, String narrator) {

        super(isbn, title, price, author);
        this.duration = duration;
        this.narrator = narrator;

    }

    @Override
    public void addBook() {
        Scanner sc = new Scanner(System.in);
        isbn++;
        System.out.println("Enter book title: ");
        title = sc.nextLine();
        System.out.println("Enter book price: ");
        price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter book author: ");
        author = sc.nextLine();
        System.out.println("Enter duration: ");
        duration = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter narrator: ");
        narrator = sc.nextLine();

        books.add(this);

    }

    public String toString(){
        return super.toString() + "\t" + duration + "\t" + narrator;
    }


}

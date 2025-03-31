package HW.eShop;

import java.util.Scanner;

public class Digital extends Book{
    protected static double size;

    public Digital(int isbn, String title, double price, String author, double size) {
        super(isbn, title, price, author);
        this.size = size;
    }

    @Override
    public void addBook(){
        Scanner sc = new Scanner(System.in);
        isbn++;
        System.out.println("Enter book title: ");
        title = sc.nextLine();
        System.out.println("Enter book price: ");
        price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter book author: ");
        author = sc.nextLine();
        System.out.println("Enter book size: ");
        size = sc.nextDouble();

        books.add(this);
    }

    public String toString() {
        return super.toString() + size;
    }



}

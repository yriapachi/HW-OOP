package HW.eShop;

import java.util.Scanner;


public class BookStore{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book printed = new Printed(1, "aaa", 0.0, "a", "aa", 0, "a", 0);
        Book digital = new Digital(2, "bbb", 0.0, "b", 0.0);
        Book audiobooks = new Audiobooks(3, "ccc", 0.0, "c", 0.0, "c");


        char ans;
        String typeAnswer;

        do {
            System.out.println("Add Book? (y/n)");
            ans = sc.next().charAt(0);
            sc.nextLine();
            if (ans == 'y') {
                System.out.println("Enter the type of book: \na) Printed, \nb) Digital, \nc) Audiobooks: ");
                typeAnswer = sc.nextLine();

                switch (typeAnswer) {
                    case "a":
                        printed.addBook();
                        break;
                    case "b":
                        digital.addBook();
                        break;
                    case "c":
                        audiobooks.addBook();
                        break;
                    default:
                        System.out.println("Invalid type");
                        break;

                }
            }
        }while (ans == 'y');

        Book.displayProducts();
    }


    }

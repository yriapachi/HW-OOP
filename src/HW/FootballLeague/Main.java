package HW.FootballLeague;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("MENU");
            System.out.println("1. Add Game");
            System.out.println("2. Team Performance");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Game.addGame();
                    break;
                case 2:
                    League.league();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }while (true);
    }
}


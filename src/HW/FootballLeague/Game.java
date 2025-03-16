package HW.FootballLeague;

import java.util.ArrayList;
import java.util.Scanner;

public class Game extends ArrayList<Game> {
    private static String homeTeam;
    private static String awayTeam;
    private static int homeScore;
    private static int awayScore;

    public static ArrayList<Game> games = new ArrayList<>();


    public Game(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }


    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }


    public static void addGame() {
        char ans = 'Y';

        Scanner sc = new Scanner(System.in);

        ArrayList<Game> games = new ArrayList<Game>();

        do {

            System.out.println("Enter the home team: ");
            String homeTeam = sc.nextLine();
            System.out.println("Enter the away team: ");
            String awayTeam = sc.nextLine();
            System.out.println("Enter the home score: ");
            int homeScore = sc.nextInt();
            System.out.println("Enter the away score: ");
            int awayScore = sc.nextInt();

            games.add(new Game(homeTeam, awayTeam, homeScore, awayScore));


            System.out.println("Add another game? (Y/N)");
            ans = sc.next().charAt(0);
            sc.nextLine();

        } while (ans == 'Y');


    }

}
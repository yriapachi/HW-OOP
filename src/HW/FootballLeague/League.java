package HW.FootballLeague;

import java.util.Scanner;
import java.util.ArrayList;

public class League {


    public static void league() {

        Scanner sc = new Scanner(System.in);
        ArrayList<Game> games = new ArrayList<>();

        int countWins = 0;
        int countLoses = 0;
        int countDraws = 0;

        System.out.println("Give team name: ");
        String teamName = sc.nextLine();

        if (Game.games !=null) {

            for (Game game : Game.games) {
                if (game.getHomeTeam().equals(teamName)) {
                    if (game.getHomeScore() > game.getAwayScore()) {
                        countWins++;
                    } else if (game.getHomeScore() < game.getAwayScore()) {
                        countLoses++;
                    } else {
                        countDraws++;
                    }
                } else if (game.getAwayTeam().equals(teamName)) {
                    if (game.getAwayScore() > game.getHomeScore()) {
                        countWins++;
                    } else if (game.getAwayScore() < game.getHomeScore()) {
                        countLoses++;
                    } else {
                        countDraws++;
                    }
                }
            }
        }else{
            System.out.println("No games found");
        }

        System.out.println(teamName + " has " + countWins + " wins.");
        System.out.println(teamName + " has " + countLoses + " loses.");
        System.out.println(teamName + " has " + countDraws + " draws.");
    }

    public static void main(String[] args) {

        Game.addGame();
        league();
    }
}

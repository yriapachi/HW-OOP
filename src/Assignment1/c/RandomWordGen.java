package Assignment1.c;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RandomWordGen {
    public static String getWord() {

        String apiUrl = "https://random-word-api.vercel.app/api?words=1";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            String response = scanner.hasNextLine() ? scanner.nextLine() : "";

            return response.replace("[", "").replace("]", "").replace("/", "").replace("\"", "");


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static String secretWord() {
        char[] word = getWord().toCharArray();
        if(word == null || word.length == 0) {
            return "";
        }
        for (int i = 1; i < word.length - 1; i++) {
            word[i] = '-';
        }
        return String.valueOf(word);

    }
}

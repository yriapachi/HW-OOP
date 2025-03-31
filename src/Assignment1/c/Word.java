package Assignment1.c;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Word {

    public static void main(String[] args) {
        try {
            // The API URL to fetch a random word
            String url = "https://random-word-api.herokuapp.com/word?number=1";

            // Create an HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful
            if (response.statusCode() == 200) {
                // Extract the random word (removes JSON array brackets and quotes)
                String word = response.body().replaceAll("[\\[\\]\"]", "");
                System.out.println("Random Word: " + word);
            } else {
                System.err.println("Error: Unable to fetch word. HTTP Code: " + response.statusCode());
            }
        } catch (InterruptedException e) {
            System.err.println("Request was interrupted: " + e.getMessage());
        } catch (java.io.IOException e) {
            System.err.println("Error with the HTTP request: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}
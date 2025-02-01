import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class URLShortener {
    private static final String BASE_URL = "http://short.ly/";
    private static Map<String, String> urlMap = new HashMap<>();
    
    // Method to shorten URL
    public String shortenURL(String longURL) {
        if (longURL == null || longURL.isEmpty()) {
            return "Error: URL cannot be empty.";
        }
        if (!isValidURL(longURL)) {
            return "Error: Invalid URL format.";
        }

        String shortURL = generateUniqueID(longURL);
        urlMap.put(shortURL, longURL);
        return BASE_URL + shortURL;
    }

    // Method to expand URL
    public String expandURL(String shortURL) {
        if (shortURL == null || shortURL.isEmpty()) {
            return "Error: Short URL cannot be empty.";
        }

        String key = shortURL.replace(BASE_URL, "");
        String originalURL = urlMap.get(key);
        if (originalURL == null) {
            return "Error: Short URL not found.";
        }
        return originalURL;
    }

    // Method to generate a unique ID for short URL
    private String generateUniqueID(String longURL) {
        // Generate a hash of the URL and convert it to base 36 (alphanumeric string)
        String hash = Integer.toString(longURL.hashCode(), 36);
        // Limit the length to make it more manageable (e.g., 6 characters)
        return hash.length() > 6 ? hash.substring(0, 6) : hash; 
    }

    // Method to validate the URL format (simple check for "http://" or "https://")
    private boolean isValidURL(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLShortener shortener = new URLShortener();

        System.out.println("Welcome to the Link Shortener");
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Shorten a URL");
            System.out.println("2. Expand a short URL");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL to shorten: ");
                    String longURL = scanner.nextLine();
                    String shortURL = shortener.shortenURL(longURL);
                    System.out.println(shortURL);  // Print either the shortened URL or an error message
                    break;

                case 2:
                    System.out.print("Enter the short URL to expand: ");
                    String shortInput = scanner.nextLine();
                    String originalURL = shortener.expandURL(shortInput);
                    System.out.println(originalURL);  // Print either the original URL or an error message
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

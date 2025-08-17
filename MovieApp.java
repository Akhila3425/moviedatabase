package movie_database;
import java.util.ArrayList;
import java.util.Scanner;

class Movie {
    private String title;
    private String director;
    private int year;
    private String genre;

    public Movie(String title, String director, int year, String genre) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void display() {
        System.out.printf("🎬 %-20s | %-15s | %4d | %-10s%n", title, director, year, genre);
    }
}

// MovieDatabase class
class MovieDatabase {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void addMovie(Movie m) {
        movies.add(m);
        System.out.println("✅ Movie added successfully!");
    }

    public Movie searchMovie(String title) {
        for (Movie m : movies) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies in database yet!");
            return;
        }

        System.out.println("\n===== Movie List =====");
        System.out.printf("%-20s | %-15s | %-4s | %-10s%n", "Title", "Director", "Year", "Genre");
        System.out.println("-----------------------------------------------------------");
        for (Movie m : movies) {
            m.display();
        }
    }
}

public class MovieApp {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieDatabase db = new MovieDatabase();
        int choice;

        do {
            System.out.println("\n===== Movie Database Menu =====");
            System.out.println("1. Add Movie");
            System.out.println("2. Search Movie");
            System.out.println("3. Display All Movies");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("⚠️ Please enter a valid number (1-4).");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Director: ");
                    String director = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int year = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();
                    db.addMovie(new Movie(title, director, year, genre));
                    break;

                case 2:
                    System.out.print("Enter movie title to search: ");
                    String searchTitle = sc.nextLine();
                    Movie found = db.searchMovie(searchTitle);
                    if (found != null) {
                        System.out.println("✅ Found Movie:");
                        System.out.printf("%-20s | %-15s | %-4s | %-10s%n", "Title", "Director", "Year", "Genre");
                        System.out.println("-----------------------------------------------------------");
                        found.display();
                    } else {
                        System.out.println("❌ Movie not found!");
                    }
                    break;

                case 3:
                    db.displayMovies();
                    System.out.println("\nPress Enter to continue...");
                    sc.nextLine(); // pause so the console doesn't disappear
                    break;

                case 4:
                    System.out.println("Exiting... Bye!");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

}

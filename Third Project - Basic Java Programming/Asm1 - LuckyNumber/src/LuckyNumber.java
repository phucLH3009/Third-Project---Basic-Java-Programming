import java.util.Random;
import java.util.Scanner;
public class LuckyNumber {
    public static Scanner sc = new Scanner(System.in);
    public static Random r = new Random();
    public static int totalGames = 0;
    public static int totalGuess = 0;
    public static int bestGames = Integer.MAX_VALUE;
    public static void main(String[] args) {
        play();
        report();
    }
    public static void play() {
        boolean playing;
        do {
            int guess = 0;
            int count = 0;
            int randomNumber = r.nextInt(101);
            System.out.println("I'm thinking of a number between 0 and 100.........");
            while (guess != randomNumber) {
                System.out.print("Your guess? ");
                guess = sc.nextInt();
                if (guess > randomNumber) {
                    System.out.println("It's lower!");
                }
                if (guess < randomNumber) {
                    System.out.println("It's higher");
                }
                count++;
            }
            System.out.println("You got it right in " + count + " guesses!");
            totalGames++;
            totalGuess += count;
            if (count < bestGames) {
                bestGames = count;
            }
            System.out.println("Do you want to continue? ");
            String answer = sc.next();
            playing = answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("Y");
        } while (playing);
    }
    public static void report() {
        System.out.println("Overall results:");
        System.out.println("Total games \t = " + totalGames);
        System.out.println("Total guesses \t = " + totalGuess);
        System.out.println("Guesses/game \t = " + (double) totalGuess / totalGames);
        System.out.println("Best game \t = " + bestGames);
    }
}
import java.util.Scanner;

public class GamePlay {

    private static Person player;

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter your first name:");
        String firstName = scnr.next();

        System.out.println("Would you like to enter a last name? Yes or no");
        String answer = scnr.next();

        if (answer.equalsIgnoreCase("Yes")) {
            System.out.println("Please enter your last name");
            String lastName = scnr.next();

            player = new Person(firstName, lastName);
        } else {
            player = new Person(firstName);
        }

        Numbers numbers = new Numbers();
        numbers.generateNumber();

        int guess;
        do {
            System.out.println(player + " enter a guess:");
            guess = scnr.nextInt();
        } while (numbers.compareNumber(guess) == false);
    }
}



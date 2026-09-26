import java.util.Random;
import java.util.Scanner;

public class Turn {

    private Scanner scnr = new Scanner(System.in);

    public boolean takeTurn(Players player, Hosts host) {
        Phrases phrase = new Phrases();

        System.out.println("Current Phrase:" + phrase.getPlayingPhrase());
        System.out.println(host.getFirstName() + ": " + player.getFirstName() + ", please enter your character.");

        String userGuess = scnr.next();

        boolean won = false;
        try {
            // Fixed: Called findLetters() as an instance method on 'phrase', passing userGuess
            won = phrase.findLetters(userGuess);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Random rand = new Random();
        boolean moneyPrize = rand.nextBoolean();
        int amountChange = 0;

        if (moneyPrize) {
            Money money = new Money();
            amountChange = money.displayWinnings(player, won);
        } else {
            Physical physical = new Physical();
            amountChange = physical.displayWinnings(player, won);
        }

        player.setCurrentAmountofMoney(player.getCurrentAmountofMoney() + amountChange);

        if (won) {
            System.out.println("Congratulations! " + player.getFirstName() + ", you are the winner!");
        }

        System.out.println(player);
        return won;
    }
}


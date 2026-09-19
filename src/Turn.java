import java.util.Random;
import java.util.Scanner;

public class Turn {

    private Scanner scnr = new Scanner(System.in);

    public boolean takeTurn(Players player, Hosts host) {
        Numbers numbers = new Numbers();

        System.out.println(host.getFirstName() + ": " + player.getFirstName() + ", please enter your guess between 0 to 100 ");

        int guess = scnr.nextInt();
        boolean won = numbers.compareNumber(guess);
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

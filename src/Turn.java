import java.util.Scanner;

public class Turn {
    private static final int winningAmount = 1000;
    private static final int losingAmount = 100;
    private Scanner scnr = new Scanner(System.in);

    public boolean takeTurn(Players player, Hosts host) {
        Numbers numbers = new Numbers();

        System.out.println(host.getFirstName() + ": " + player.getFirstName() + ", please enter your guess between 0 to 100 ");

        int guess = scnr.nextInt();

        if (numbers.compareNumber(guess)) {
            player.setCurrentAmountofMoney(player.getCurrentAmountofMoney() + winningAmount);
            System.out.println("congratulations! " + player.getFirstName() + " you are the winner!");
            System.out.println(player);
            return true;
        } else {
            player.setCurrentAmountofMoney(player.getCurrentAmountofMoney() - losingAmount);
            System.out.println(player);
            return false;
        }
    }
}

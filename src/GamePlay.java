import java.util.Scanner;

public class GamePlay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a host name:");
        String hostName = scanner.nextLine();
        Hosts host = new Hosts(hostName);


        System.out.println("Enter player name:");
        String playerName = scanner.nextLine();
        Players player = new Players(playerName);

        Turn turn = new Turn();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")){
            host.randomizeNum();

            boolean correct = false;
            while(!correct){
                correct = turn.takeTurn(player, host);
            }
            System.out.println("Do you want to keep playing? (yes/no):");
            playAgain = scanner.nextLine();
        }
        System.out.println("Thanks for playing!");
    }
}

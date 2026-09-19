import java.util.Scanner;

public class GamePlay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a host name:");
        String hostName = scanner.nextLine();
        Hosts host = new Hosts(hostName);


        Players[] currentPlayers = new Players[3];


        for (int i = 0; i <3; i = i + 1){
            System.out.println("Please enter player " + (i+1) + ":");
            String playerName = scanner.nextLine();
            currentPlayers[i] = new Players(playerName);
        }
        Turn turn = new Turn();
        String playAgain = "yes";

        while (playAgain.equalsIgnoreCase("yes")){
            host.randomizeNum();
            int playerIndex = 0;
            boolean correct = false;
            while(!correct){
                correct = turn.takeTurn(currentPlayers[playerIndex], host);
                playerIndex = (playerIndex + 1) % currentPlayers.length;
            }
            System.out.println("Do you want to keep playing? (yes/no):");
            playAgain = scanner.nextLine();
        }
        System.out.println("Thanks for playing!");
    }
}

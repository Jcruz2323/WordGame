import java.util.Scanner;

public class Hosts extends Person{
    public Hosts(String firstName) {
        super(firstName);

        enterGamePhrase();
    }

    public void enterGamePhrase() {
        Scanner hostPhrase = new Scanner(System.in);
        System.out.println("Host, please enter a phrase:");
        Phrases.gamePhrase = hostPhrase.nextLine();
    }
}

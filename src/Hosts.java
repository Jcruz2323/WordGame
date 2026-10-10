public class Hosts extends Person {
    private String gamePhrase;

    public Hosts(String firstName, String gamePhrase) {
        super(firstName);
        this.gamePhrase = gamePhrase;
    }

    public String getGamePhrase() {
        return gamePhrase;
    }

    public void setGamePhrase(String gamePhrase) {
        this.gamePhrase = gamePhrase;
    }

    public String getPlayingPhrase() {

        return gamePhrase;
    }

    public boolean checkGuess(String guess) {

        return gamePhrase.toLowerCase().contains(guess.toLowerCase());
    }

    public boolean isGameWon() {
        return false;
    }
}
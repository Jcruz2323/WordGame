public class Hosts extends Person {
    private String gamePhrase;
    private boolean gameWon;

    public Hosts(String firstName, String gamePhrase) {
        super(firstName);
        this.gamePhrase = gamePhrase;
        this.gameWon = false;
    }

    public String getGamePhrase() {
        return gamePhrase;
    }

    public void setGamePhrase(String gamePhrase) {
        this.gamePhrase = gamePhrase;
        this.gameWon = false;
    }

    public String getPlayingPhrase() {
        return gamePhrase;
    }

    public boolean checkGuess(String guess) {
        gameWon = gamePhrase != null
                && guess != null
                && gamePhrase.equalsIgnoreCase(guess);

        return gameWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
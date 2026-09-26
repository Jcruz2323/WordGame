import java.util.ArrayList;
import java.util.List;

public class Phrases {
    public static String gamePhrase;

    private String playingPhrase;

    public Phrases() {
        playingPhrase = underline();
    }

    private String underline() {
        String underlined = "";
        for (int i = 0; i < gamePhrase.length(); i++) {
            if (!Character.isWhitespace(gamePhrase.charAt(i))) {
                underlined += "_";
            } else {
                underlined += " ";
            }
        }

        return underlined;
    }

    public boolean findLetters(String letter) throws MultipleLettersException {
        if (letter.length() > 1) {
            throw new MultipleLettersException();
        }

        final List<String> characters = asCharacters();
        for (int i = 0; i < gamePhrase.length(); i++) {
            if (gamePhrase.substring(i, i + 1).equals(letter)) {
                characters.set(i, letter);
            }
        }

        playingPhrase = String.join("", characters);

         return !playingPhrase.contains("_");
    }

    private List<String> asCharacters() {
        final List<String> characters = new ArrayList<>();
        for (int i = 0; i < playingPhrase.length(); i++) {
            characters.add(playingPhrase.substring(i, i + 1));
        }

        return characters;
    }
       public String getPlayingPhrase() {
           return playingPhrase;
       }
}

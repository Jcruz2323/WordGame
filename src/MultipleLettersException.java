public class MultipleLettersException extends Exception {

    @Override
    public String getMessage() {
        return "That doesn’t match, please try again!";
    }
}

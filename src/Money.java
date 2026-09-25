public class Money implements Award{
    private static final int winningAmount = 1000;
    private static final int losingAmount = 100;

    @Override
    public int displayWinnings(Players player, boolean isCorrect) {
        if(isCorrect){
            System.out.println(player.getFirstName() + " You have won!");
                    return winningAmount;
        } else{
            System.out.println(player.getFirstName() + " Sorry, you have lost!");
            return -losingAmount;
        }
    }
}

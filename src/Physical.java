import java.util.Random;

public class Physical implements Award{
    String[] prizes = {"Car", "House", "Boat", "Computer", "Trip for 2"};

    public int getRandomPrize(){
        Random prizeNum = new Random();
        return prizeNum.nextInt(5);
    }


    @Override
    public int displayWinnings(Players player, boolean isCorrect) {
        String prize = prizes[getRandomPrize()];

        if(isCorrect){
            System.out.println(player.getFirstName() + "You have won a " + prize + "!");
            return 0;
        } else{
            System.out.println(player.getFirstName() + "Sorry, you have lost a " + prize + "!");
            return 0;
        }
    }
}

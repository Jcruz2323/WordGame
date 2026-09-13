import java.util.Random;

public class Numbers {

    private static int randomNum;

    public int getRandomNum(){
        return randomNum;
    }

    public void setRandomNum(int randomNum){
        this.randomNum = randomNum;
    }

    public void generateNumber (){
        Random num = new Random();
        randomNum = num.nextInt(101);
    }

     public boolean compareNumber(int guess){
        if (guess == randomNum){
            System.out.println("congratulations, you guessed the correct number!");
            return true;
         } else if (guess > randomNum){
            System.out.println("That guess was too high ");
            return false;
        } else {
            System.out.print("I'm sorry, That guess was too low ");
            return false;
        }
     }
}

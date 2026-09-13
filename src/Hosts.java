public class Hosts extends Person{

    public Hosts(String firstName) {
        super(firstName);
    }
    public int randomizeNum(){
        Numbers random = new Numbers();
        random.generateNumber();

        return random.getRandomNum();
    }
}

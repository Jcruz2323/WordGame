public class Players extends Person {
    private int currentAmountOfMoney;

    public Players(String firstName) {
        super(firstName);
        this.currentAmountOfMoney = 1000;
    }

    public int getCurrentAmountOfMoney() {
        return currentAmountOfMoney;
    }

    public void setCurrentAmountOfMoney(int amountOfMoney) {
        this.currentAmountOfMoney = amountOfMoney;
    }

    public void addMoney(int amount) {
        this.currentAmountOfMoney += amount;
    }

    @Override
    public String toString() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String fullName = firstName + (lastName != null ? " " + lastName : "");
        return fullName + " has $" + this.currentAmountOfMoney + " remaining";
    }
}



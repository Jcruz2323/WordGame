public class Players extends Person {
    private int currentAmountofMoney;

    public int getCurrentAmountofMoney() {
        return currentAmountofMoney;
    }

    public void setCurrentAmountofMoney(int amountofMoney) {
        this.currentAmountofMoney = amountofMoney;
    }

    public Players (String firstName) {

        super(firstName);
        currentAmountofMoney = 1000;
    }

    @Override
    public String toString() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String fullName = firstName + " " + lastName;

        return fullName + "has $" + this.currentAmountofMoney + " remaining";
    }
}



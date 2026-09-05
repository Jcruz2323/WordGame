public class Person {

    private String firstName;
    private String lastName;

    public Person(String firstName) {
        this.firstName = firstName;
        this.lastName = "";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName() {
        this.lastName = lastName;
    }

    public String toString() {
        if (lastName.length() > 0) {
            return firstName + " " + lastName;
        } else {
            return firstName;
        }
    }
}
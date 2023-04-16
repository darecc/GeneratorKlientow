package dc;

public class Client {
    String firstName;
    String lastName;
    String pesel;
    Address adress;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public Address getAdress() {
        return adress;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    int clientId;

    public Client(String firstName, String lastName, String pesel, Address adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + pesel + " " + adress.toString();
    }
}

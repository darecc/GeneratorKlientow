package dc;

public class Address {
    String city;
    String code;

    @Override
    public String toString() {
        return city + " "  + code + " " + street;
    }

    public String getCity() {
        return city;
    }

    public String getCode() {
        return code;
    }

    public String getStreet() {
        return street;
    }

    String street;

    public Address(String city, String code, String street) {
        this.city = city;
        this.code = code;
        this.street = street;
    }
}

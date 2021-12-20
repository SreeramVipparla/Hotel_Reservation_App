package model;
import java.util.regex.Matcher;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
/**
 * @author-VipparlaSreeram
 */

    private String email;
    public String getEmail()     {
        return this.email;
    }
    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }

    private String lastName;
    public String getLastName() {
        return this.lastName;
    }

    private boolean Email_Verification(String email) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public Customer(final String firstName,final String lastName,final String email) {

        this.firstName = firstName;
        this.lastName = lastName;

        if(Email_Verification(email)) this.email = email;
        else throw new IllegalArgumentException();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) && lastName.equals(customer.lastName) && email.equals(customer.email);
    }

    @Override
    public String toString() {
        return "Customer: " + this.firstName + " " + this.lastName + " (" + this.email + ")";
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}

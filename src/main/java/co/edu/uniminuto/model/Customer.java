package co.edu.uniminuto.model;

public class Customer {

    private Long id;

    private String lastName;

    private String foreName;

    private String address;

    private char role;

    public Customer() {
        setRole(' ');
    }

    public Customer(Long id, String foreName, String lastName,String address, char role) {
        this.id = id;
        this.lastName = lastName;
        this.foreName = foreName;
        this.address = address;
        this.role = role;
    }

    public Customer(String foreName, String lastName, String address, char role) {
        this.lastName = lastName;
        this.foreName = foreName;
        this.address = address;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }


    public String getForeName() {
        return foreName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", foreName='" + foreName + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                '}';
    }
}

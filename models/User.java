package models;

public class User {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
    private String role;
    private String phoneNumber;

    // Constructors, getters, and setters
    public User() {}
    public User(int id, String email, String password, String firstname, String lastname, int age, String role, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) {this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) {this.lastname = lastname; }

    public int getAge() { return age; }
    public void setAge(int age) {this.age = age; }

    public String getRole() { return role; }
    public void setRole(String role) {this.role = role; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber; }

}

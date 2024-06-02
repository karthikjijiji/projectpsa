public class PropertyUserDTO {

    private Long id;
    private String firstName;
    private String lastname;
    private String username;
    private String userRole;
    private String email;
    private String password;

    // Constructors
    public PropertyUserDTO() {
    }

    public PropertyUserDTO(Long id, String firstName, String lastname, String username, String userRole, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastname = lastname;
        this.username = username;
        this.userRole = userRole;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    // You can generate these using your IDE or write them manually like below

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

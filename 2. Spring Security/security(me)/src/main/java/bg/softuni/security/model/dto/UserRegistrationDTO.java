package bg.softuni.security.model.dto;


public class UserRegistrationDTO {




//    @Size(min = 4, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;
//    @Size(min = 4, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;
//    @Email(message = "email is invalid")
    private String email;
//    @Size(min = 4, max = 40,message = "Password must be between 5 and 40 characters!")
    private String password;
    private String confirmPassword;


    public UserRegistrationDTO() {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password != null ? "[PROVIDED]" : "[null]" + '\'' +
                ", confirmPassword='" + confirmPassword != null ? "[PROVIDED]" : "[null]" + '\'' +
                '}';
    }
}

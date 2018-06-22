package XMLandSecurity.backend1.model.dto;


public class UserDto {


    private String username;

    private String name;

    private String surname;

    private String email;

    private String city;

    private String adress;

    private String number;

    public UserDto(String username, String name, String surname, String email, String city, String adress, String number) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.adress = adress;
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

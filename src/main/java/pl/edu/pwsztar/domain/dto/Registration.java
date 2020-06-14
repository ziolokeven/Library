package pl.edu.pwsztar.domain.dto;

public class Registration {

  private final String login;
  private final String password;
  private final String firstName;
  private final String lastName;
  private final String address;

  public Registration(String login, String password, String firstName, String lastName, String address) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }
}

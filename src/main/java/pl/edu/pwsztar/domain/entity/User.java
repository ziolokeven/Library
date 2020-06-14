package pl.edu.pwsztar.domain.entity;

import pl.edu.pwsztar.domain.dto.UserDto;
import pl.edu.pwsztar.exception.WrongPassword;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String login;

  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String address;

  @Column(name = "is_admin")
  private Boolean isAdmin = false;

  public User() {
  }

  public User(String login, String password, String firstName, String lastName, String address) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.isAdmin = false;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  public Boolean getAdmin() {
    return isAdmin;
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

  public void login(String password) {
    if (!this.password.equals(password)) {
      throw new WrongPassword();
    }
  }

  public UserDto mapToUserDto() {
    return new UserDto(id, login, isAdmin);
  }
}

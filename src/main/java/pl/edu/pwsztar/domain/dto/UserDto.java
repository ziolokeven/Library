package pl.edu.pwsztar.domain.dto;

public class UserDto {
  private final Long id;
  private final String login;
  private final boolean isAdmin;

  public UserDto(Long id, String login, boolean isAdmin) {
    this.id = id;
    this.login = login;
    this.isAdmin = isAdmin;
  }

  public Long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public boolean isAdmin() {
    return isAdmin;
  }
}

package pl.edu.pwsztar.service;

import pl.edu.pwsztar.domain.dto.Login;
import pl.edu.pwsztar.domain.dto.Registration;
import pl.edu.pwsztar.domain.dto.UserDto;

public interface UserService {

  UserDto register(Registration registration);

  UserDto login(Login login);

  UserDto getUser(String login);
}

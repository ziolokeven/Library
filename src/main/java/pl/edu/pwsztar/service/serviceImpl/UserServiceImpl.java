package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.dto.Login;
import pl.edu.pwsztar.domain.dto.Registration;
import pl.edu.pwsztar.domain.dto.UserDto;
import pl.edu.pwsztar.domain.entity.User;
import pl.edu.pwsztar.domain.repository.UserRepository;
import pl.edu.pwsztar.exception.UserAlreadyRegistered;
import pl.edu.pwsztar.exception.UserNotFound;
import pl.edu.pwsztar.exception.WrongLogin;
import pl.edu.pwsztar.service.UserService;

import java.util.Optional;

@Service
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto register(Registration registration) {
    Optional<User> user = userRepository.findByLogin(registration.getLogin());
    if (user.isPresent()) {
      throw new UserAlreadyRegistered();
    }
    User registeredUser = registerUser(registration);

    return login(new Login(registeredUser.getLogin(), registeredUser.getPassword()));
  }

  @Override
  public UserDto login(Login login) {
    Optional<User> user = userRepository.findByLogin(login.getLogin());
    if (user.isPresent()) {
      user.get().login(login.getPassword());
      return user.get().mapToUserDto();
    } else {
      throw new WrongLogin();
    }
  }

  @Override
  public UserDto getUser(String login) {
    User user = userRepository.findByLogin(login).orElseThrow(() -> new UserNotFound());
    return user.mapToUserDto();
  }

  private User registerUser(Registration registration) {
    User toSave = new User(
        registration.getLogin(), registration.getPassword(), registration.getFirstName(), registration.getLastName(), registration.getAddress()
    );
    return userRepository.save(toSave);
  }
}

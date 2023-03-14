package quiz.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import quiz.entity.user.UserDTO;

public interface UserService extends UserDetailsService {

    void create(UserDTO userDTO, boolean isAdmin);
}

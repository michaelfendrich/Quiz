package quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quiz.entity.user.UserDTO;
import quiz.entity.user.UserEntity;
import quiz.exception.DuplicateEmailException;
import quiz.exception.PasswordDoNotEqualException;
import quiz.repostitory.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(UserDTO userDTO, boolean isAdmin) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordDoNotEqualException();
        }
        UserEntity entity = new UserEntity();
        entity.setEmail(userDTO.getEmail());
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        entity.setAdmin(isAdmin);

        try {
            repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}

package one.transfinite.rms.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
    }

    public void addUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
        this.userRepository.deleteById(userId);
    }
}

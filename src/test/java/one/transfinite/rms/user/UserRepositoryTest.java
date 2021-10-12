package one.transfinite.rms.user;

import one.transfinite.rms.role.Role;
import one.transfinite.rms.utility.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void saveUserTest() {
        Role role = new Role();
		role.setName(RoleName.CUSTOMER.name());

		User user = new User();
		user.setName("customer");
		user.setEmail("customer@gmail.com");
		user.setPhone("7485963215");
		user.setPassword("pass");
		user.setRole(role);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getUserId()).isGreaterThan(0);
    }

    @Test
    void fetchUserByEmailTest() {
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());
        String email = "customer@gmail.com";

        User user = new User();
        user.setName("customer");
        user.setEmail(email);
        user.setPhone("7485963215");
        user.setPassword("pass");
        user.setRole(role);

        User savedUser = userRepository.save(user);
        Optional<User> fetchedUser = userRepository.findUserByEmail(email);

        assertThat(fetchedUser)
                .isPresent()
                .hasValueSatisfying(user1 -> assertThat(user1).isEqualTo(savedUser));
    }

    @Test
    void fetchUserByPhoneTest() {
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());
        String phone = "7485963215";

        User user = new User();
        user.setName("customer");
        user.setEmail("customer@gmail.com");
        user.setPhone(phone);
        user.setPassword("pass");
        user.setRole(role);

        User savedUser = userRepository.save(user);
        Optional<User> fetchedUser = userRepository.findUserByPhone(phone);

        assertThat(fetchedUser)
                .isPresent()
                .hasValueSatisfying(user1 -> assertThat(user1).isEqualTo(savedUser));
    }
}
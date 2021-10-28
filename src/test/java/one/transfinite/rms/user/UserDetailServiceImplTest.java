package one.transfinite.rms.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.role.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserDetailServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserDetailServiceImplTest {
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);
        String email = "jane.doe@example.org";

        User user = new User();
        user.setRole(role);
        user.setEmail(email);
        user.setPassword("password");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        when(this.userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
        assertSame(user, this.userDetailServiceImpl.loadUserByUsername(email));
        verify(this.userRepository).findUserByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userRepository.findUserByEmail((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(UsernameNotFoundException.class,
                () -> this.userDetailServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(this.userRepository).findUserByEmail((String) any());
    }
}


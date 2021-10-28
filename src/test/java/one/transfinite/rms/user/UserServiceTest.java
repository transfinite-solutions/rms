package one.transfinite.rms.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.utility.EmailValidator;
import one.transfinite.rms.utility.RoleName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class, EmailValidator.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private EmailValidator emailValidator;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Test
//    void saveUserTest() {
//        Role role = new Role();
//        role.setName(RoleName.CUSTOMER.name());
//
//        User user = new User();
//        user.setName("customer");
//        user.setEmail("customer@gmail.com");
//        user.setPhone("7485963215");
//        user.setPassword("pass");
//        user.setRole(role);
//
//        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
//        userService.addUser(user);
//        verify(userRepository).save(userArgumentCaptor.capture());
//        User captorValue = userArgumentCaptor.getValue();
//
//        assertThat(captorValue).isEqualTo(user);
//    }

//    @Test
//    void saveUserExceptionTest() {
//        Role role = new Role();
//        role.setName(RoleName.CUSTOMER.name());
//
//        User user = new User();
//        user.setName("customer");
//        user.setEmail("customer@gmail.com");
//        user.setPhone("7485963215");
//        user.setPassword("pass");
//        user.setRole(role);
//
//    }

    @Test
    void getAllUserTest() {
        List<User> userList = new ArrayList<>();
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());

        User user = new User();
        user.setName("customer");
        user.setEmail("customer@gmail.com");
        user.setPhone("7485963215");
        user.setPassword("pass");
        user.setRole(role);
        userList.add(user);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> allUsers = userService.getAllUsers();
        assertThat(allUsers).isEqualTo(userList);
    }

    @Test
    void itShouldGetUserById() {
        long userId = 1L;
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());

        User user = new User();
        user.setUserId(userId);
        user.setName("customer");
        user.setEmail("customer@gmail.com");
        user.setPhone("7485963215");
        user.setPassword("pass");
        user.setRole(role);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User userById = userService.getUserById(userId);
        assertThat(userById).isEqualTo(user);
    }

    @Test
    void itShouldNotGetUserById() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(null));
        try {
            User userById = userService.getUserById(userId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("User does not exists");
        }
    }

    @Test
    void testGetUserByEmail() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findUserByEmail((String) any())).thenReturn(ofResult);
        assertSame(user, this.userService.getUserByEmail("jane.doe@example.org"));
        verify(this.userRepository).findUserByEmail((String) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testGetUserByEmail2() {
        when(this.userRepository.findUserByEmail((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userService.getUserByEmail("jane.doe@example.org"));
        verify(this.userRepository).findUserByEmail((String) any());
    }

    @Test
    void testGetUserByPhone() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findUserByPhone((String) any())).thenReturn(ofResult);
        assertSame(user, this.userService.getUserByPhone("4105551212"));
        verify(this.userRepository).findUserByPhone((String) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testGetUserByPhone2() {
        when(this.userRepository.findUserByPhone((String) any())).thenReturn(Optional.<User>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userService.getUserByPhone("4105551212"));
        verify(this.userRepository).findUserByPhone((String) any());
    }

    @Test
    void testAddAddress() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        this.userService.addAddress(123L, new ArrayList<Address>());
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testAddAddress2() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userService.addAddress(123L, new ArrayList<Address>()));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testAddAddress3() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user2);
        address.setTag("Tag");
        address.setState("MD");

        ArrayList<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        this.userService.addAddress(123L, addressList);
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testAddAddress4() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user2);
        address.setTag("Tag");
        address.setState("MD");

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user3);
        address1.setTag("Tag");
        address1.setState("MD");

        ArrayList<Address> addressList = new ArrayList<Address>();
        addressList.add(address1);
        addressList.add(address);
        this.userService.addAddress(123L, addressList);
        verify(this.userRepository).findById((Long) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteUser() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);
        Optional<User> ofResult = Optional.<User>of(user);
        doNothing().when(this.userRepository).deleteById((Long) any());
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        this.userService.deleteUser(123L);
        verify(this.userRepository).deleteById((Long) any());
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteUser2() {
        doNothing().when(this.userRepository).deleteById((Long) any());
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.<User>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.userService.deleteUser(123L));
        verify(this.userRepository).findById((Long) any());
    }

}
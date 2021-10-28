package one.transfinite.rms.user;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.execption.ApiBadRequestException;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.jwt.JwtUtils;
import one.transfinite.rms.utility.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailValidator emailValidator;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
    }

    public User getUserByPhone(String phone) {
        return this.userRepository.findUserByPhone(phone).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
    }

    public User getUserByToken(String token) {
        User user = this.userRepository.findUserByEmail(jwtUtils.extractUsername(token)).orElseThrow(() -> new ApiBadRequestException("Invalid token"));
        return user;
    }

    public void addUser(User user) {
        User searchedUserByEmail = userRepository.findUserByEmail(user.getEmail()).orElse(null);
        User searchedUserByPhone = userRepository.findUserByPhone(user.getPhone()).orElse(null);

        if (!emailValidator.test(user.getEmail())){
            throw new ApiBadRequestException(user.getEmail() + " is not valid");
        }
        if (searchedUserByEmail != null){
            throw new ApiBadRequestException(user.getEmail() + " is already taken");
        }
        if (searchedUserByPhone != null) {
            throw new ApiBadRequestException("Phone already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public void addAddress(Long userId, List<Address> addresses) {
        List<Address> addressList;
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
        addressList = user.getAddresses();

        addresses.forEach(address -> {
            address.setUser(user);
            addressList.add(address);
        });
        System.out.println(addresses);


        user.setAddresses(addressList);
        this.userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.findById(user.getUserId()).orElseThrow(() -> new ApiBadRequestException("User does not exists"));

        if (emailValidator.test(user.getEmail())){
            throw new ApiBadRequestException(user.getEmail() + " is not valid");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User does not exists"));
        this.userRepository.deleteById(userId);
    }
}

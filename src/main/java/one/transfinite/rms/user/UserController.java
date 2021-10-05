package one.transfinite.rms.user;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.role.RoleService;
import one.transfinite.rms.utility.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return this.userService.getUserById(userId);
    }

    @PostMapping("/customer")
    public void addCustomer(@Valid @RequestBody User user) {
        System.out.println(user);
        Role role = roleService.getRoleByName(RoleName.CUSTOMER.name());
        user.setRole(role);
        this.userService.addUser(user);
    }

    @PostMapping("/vendor")
    public void addVendor(@Valid @RequestBody User user) {
        Role role = roleService.getRoleByName(RoleName.VENDOR.name());
        user.setRole(role);
        this.userService.addUser(user);
    }

    @PostMapping("/{userId}/address")
    public void addAddress(@PathVariable("userId") Long userId, @RequestBody List<Address> addresses) throws Exception{
        this.userService.addAddress(userId, addresses);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
    }
}

package one.transfinite.rms.authentication;

import one.transfinite.rms.jwt.JwtUtils;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.role.RoleService;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserDetailServiceImpl;
import one.transfinite.rms.user.UserService;
import one.transfinite.rms.utility.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/authenticate")
    private ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest);
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User does not found");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    private ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
        System.out.println(user);
        Role role = roleService.getRoleByName(RoleName.CUSTOMER.name());
        user.setRole(role);
        this.userService.addUser(user);

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);

        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/get-user")
    private ResponseEntity<?> getUser(HttpServletRequest req) {
        String TOKEN = req.getHeader("Authorization").replace("BEARER ", "");
        return ResponseEntity.ok(userService.getUserByToken(TOKEN));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("User Disabled");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}

package one.transfinite.rms.role;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    void itShouldGetRoleByName() {
        String roleName = "role1";
        Role role = new Role();
        role.setName(roleName);

        when(roleRepository.findRoleByName(roleName)).thenReturn(Optional.of(role));
        Role roleByName = roleService.getRoleByName(roleName);

        assertThat(roleByName).isEqualTo(role);
    }

    @Test
    void itShouldNotGetRoleByName() {
        String roleName = "role1";

        when(roleRepository.findRoleByName(roleName)).thenReturn(Optional.ofNullable(null));
        try {
            Role roleByName = roleService.getRoleByName(roleName);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Role does not exists");
        }
    }
}
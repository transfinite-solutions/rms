package one.transfinite.rms.role;

import one.transfinite.rms.utility.RoleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void saveRoleTest() {
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());

        Role saveRole = roleRepository.save(role);

        assertThat(saveRole.getRoleId()).isGreaterThan(0);
    }

    @Test
    void fetchRoleByName() {
        Role role = new Role();
        role.setName(RoleName.CUSTOMER.name());

        Role saveRole = roleRepository.save(role);
        Optional<Role> fetchedRole = roleRepository.findRoleByName(RoleName.CUSTOMER.name());

        assertThat(fetchedRole)
                .isPresent()
                .hasValueSatisfying(role1 -> assertThat(role1).isEqualTo(saveRole));
    }
}
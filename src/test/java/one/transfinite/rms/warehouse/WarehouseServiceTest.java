package one.transfinite.rms.warehouse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {WarehouseService.class})
@ExtendWith(SpringExtension.class)
class WarehouseServiceTest {

    @MockBean
    private WarehouseRepository warehouseRepository;

    @Autowired
    private WarehouseService warehouseService;


    @Test
    void getAllWarehouseTest() {
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setName("warehouse1");
        warehouse.setCreatedAt(new Date());
        warehouseList.add(warehouse);

        when(warehouseRepository.findAll()).thenReturn(warehouseList);
        List<Warehouse> allWarehouses = warehouseService.getAllWarehouses();

        assertThat(allWarehouses).isEqualTo(warehouseList);
    }

    @Test
    void itShouldGetWarehouseById() {
        long warehouseId = 1L;
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseId);
        warehouse.setName("warehouse1");
        warehouse.setCreatedAt(new Date());

        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));
        Warehouse warehouseById = warehouseService.getWarehouseById(warehouseId);

        assertThat(warehouseById).isEqualTo(warehouse);
    }

    @Test
    void itShouldNotGetWarehouseById() {
        long warehouseId = 1L;

        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.ofNullable(null));
        try {
            warehouseService.getWarehouseById(warehouseId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Warehouse does not exists");
        }
    }

    @Test
    void testAddWarehouse() {
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

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user);
        address.setTag("Tag");
        address.setState("MD");

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

        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);
        warehouse.setWarehouseId(123L);
        warehouse.setName("Name");
        warehouse.setUser(user1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        warehouse.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.warehouseRepository.save((Warehouse) any())).thenReturn(warehouse);

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

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user2);
        address1.setTag("Tag");
        address1.setState("MD");

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

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setAddress(address1);
        warehouse1.setWarehouseId(123L);
        warehouse1.setName("Name");
        warehouse1.setUser(user3);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        warehouse1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        this.warehouseService.addWarehouse(warehouse1);
        verify(this.warehouseRepository).save((Warehouse) any());
    }

    @Test
    void testDeleteWarehouse() {
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

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user);
        address.setTag("Tag");
        address.setState("MD");

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

        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);
        warehouse.setWarehouseId(123L);
        warehouse.setName("Name");
        warehouse.setUser(user1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        warehouse.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<Warehouse> ofResult = Optional.<Warehouse>of(warehouse);
        doNothing().when(this.warehouseRepository).deleteById((Long) any());
        when(this.warehouseRepository.findById((Long) any())).thenReturn(ofResult);
        this.warehouseService.deleteWarehouse(123L);
        verify(this.warehouseRepository).deleteById((Long) any());
        verify(this.warehouseRepository).findById((Long) any());
        assertTrue(this.warehouseService.getAllWarehouses().isEmpty());
    }

    @Test
    void testDeleteWarehouse2() {
        doNothing().when(this.warehouseRepository).deleteById((Long) any());
        when(this.warehouseRepository.findById((Long) any())).thenReturn(Optional.<Warehouse>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.warehouseService.deleteWarehouse(123L));
        verify(this.warehouseRepository).findById((Long) any());
    }

}
package one.transfinite.rms.warehouse;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {WarehouseController.class})
@ExtendWith(SpringExtension.class)
class WarehouseControllerTest {
    @Autowired
    private WarehouseController warehouseController;

    @MockBean
    private WarehouseService warehouseService;

    @Test
    void testGetWarehouseById() throws Exception {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
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
        user1.setPassword("password");
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
        when(this.warehouseService.getWarehouseById((Long) any())).thenReturn(warehouse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/warehouse/{warehouseId}", 123L);
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddWarehouse() throws Exception {
//        when(this.warehouseService.getAllWarehouses()).thenReturn(new ArrayList<Warehouse>());

        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
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
        user1.setPassword("password");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);
        warehouse.setWarehouseId(123L);
        warehouse.setName("Name");
//        warehouse.setUser(user1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        warehouse.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(warehouse);

        when(this.warehouseService.addWarehouse(any())).thenReturn(warehouse);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteWarehouse() throws Exception {
        doNothing().when(this.warehouseService).deleteWarehouse((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/warehouse/{warehouseId}", 123L);
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteWarehouse2() throws Exception {
        doNothing().when(this.warehouseService).deleteWarehouse((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/warehouse/{warehouseId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(this.warehouseService.getAllWarehouses()).thenReturn(new ArrayList<Warehouse>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/warehouse");
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllOrders2() throws Exception {
        when(this.warehouseService.getAllWarehouses()).thenReturn(new ArrayList<Warehouse>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/warehouse");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateWarehouse() throws Exception {
        when(this.warehouseService.getAllWarehouses()).thenReturn(new ArrayList<Warehouse>());

        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
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
        user1.setPassword("password");
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
        String content = (new ObjectMapper()).writeValueAsString(warehouse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.warehouseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


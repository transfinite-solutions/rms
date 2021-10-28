package one.transfinite.rms.Rent;

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
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RentController.class})
@ExtendWith(SpringExtension.class)
class RentControllerTest {
    @Autowired
    private RentController rentController;

    @MockBean
    private RentService rentService;

    @Test
    void testGetRentById() throws Exception {
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

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

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

        Rent rent = new Rent();
        rent.setRentId(123L);
        rent.setStatus(Status.DELIVERED);
        rent.setOrderStocks(new ArrayList<Stock>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setDropDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setCustomer(user);
        rent.setAddress(address);
        rent.setPaymentStatus(PaymentStatus.PAID);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setPickupDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setVendor(user2);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setTotalPrice(10.0);
        when(this.rentService.getRentById((Long) any())).thenReturn(rent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rent/{rentId}", 123L);
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"rentId\":123,\"vendor\":{\"userId\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212"
//                                        + "\",\"password\":\"iloveyou\",\"addresses\":[],\"role\":{\"roleId\":123,\"name\":\"Name\"},\"enabled\":true,\"username\""
//                                        + ":\"jane.doe@example.org\",\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true"
//                                        + ",\"authorities\":[{\"authority\":\"Name\"}]},\"customer\":{\"userId\":123,\"name\":\"Name\",\"email\":\"jane.doe@example"
//                                        + ".org\",\"phone\":\"4105551212\",\"password\":\"iloveyou\",\"addresses\":[],\"role\":{\"roleId\":123,\"name\":\"Name\"},"
//                                        + "\"enabled\":true,\"username\":\"jane.doe@example.org\",\"accountNonExpired\":true,\"credentialsNonExpired\":true"
//                                        + ",\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"Name\"}]},\"pickupDate\":0,\"dropDate\":0,\"createdAt"
//                                        + "\":0,\"status\":\"DELIVERED\",\"address\":{\"addressId\":123,\"line1\":\"Line1\",\"landmark\":\"Landmark\",\"city\":"
//                                        + "\"Oxford\",\"state\":\"MD\",\"country\":\"GB\",\"pincode\":\"Pincode\",\"tag\":\"Tag\"},\"totalPrice\":10.0,\"paymentStatus"
//                                        + "\":\"PAID\",\"orderStocks\":[]}"));
    }

    @Test
    void testAcceptRentOrder() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/rent/{rentId}/status/{status}",
                "Status", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testDeleteRent() throws Exception {
        doNothing().when(this.rentService).deleteRent((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/rent/{rentId}", 123L);
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteRent2() throws Exception {
        doNothing().when(this.rentService).deleteRent((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/rent/{rentId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddRent() throws Exception {
        when(this.rentService.getAllRent()).thenReturn(new ArrayList<Rent>());

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

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

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

        Rent rent = new Rent();
        rent.setRentId(123L);
        rent.setStatus(Status.DELIVERED);
        rent.setOrderStocks(new ArrayList<Stock>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setDropDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setCustomer(user);
        rent.setAddress(address);
        rent.setPaymentStatus(PaymentStatus.PAID);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setPickupDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setVendor(user2);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setTotalPrice(10.0);
        String content = (new ObjectMapper()).writeValueAsString(rent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllRents() throws Exception {
        when(this.rentService.getAllRent()).thenReturn(new ArrayList<Rent>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rent");
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllRents2() throws Exception {
        when(this.rentService.getAllRent()).thenReturn(new ArrayList<Rent>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/rent");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateRent() throws Exception {
        when(this.rentService.getAllRent()).thenReturn(new ArrayList<Rent>());

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

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

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

        Rent rent = new Rent();
        rent.setRentId(123L);
        rent.setStatus(Status.DELIVERED);
        rent.setOrderStocks(new ArrayList<Stock>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setDropDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setCustomer(user);
        rent.setAddress(address);
        rent.setPaymentStatus(PaymentStatus.PAID);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setPickupDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setVendor(user2);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        rent.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        rent.setTotalPrice(10.0);
        String content = (new ObjectMapper()).writeValueAsString(rent);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.rentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


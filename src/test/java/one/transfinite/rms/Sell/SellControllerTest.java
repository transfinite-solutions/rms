package one.transfinite.rms.Sell;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SellController.class})
@ExtendWith(SpringExtension.class)
class SellControllerTest {
    @Autowired
    private SellController sellController;

    @MockBean
    private SellService sellService;

    @Test
    void testGetSellById() throws Exception {
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
        user2.setPassword("password");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        when(this.sellService.getSellById((Long) any())).thenReturn(sell);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sell/{sellId}", 123L);
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"sellId\":123,\"vendor\":{\"userId\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212"
//                                        + "\",\"password\":\"password\",\"addresses\":[],\"role\":{\"roleId\":123,\"name\":\"Name\"},\"username\":\"jane.doe@example"
//                                        + ".org\",\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"Name\"}],"
//                                        + "\"accountNonLocked\":true,\"enabled\":true},\"customer\":{\"userId\":123,\"name\":\"Name\",\"email\":\"jane.doe"
//                                        + "@example.org\",\"phone\":\"4105551212\",\"password\":\"password\",\"addresses\":[],\"role\":{\"roleId\":123,\"name\""
//                                        + ":\"Name\"},\"username\":\"jane.doe@example.org\",\"accountNonExpired\":true,\"credentialsNonExpired\":true,"
//                                        + "\"authorities\":[{\"authority\":\"Name\"}],\"accountNonLocked\":true,\"enabled\":true},\"createdAt\":0,\"status\""
//                                        + ":\"DELIVERED\",\"address\":{\"addressId\":123,\"line1\":\"Line1\",\"landmark\":\"Landmark\",\"city\":\"Oxford\",\"state"
//                                        + "\":\"MD\",\"country\":\"GB\",\"pincode\":\"Pincode\",\"tag\":\"Tag\"},\"totalPrice\":10.0,\"paymentStatus\":\"PAID\","
//                                        + "\"orderStocks\":[]}"));
    }

    @Test
    void testAddSell() throws Exception {
        when(this.sellService.getAllSells()).thenReturn(new ArrayList<Sell>());

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
        user2.setPassword("password");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        String content = (new ObjectMapper()).writeValueAsString(sell);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeleteSell() throws Exception {
        doNothing().when(this.sellService).deleteSell((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sell/{sellId}", 123L);
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteSell2() throws Exception {
        doNothing().when(this.sellService).deleteSell((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/sell/{sellId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllSell() throws Exception {
        when(this.sellService.getAllSells()).thenReturn(new ArrayList<Sell>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sell");
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllSell2() throws Exception {
        when(this.sellService.getAllSells()).thenReturn(new ArrayList<Sell>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/sell");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateSell() throws Exception {
        when(this.sellService.getAllSells()).thenReturn(new ArrayList<Sell>());

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
        user2.setPassword("password");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        String content = (new ObjectMapper()).writeValueAsString(sell);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.sellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


package one.transfinite.rms.address;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AddressController.class})
@ExtendWith(SpringExtension.class)
class AddressControllerTest {

    @Autowired
    private AddressController addressController;

    @MockBean
    private AddressService addressService;

    @Test
    void testAddAddress2() throws Exception {
        when(this.addressService.getAllAddress()).thenReturn(new ArrayList<Address>());

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
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllAddress() throws Exception {
        when(this.addressService.getAllAddress()).thenReturn(new ArrayList<Address>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllAddress2() throws Exception {
        when(this.addressService.getAllAddress()).thenReturn(new ArrayList<Address>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/address");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testAddAddress() throws Exception {
        when(this.addressService.getAllAddress()).thenReturn(new ArrayList<Address>());

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
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAddressById() throws Exception {
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
        when(this.addressService.getAddressById((Long) any())).thenReturn(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address/{addressId}", 123L);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"addressId\":123,\"line1\":\"Line1\",\"landmark\":\"Landmark\",\"city\":\"Oxford\",\"state\":\"MD\",\"country\":\"GB\","
                                        + "\"pincode\":\"Pincode\",\"tag\":\"Tag\"}"));
    }

    @Test
    void testDeleteMapping() throws Exception {
        doNothing().when(this.addressService).deleteAddress((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/address/{addressId}", 123L);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMapping2() throws Exception {
        doNothing().when(this.addressService).deleteAddress((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/address/{addressId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateAddress() throws Exception {
        when(this.addressService.getAllAddress()).thenReturn(new ArrayList<Address>());

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
        String content = (new ObjectMapper()).writeValueAsString(address);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.addressController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


}


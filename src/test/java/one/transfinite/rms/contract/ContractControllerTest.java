package one.transfinite.rms.contract;

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
import one.transfinite.rms.product.Product;
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

@ContextConfiguration(classes = {ContractController.class})
@ExtendWith(SpringExtension.class)
class ContractControllerTest {
    @Autowired
    private ContractController contractController;

    @MockBean
    private ContractService contractService;

    @Test
    void testAddContract() throws Exception {
        when(this.contractService.getAllContract()).thenReturn(new ArrayList<Contract>());

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

        Contract contract = new Contract();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setEndDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setContractId(123L);
        contract.setCustomer(user);
        contract.setName("Name");
        contract.setVendor(user1);
        contract.setProducts(new ArrayList<Product>());
        contract.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(contract);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllContract() throws Exception {
        when(this.contractService.getAllContract()).thenReturn(new ArrayList<Contract>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contract");
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllContract2() throws Exception {
        when(this.contractService.getAllContract()).thenReturn(new ArrayList<Contract>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/contract");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetContractById() throws Exception {
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

        Contract contract = new Contract();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setEndDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setContractId(123L);
        contract.setCustomer(user);
        contract.setName("Name");
        contract.setVendor(user1);
        contract.setProducts(new ArrayList<Product>());
        contract.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.contractService.getContractById((Long) any())).thenReturn(contract);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contract/{contractId}", 123L);
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMapping() throws Exception {
        doNothing().when(this.contractService).deleteContract((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/contract/{contractId}", 123L);
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteMapping2() throws Exception {
        doNothing().when(this.contractService).deleteContract((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/contract/{contractId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateContract() throws Exception {
        when(this.contractService.getAllContract()).thenReturn(new ArrayList<Contract>());

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

        Contract contract = new Contract();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setEndDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setContractId(123L);
        contract.setCustomer(user);
        contract.setName("Name");
        contract.setVendor(user1);
        contract.setProducts(new ArrayList<Product>());
        contract.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        contract.setTotalPrice(10.0);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contract.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(contract);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/contract")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.contractController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


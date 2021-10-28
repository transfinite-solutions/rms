package one.transfinite.rms.stock;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.category.Category;
import one.transfinite.rms.order.Order;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;
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

@ContextConfiguration(classes = {StockController.class})
@ExtendWith(SpringExtension.class)
class StockControllerTest {
    @Autowired
    private StockController stockController;

    @MockBean
    private StockService stockService;

    @Test
    void testGetAllStocks() throws Exception {
        when(this.stockService.getAllStocks()).thenReturn(new ArrayList<Stock>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock");
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllStocks2() throws Exception {
        when(this.stockService.getAllStocks()).thenReturn(new ArrayList<Stock>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/stock");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetStockById() throws Exception {
        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");

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

        Product product = new Product();
        product.setProductId(123L);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setCategory(category);
        product.setUser(user);
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");

        Stock stock = new Stock();
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(10.0);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setOrders(new ArrayList<Order>());
        stock.setDurationTime(1L);
        stock.setProduct(product);
        stock.setStockId(123L);
        when(this.stockService.getStockById((Long) any())).thenReturn(stock);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock/{stockId}", 123L);
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"stockId\":123,\"product\":{\"productId\":123,\"name\":\"Name\",\"description\":\"The characteristics of someone"
//                                        + " or something\",\"imageUrl\":\"https://example.org/example\",\"type\":\"Type\",\"registrationNumber\":\"42\",\"user"
//                                        + "\":{\"userId\":123,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"password\":\"password"
//                                        + "\",\"addresses\":[],\"role\":{\"roleId\":123,\"name\":\"Name\"},\"enabled\":true,\"username\":\"jane.doe@example.org"
//                                        + "\",\"authorities\":[{\"authority\":\"Name\"}],\"accountNonLocked\":true,\"credentialsNonExpired\":true,"
//                                        + "\"accountNonExpired\":true},\"category\":{\"categoryId\":123,\"name\":\"Name\",\"image\":\"Image\"}},\"rate\":10.0,"
//                                        + "\"durationType\":\"HOUR\",\"durationTime\":1,\"availability\":\"AVAILABLE\",\"orders\":[]}"));
    }

    @Test
    void testAddStock() throws Exception {
//        when(this.stockService.getAllStocks()).thenReturn(new ArrayList<Stock>());

        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");

        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("password");
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId(123L);

        Product product = new Product();
        product.setProductId(123L);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setCategory(category);
//        product.setUser(user);
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");

        Stock stock = new Stock();
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(10.0);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setOrders(new ArrayList<Order>());
        stock.setDurationTime(1L);
        stock.setProduct(product);
        stock.setStockId(123L);
        String content = (new ObjectMapper()).writeValueAsString(stock);

        when(stockService.addStock((Stock) any())).thenReturn(stock);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteStock() throws Exception {
        doNothing().when(this.stockService).deleteStock((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/stock/{stockId}", 123L);
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteStock2() throws Exception {
        doNothing().when(this.stockService).deleteStock((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/stock/{stockId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.stockController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


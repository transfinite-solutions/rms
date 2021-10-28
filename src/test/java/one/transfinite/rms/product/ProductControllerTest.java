package one.transfinite.rms.product;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import one.transfinite.rms.address.Address;
import one.transfinite.rms.category.Category;

import one.transfinite.rms.category.CategoryService;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserService;
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

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private UserService userService;

    @Test
    void saveProductTest() throws Exception {
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
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");
        String content = (new ObjectMapper()).writeValueAsString(product);
        when(this.userService.getUserById(any())).thenReturn(user);
        when(this.productService.addProduct(any())).thenReturn(product);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product/user/{userId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetProductById() throws Exception {
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
        when(this.productService.getProductById((Long) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/{productId}", 123L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"productId\":123,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"imageUrl"
//                                        + "\":\"https://example.org/example\",\"type\":\"Type\",\"registrationNumber\":\"42\",\"user\":{\"userId\":123,\"name\":"
//                                        + "\"Name\",\"email\":\"jane.doe@example.org\",\"phone\":\"4105551212\",\"password\":\"password\",\"addresses\":[],\"role"
//                                        + "\":{\"roleId\":123,\"name\":\"Name\"},\"username\":\"jane.doe@example.org\",\"enabled\":true,\"authorities\":[{"
//                                        + "\"authority\":\"Name\"}],\"accountNonLocked\":true,\"accountNonExpired\":true,\"credentialsNonExpired\":true},"
//                                        + "\"category\":{\"categoryId\":123,\"name\":\"Name\",\"image\":\"Image\"}}"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(this.productService).deleteProduct((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/{productId}", 123L);
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(this.productService).deleteProduct((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/product/{productId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllProduct() throws Exception {
        when(this.productService.getAllProducts()).thenReturn(new ArrayList<Product>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllProduct2() throws Exception {
        when(this.productService.getAllProducts()).thenReturn(new ArrayList<Product>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


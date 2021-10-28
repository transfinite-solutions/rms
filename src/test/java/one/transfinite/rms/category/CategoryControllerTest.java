package one.transfinite.rms.category;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @Test
    void testAddCategory() throws Exception {
        when(this.categoryService.getAllCategory()).thenReturn(new ArrayList<Category>());

        Category category = new Category();
        category.setName("Name");
        category.setImage("Image");
        String content = (new ObjectMapper()).writeValueAsString(category);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string(content));
    }

    @Test
    void testGetAllCategory() throws Exception {
        when(this.categoryService.getAllCategory()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllCategory2() throws Exception {
        when(this.categoryService.getAllCategory()).thenReturn(new ArrayList<Category>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/category");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCategoryById() throws Exception {
        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");
        when(this.categoryService.getCategoryById((Long) any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/{categoryId}", 123L);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"categoryId\":123,\"name\":\"Name\",\"image\":\"Image\"}"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(this.categoryService).deleteCategory((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/category/{categoryId}", 123L);
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteCategory2() throws Exception {
        doNothing().when(this.categoryService).deleteCategory((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/category/{categoryId}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.categoryController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


package one.transfinite.rms.category;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.execption.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void saveCategoryTest() {
        Category category = new Category();
        category.setName("category1");
        category.setImage("url");

        categoryService.addCategory(category);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());

        Category captorValue = categoryArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(category);
    }

    @Test
    void getAllCategoryTest() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setName("category1");
        category.setImage("url");
        categoryList.add(category);

        when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> allCategory = categoryService.getAllCategory();

        assertThat(allCategory).isEqualTo(categoryList);
    }

    @Test
    void itShouldGetCategoryByIdTest() {
        long categoryId = 1L;

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName("category1");
        category.setImage("url");

        when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(category));
        Category categoryById = categoryService.getCategoryById(categoryId);

        assertThat(categoryById).isEqualTo(category);
    }

    @Test
    void itShouldNotGetCategoryByIdTest() {
        long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(null));
        try {
            categoryService.getCategoryById(categoryId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Category does not exists");
        }
    }
}
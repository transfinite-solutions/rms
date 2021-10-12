package one.transfinite.rms.category;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void saveCategoryTest() {
        Category category = new Category();
        category.setName("category1");
        category.setImage("url");

        Category savedCategory = categoryRepository.save(category);

        assertThat(savedCategory.getCategoryId()).isGreaterThan(0);
    }

    @Test
    void findCategoryTest() {
        Category category = new Category();
        String categoryName = "category1";
        category.setName(categoryName);
        category.setImage("url");

        Category savedCategory = categoryRepository.save(category);
        Optional<Category> fetchedCategory = categoryRepository.findCategoryByName(categoryName);

        assertThat(fetchedCategory)
                .isPresent()
                .hasValueSatisfying(category1 -> assertThat(category1).isEqualTo(savedCategory));
    }
}
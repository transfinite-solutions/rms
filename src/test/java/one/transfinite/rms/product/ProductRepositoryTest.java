package one.transfinite.rms.product;

import one.transfinite.rms.category.Category;
import one.transfinite.rms.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void saveProductTest() {
        String productName = "product1";

        Product product = new Product();
        product.setName(productName);
        product.setDescription("description");
        product.setType("item");
        product.setCategory(null);
        product.setUser(null);

        underTest.save(product);

        assertThat(product.getProductId()).isGreaterThan(0);
    }

    @Test
    void itShouldSelectProductByName() {
        //GIVEN
        String productName = "product1";

        Product product = new Product();
        product.setName(productName);
        product.setDescription("description");
        product.setType("item");

        //WHEN
        underTest.save(product);

        //THEN
        Optional<Product> optionalProduct = underTest.findProductByName(productName);
        assertThat(optionalProduct)
                .isPresent()
                .hasValueSatisfying(product1 -> assertThat(product1).isEqualTo(product));
    }

    @Test
    void itShouldNotSelectProductByName() {
        String productName = "product1";

        Optional<Product> optionalProduct = underTest.findProductByName(productName);
        assertThat(optionalProduct).isEmpty();
    }

    @Test
    void findProductByRegistrationNumber() {
        //GIVEN
        String productName = "product1";
        String registrationNumber = "res123";

        Product product = new Product();
        product.setName(productName);
        product.setDescription("description");
        product.setType("item");
        product.setRegistrationNumber(registrationNumber);

        //WHEN
        underTest.save(product);

        //THEN
        Optional<Product> optionalProduct = underTest.findProductByRegistrationNumber(registrationNumber);
        assertThat(optionalProduct)
                .isPresent()
                .hasValueSatisfying(product1 -> assertThat(product1).isEqualTo(product));
    }

    @Test
    void findProductByType() {
        //GIVEN
        List<Product> products = new ArrayList<>();
        List<Product> sellProducts = new ArrayList<>();
        String sellType = "sell";

        Product product = new Product();
        product.setName("product1");
        product.setDescription("description");
        product.setType(sellType);
        product.setRegistrationNumber("res1");
        products.add(product);
        sellProducts.add(product);

        Product product1 = new Product();
        product1.setName("product2");
        product1.setDescription("description");
        product1.setType(sellType);
        product1.setRegistrationNumber("res2");
        products.add(product1);
        sellProducts.add(product1);

        Product product2 = new Product();
        product2.setName("product3");
        product2.setDescription("description");
        product2.setType("rent");
        product2.setRegistrationNumber("res3");
        products.add(product2);

        //WHEN
        underTest.saveAll(products);
        List<Product> productList = underTest.findProductByType(sellType);

        //THEN
        assertThat(productList).isEqualTo(sellProducts);
        assertThat(productList).isNotEqualTo(products);
        assertThat(productList).asList().contains(product1);
        assertThat(productList).asList().doesNotContain(product2);
    }

    @Test
    void deleteProductTest() {
        String productName = "product1";

        Product product = new Product();
        product.setName(productName);
        product.setDescription("description");
        product.setType("item");
        product.setCategory(null);
        product.setUser(null);

        Product saveProduct = underTest.save(product);
        Optional<Product> fetchedProduct = underTest.findProductByName(productName);

        assertThat(fetchedProduct)
                .isPresent()
                .hasValueSatisfying(product1 -> {
                    underTest.delete(product1);
                    assertThat(underTest.count()).isEqualTo(0);
                });
    }
}
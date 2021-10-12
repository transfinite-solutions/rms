package one.transfinite.rms.product;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void saveProductTest() {
        Product product = new Product();
        product.setName("product1");
        product.setDescription("description");
        product.setImageUrl("url");

        productService.addProduct(product);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
        Product captorValue = productArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(product);
    }

    @Test
    void getAllProducts(){
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setName("product1");
        product.setDescription("description");
        product.setImageUrl("url");
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);
        List<Product> allProducts = productService.getAllProducts();

        assertThat(allProducts).isEqualTo(productList);
    }

    @Test
    void itShouldGetProductById() {
        long productId = 1L;

        Product product = new Product();
        product.setProductId(productId);
        product.setName("product1");
        product.setDescription("description");
        product.setImageUrl("url");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Product productById = productService.getProductById(productId);

        assertThat(productById).isEqualTo(product);
    }

    @Test
    void itShouldNotGetProductById() {
        long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(null));

        try {
            Product productById = productService.getProductById(productId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Product not found");
        }
    }
}
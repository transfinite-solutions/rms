package one.transfinite.rms.product;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return this.productRepository.findById(productId).orElse(null);
    }

    public void addProduct(Product product) {
        this.productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        this.productRepository.deleteById(productId);
    }
}

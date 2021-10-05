package one.transfinite.rms.product;

import one.transfinite.rms.category.Category;
import one.transfinite.rms.category.CategoryService;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/user/{userId}")
    public void addProduct(@RequestBody Product product, @PathVariable("userId") Long userId) {
        User user = this.userService.getUserById(userId);
        product.setUser(user);
        productService.addProduct(product);
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public void addProductWithCategory(@RequestBody Product product,
                                       @PathVariable("userId") Long userId,
                                       @PathVariable("categoryId") Long categoryId) {
        User user = this.userService.getUserById(userId);
        product.setUser(user);
        Category category = this.categoryService.getCategoryById(categoryId);
        product.setCategory(category);
        productService.addProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}

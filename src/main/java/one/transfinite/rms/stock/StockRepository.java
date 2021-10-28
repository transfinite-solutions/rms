package one.transfinite.rms.stock;

import one.transfinite.rms.category.Category;
import one.transfinite.rms.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<List<Stock>> findStockByProduct(Product product);

//    @Query(value = "SELECT s.* FROM stock s INNER JOIN product p ON s.product_product_id = p.product_id WHERE p.category_categoryId = :categoryId", nativeQuery = true)
//    Optional<List<Stock>> findStockByCategory(Long categoryId);

    @Query(value = "SELECT *, COUNT(*) AS quantity FROM stock s INNER JOIN product p ON s.product_product_id = p.product_id WHERE p.category_categoryId = :categoryId AND s.availability = \"AVAILABLE\" GROUP BY S.product_product_id", nativeQuery = true)
    Optional<List<Map<String, Object>>> findStockByCategory(Long categoryId);
}

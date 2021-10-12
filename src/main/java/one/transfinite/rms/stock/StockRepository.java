package one.transfinite.rms.stock;

import one.transfinite.rms.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<List<Stock>> findStockByProduct(Product product);
}

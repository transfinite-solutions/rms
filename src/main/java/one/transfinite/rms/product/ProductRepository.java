package one.transfinite.rms.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    Optional<Product> findProductByRegistrationNumber(String registrationNumber);

    List<Product> findProductByType(String type);

    List<Product> findProductByCategory(String category);
}

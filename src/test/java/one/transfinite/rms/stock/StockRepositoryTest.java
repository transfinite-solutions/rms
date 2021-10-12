package one.transfinite.rms.stock;

import one.transfinite.rms.product.Product;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    void saveStockTest() {
        Stock stock = new Stock();
        stock.setAvailability(Availability.AVAILABLE);
        stock.setDurationTime(5L);
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(100.23);

        Stock savedStock = stockRepository.save(stock);

        assertThat(savedStock.getStockId()).isGreaterThan(0);
    }

    @Test
    void findStockByProductTest() {
        Product product1 = new Product();
        product1.setName("product1");

        Product product2 = new Product();
        product2.setName("product2");

        Stock stock = new Stock();
        stock.setAvailability(Availability.AVAILABLE);
        stock.setDurationTime(5L);
        stock.setDurationType(DurationType.HOUR);
        stock.setProduct(product1);
        stock.setRate(100.23);
        Stock savedStock1 = stockRepository.save(stock);

        Stock stock1 = new Stock();
        stock1.setAvailability(Availability.AVAILABLE);
        stock1.setDurationTime(5L);
        stock1.setDurationType(DurationType.HOUR);
        stock1.setProduct(product2);
        stock1.setRate(100.23);
        Stock savedStock2 = stockRepository.save(stock1);

        Optional<List<Stock>> stockList = stockRepository.findStockByProduct(product1);

        assertThat(stockList)
                .isPresent()
                .hasValueSatisfying(stocks -> assertThat(stocks).asList().contains(savedStock1));
        assertThat(stockList)
                .isPresent()
                .hasValueSatisfying(stocks -> assertThat(stocks).asList().doesNotContain(savedStock2));
    }
}
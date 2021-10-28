package one.transfinite.rms.stock;

import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Stock> getAllStocks(){
        return this.stockRepository.findAll();
    }

    public Stock getStockById(Long stockId) {
        return this.stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
    }

    public List<Stock> getStockByProductId(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product does not exists"));
        return this.stockRepository.findStockByProduct(product).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
    }

    public List<Map<String, Object>> getStockByCategoryId(Long categoryId) {
        return this.stockRepository.findStockByCategory(categoryId).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
    }

    public Stock addStock(Stock stock) {
        return this.stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        this.stockRepository.findById(stock.getStockId()).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
        return this.stockRepository.save(stock);
    }

    public void deleteStock(Long stockId) {
        this.stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
        this.stockRepository.deleteById(stockId);
    }
}

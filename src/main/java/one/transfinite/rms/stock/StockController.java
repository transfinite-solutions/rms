package one.transfinite.rms.stock;

import one.transfinite.rms.Constant;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> allStocks = stockService.getAllStocks();

        return new ResponseEntity<>(allStocks, HttpStatus.OK);
    }

    @GetMapping("/{stockId}")
    public Stock getStockById(@PathVariable Long stockId) {
        return stockService.getStockById(stockId);
    }

    @GetMapping("/product/{productId}")
    public List<Stock> getStockByProduct(@PathVariable("productId") Long productId) {
        return stockService.getStockByProductId(productId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Map<String, Object>> getStockByCategory(@PathVariable("categoryId") Long categoryId) {
        return stockService.getStockByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.addStock(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.OK);
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<Stock> addStockByProduct(@PathVariable("productId") Long productId ,@RequestBody Stock stock) {
        Product product = productService.getProductById(productId);
        stock.setProduct(product);
        Stock savedStock = stockService.addStock(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.updateStock(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.OK);
    }

    @DeleteMapping("/{stockId}")
    public void deleteStock(@PathVariable Long stockId) {
        stockService.deleteStock(stockId);
    }
}

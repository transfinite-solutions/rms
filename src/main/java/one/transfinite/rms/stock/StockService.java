package one.transfinite.rms.stock;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return this.stockRepository.findAll();
    }

    public Stock getStockById(Long stockId) {
        return this.stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
    }

    public void addStock(Stock stock) {
        this.stockRepository.save(stock);
    }

    public void deleteStock(Long stockId) {
        this.stockRepository.findById(stockId).orElseThrow(() -> new ResourceNotFoundException("Stock does not exists"));
        this.stockRepository.deleteById(stockId);
    }
}

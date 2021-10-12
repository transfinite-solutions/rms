package one.transfinite.rms.Sell;

import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellService {

    @Autowired
    private SellRepository sellRepository;

    public List<Sell> getAllSells() {
        return sellRepository.findAll();
    }

    public Sell getSellById(Long sellId) {
        return sellRepository.findById(sellId).orElseThrow(() -> new ResourceNotFoundException("Sell order does not exists"));
    }

    public void addSell(Sell sell) {
        List<Stock> orderStocks = sell.getOrderStocks();
        Double totalAmount = 0.0d;
        for (Stock stock : orderStocks) {
            totalAmount += stock.getRate();
        }
        sell.setTotalPrice(totalAmount);
        sellRepository.save(sell);
    }

    public void updateSell(Sell sell){
        sellRepository.findById(sell.getSellId()).orElseThrow(() -> new ResourceNotFoundException("Sell order does not exists"));

        List<Stock> orderStocks = sell.getOrderStocks();
        Double totalAmount = 0.0d;
        for (Stock stock : orderStocks) {
            totalAmount += stock.getRate();
        }
        sell.setTotalPrice(totalAmount);
        sellRepository.save(sell);
    }

    public void deleteSell(Long sellId) {
        sellRepository.findById(sellId).orElseThrow(() -> new ResourceNotFoundException("Sell order does not exists"));
        sellRepository.deleteById(sellId);
    }
}

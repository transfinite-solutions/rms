package one.transfinite.rms.Sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private SellService sellService;

    @GetMapping
    public List<Sell> getAllSell(){
        return sellService.getAllSells();
    }

    @GetMapping("/{sellId}")
    public Sell getSellById(@PathVariable("sellId") Long sellId) {
        return sellService.getSellById(sellId);
    }

    @PostMapping
    public void addSell(@RequestBody Sell sell) {
        sellService.addSell(sell);;
    }

    @PutMapping
    public void updateSell(@RequestBody Sell sell) {
        sellService.updateSell(sell);
    }

    @DeleteMapping("/{sellId}")
    public void deleteSell(@PathVariable("sellId") Long sellId) {
        sellService.deleteSell(sellId);
    }
}

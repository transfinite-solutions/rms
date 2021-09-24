package one.transfinite.rms.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAllOrders() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping("/{warehouseId}")
    public Warehouse getWarehouseById(@PathVariable Long warehouseId) {
        return warehouseService.getWarehouseById(warehouseId);
    }

    @PostMapping
    public void addWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.addWarehouse(warehouse);
    }

    @DeleteMapping("/{warehouseId}")
    public void deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
    }
}

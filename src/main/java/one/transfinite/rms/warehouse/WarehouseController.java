package one.transfinite.rms.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseService.addWarehouse(warehouse);
        return new ResponseEntity<>(savedWarehouse, HttpStatus.OK);
    }

    @PutMapping
    public void updateWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.updateWarehouse(warehouse);
    }

    @DeleteMapping("/{warehouseId}")
    public void deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
    }
}

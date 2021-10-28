package one.transfinite.rms.warehouse;

import one.transfinite.rms.execption.ApiBadRequestException;
import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses(){
        return this.warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return this.warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse does not exists"));
    }

    public Warehouse addWarehouse(Warehouse warehouse) {
        return this.warehouseRepository.save(warehouse);
    }

    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.findById(warehouse.getWarehouseId()).orElseThrow(() -> new ApiBadRequestException("Warehouse does not exists"));
        this.warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long warehouseId) {
        this.warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse does not exists"));
        this.warehouseRepository.deleteById(warehouseId);
    }
}

package one.transfinite.rms.warehouse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @AfterEach
    void tearDown() {
        warehouseRepository.deleteAll();
    }

    @Test
    void saveWarehouseTest() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("warehouse1");
        warehouse.setCreatedAt(new Date());

        Warehouse saveWarehouse = warehouseRepository.save(warehouse);

        assertThat(saveWarehouse.getWarehouseId()).isGreaterThan(0);
    }

    @Test
    void findWarehouseByName() {
        Warehouse warehouse = new Warehouse();
        warehouse.setName("warehouse1");
        warehouse.setCreatedAt(new Date());

        Warehouse saveWarehouse = warehouseRepository.save(warehouse);
        Optional<List<Warehouse>> warehouseList = warehouseRepository.findWarehouseByName("warehouse1");

        assertThat(warehouseList)
                .isPresent()
                .hasValueSatisfying(warehouses -> assertThat(warehouses).asList().contains(saveWarehouse));
    }
}
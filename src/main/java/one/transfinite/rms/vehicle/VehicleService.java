package one.transfinite.rms.vehicle;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles(){
        return this.vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long vehicleId) {
        return this.vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exists"));
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId) {
        this.vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle does not exists"));
        this.vehicleRepository.deleteById(vehicleId);
    }
}

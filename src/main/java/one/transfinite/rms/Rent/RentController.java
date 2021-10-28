package one.transfinite.rms.Rent;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.address.AddressService;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.stock.StockService;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserService;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Rent> getAllRents() {
        return rentService.getAllRent();
    }

    @GetMapping("/{rentId}")
    public Rent getRentById(@PathVariable("rentId") Long rentId) {
        return rentService.getRentById(rentId);
    }

    @PostMapping
    public void addRent(@RequestBody Rent rent) {
        System.out.println(rent);
        rentService.addRent(rent);
    }

    @PostMapping("/customer/{customerId}/address/{addressId}")
    public void addRentByCustomer(@PathVariable("customerId") Long customerId,
                                  @PathVariable("addressId") Long addressId,
                                  @RequestBody Rent rent) {
        User customer = this.userService.getUserById(customerId);
        Address address = this.addressService.getAddressById(addressId);
        rent.setCustomer(customer);
        rent.setAddress(address);
        List<Stock> stockList = new ArrayList<>();
        rent.getOrderStocks().forEach(stock -> {
            Stock stockById = this.stockService.getStockById(stock.getStockId());
            stockById.setAvailability(Availability.RENTED);
            stockList.add(stockById);
        });
        rent.setOrderStocks(stockList);
        rentService.addRent(rent);
    }

    @PutMapping
    public void updateRent(@RequestBody Rent rent) {
        rentService.updateRent(rent);
    }

    @PutMapping("/{rentId}/status/{status}")
    public void acceptRentOrder(@PathVariable("rentId") Long rentId, @PathVariable("status") String status) {
        Rent rent = rentService.getRentById(rentId);
        if (status.equalsIgnoreCase("DELIVERED")) {
            rent.setStatus(Status.DELIVERED);
        } else if (status.equalsIgnoreCase("ACCEPTED")) {
            rent.setStatus(Status.ACCEPTED);
        } else if (status.equalsIgnoreCase("REJECTED")) {
            rent.setStatus(Status.REJECTED);
        } else if (status.equalsIgnoreCase("PROCESSING")) {
            rent.setStatus(Status.PROCESSING);
        } else {
            throw new ResourceNotFoundException("Status not found");
        }

        rentService.updateRent(rent);
    }

    @DeleteMapping("/{rentId}")
    public void deleteRent(@PathVariable("rentId") Long rentId) {
        rentService.deleteRent(rentId);
    }
}

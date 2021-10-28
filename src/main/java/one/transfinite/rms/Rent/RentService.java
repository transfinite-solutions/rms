package one.transfinite.rms.Rent;

import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.utility.Status;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    public List<Rent> getAllRent() {
        return rentRepository.findAll();
    }

    public Rent getRentById(Long rentId) {
        return rentRepository.findById(rentId).orElseThrow(() -> new ResourceNotFoundException("Rent order does not exist"));
    }

    public void addRent(Rent rent){
        List<Stock> orderStocks = rent.getOrderStocks();
        Double totalAmount = 0.0d;
        for (Stock stock: orderStocks) {
            totalAmount += stock.getRate();
        }

        rent.setStatus(Status.PROCESSING);
        if (rent.getPickupDate() != null && rent.getDropDate() != null) {
            rent.setTotalPrice(totalAmount * getHourDiff(rent.getPickupDate(), rent.getDropDate()));
        }
        rentRepository.save(rent);
    }

    public void updateRent(Rent rent){
        rentRepository.findById(rent.getRentId()).orElseThrow(() -> new ResourceNotFoundException("Rent order does not exist"));

        List<Stock> orderStocks = rent.getOrderStocks();
        Double totalAmount = 0.0d;
        for (Stock stock: orderStocks) {
            totalAmount += stock.getRate();
        }

        if (rent.getPickupDate() != null && rent.getDropDate() != null) {
            rent.setTotalPrice(totalAmount * getHourDiff(rent.getPickupDate(), rent.getDropDate()));
        }
        rentRepository.save(rent);
    }

    public void deleteRent(Long rentId) {
        rentRepository.deleteById(rentId);
    }

    public Long getHourDiff(Date d1, Date d2) {
        Long diff = d2.getTime() - d1.getTime();
        Long hours = diff / (60 * 60 * 1000) % 24;
        return hours;
    }
}

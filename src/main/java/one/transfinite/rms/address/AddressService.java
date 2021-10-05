package one.transfinite.rms.address;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address does not exists"));
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void updateAddress(Address address){
        addressRepository.save(address);
    }

    public void deleteAddress(Long addressId) {
        addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address does not exists"));
        addressRepository.deleteById(addressId);
    }
}

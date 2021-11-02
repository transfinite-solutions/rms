package one.transfinite.rms.address;

import one.transfinite.rms.execption.ApiBadRequestException;
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

    public Address addAddress(Address address) {

        if(address.getLine1() == null
                && address.getCity() == null
                && address.getState() == null
                && address.getCountry() == null
                && address.getPincode() == null
        ){
            throw new ApiBadRequestException("Necessary address field not provided");
        }
        return addressRepository.save(address);
    }

    public List<Address> addAddressBulk(List<Address> addresses) {
      addresses.forEach(address -> {
        if(address.getLine1() == null
          && address.getCity() == null
          && address.getState() == null
          && address.getCountry() == null
          && address.getPincode() == null
        ){
          throw new ApiBadRequestException("Necessary address field not provided");
        }
      });
      return this.addressRepository.saveAll(addresses);
    }

    public Address updateAddress(Address address){

        if(address.getAddressId() == null
                && address.getLine1() == null
                && address.getCity() == null
                && address.getState() == null
                && address.getCountry() == null
                && address.getPincode() == null
        ){
            throw new ApiBadRequestException("Necessary address field not provided");
        }
        return addressRepository.save(address);
    }

    public void deleteAddress(Long addressId) {

        addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("Address does not exists"));
        addressRepository.deleteById(addressId);
    }
}

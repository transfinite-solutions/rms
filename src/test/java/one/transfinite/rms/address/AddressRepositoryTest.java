package one.transfinite.rms.address;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

    @Test
    void saveAddressTest() {
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setLine1("address line 1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");

        Address savedAddress = addressRepository.save(address);

        assertThat(savedAddress.getAddressId()).isGreaterThan(0);
    }

    @Test
    void deleteAddressTest() {
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setLine1("address line 1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");

        addressRepository.save(address);
        addressRepository.deleteAll();

        assertThat(addressRepository.count()).isEqualTo(0);
    }
}
package one.transfinite.rms.address;

import one.transfinite.rms.execption.ApiBadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @AfterEach
    void tearDown() {
        addressRepository.deleteAll();
    }

    @Test
    void itShouldSaveAddressTest() {
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setLine1("address line 1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");

        addressService.addAddress(address);

        ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
        verify(addressRepository).save(addressArgumentCaptor.capture());

        Address capturedAddress = addressArgumentCaptor.getValue();
        assertThat(capturedAddress).isEqualTo(address);
    }

    @Test
    void itShouldNotSaveAddressTest() {
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");

        try {
            addressService.addAddress(address);

            ArgumentCaptor<Address> addressArgumentCaptor = ArgumentCaptor.forClass(Address.class);
            verify(addressRepository).save(addressArgumentCaptor.capture());

            Address capturedAddress = addressArgumentCaptor.getValue();
        } catch (ApiBadRequestException e) {
            assertThat(e.getMessage()).isEqualTo("Necessary address field not provided");
        }


    }

    @Test
    void itShouldNotUpdateAddressTest() {
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setLine1("address line 1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");

        when(addressRepository.save(any(Address.class))).thenReturn(new Address());

        try {
            Address savedAddress = addressService.updateAddress(address);
        } catch (ApiBadRequestException e) {
            assertThat(e.getMessage()).isEqualTo("Necessary address field not provided");
        }

    }

    @Test
    void itShouldFindAllAddressTest() {
        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setCity("city1");
        address.setCountry("country1");
        address.setLandmark("landmark1");
        address.setLine1("address line 1");
        address.setPincode("123456");
        address.setState("state1");
        address.setTag("home");
        addressList.add(address);

        when(addressRepository.findAll()).thenReturn(addressList);
        List<Address> allAddress = addressService.getAllAddress();

        assertThat(allAddress.size()).isGreaterThan(0);
    }
}
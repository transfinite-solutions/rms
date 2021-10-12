package one.transfinite.rms.Rent;

import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.utility.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentServiceTest {

    @Mock
    private RentRepository rentRepository;

    @InjectMocks
    private RentService rentService;

    @Test
    void saveRentTest() {
        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);

        ArgumentCaptor<Rent> rentArgumentCaptor = ArgumentCaptor.forClass(Rent.class);
        rentService.addRent(rent);
        verify(rentRepository).save(rentArgumentCaptor.capture());
        Rent captorValue = rentArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(rent);
    }

    @Test
    void getAllRentTest() {
        List<Rent> rentList = new ArrayList<>();
        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);
        rentList.add(rent);

        when(rentRepository.findAll()).thenReturn(rentList);
        List<Rent> allRent = rentService.getAllRent();

        assertThat(allRent).isEqualTo(rentList);
    }

    @Test
    void itShouldGetRentById() {
        long rentId = 1L;

        Rent rent = new Rent();
        rent.setRentId(rentId);
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);

        when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
        Rent rentById = rentService.getRentById(rentId);

        assertThat(rentById).isEqualTo(rent);
    }

    @Test
    void itShouldNotGetRentById() {
        long rentId = 1L;

        when(rentRepository.findById(rentId)).thenReturn(Optional.ofNullable(null));
        try {
            Rent rentById = rentService.getRentById(rentId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Rent order does not exist");
        }
    }
}
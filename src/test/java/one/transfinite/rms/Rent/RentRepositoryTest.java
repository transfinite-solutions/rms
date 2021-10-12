package one.transfinite.rms.Rent;

import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserRepository;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.RoleName;
import one.transfinite.rms.utility.Status;
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
class RentRepositoryTest {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        rentRepository.deleteAll();
    }

    @Test
    void saveRentTest() {
        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);

        Rent savedRent = rentRepository.save(rent);

        assertThat(savedRent.getRentId()).isGreaterThan(0);
    }

    @Test
    void findRentByVendor() {
        //Customer
        Role roleCustomer = new Role();
        roleCustomer.setName(RoleName.CUSTOMER.name());
        User customer = new User();
        customer.setName("customer");
        customer.setEmail("customer@gmail.com");
        customer.setPhone("7485963215");
        customer.setPassword("pass");
        customer.setRole(roleCustomer);

        //Vendor
        Role roleVendor = new Role();
        roleVendor.setName(RoleName.VENDOR.name());
        User vendor = new User();
        vendor.setName("vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhone("8596324488");
        vendor.setPassword("pass");
        vendor.setRole(roleVendor);

        //Vendor2
        User vendor2 = new User();
        vendor2.setName("vendor2");
        vendor2.setEmail("vendor2@gmail.com");
        vendor2.setPhone("8596354488");
        vendor2.setPassword("pass");
        vendor2.setRole(roleVendor);

        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);
        rent.setVendor(vendor);
        rent.setCustomer(customer);
        rentRepository.save(rent);

        Rent rent2 = new Rent();
        rent2.setStatus(Status.PROCESSING);
        rent2.setCreatedAt(new Date());
        rent2.setPaymentStatus(null);
        rent2.setVendor(vendor2);
        rentRepository.save(rent2);

        Optional<List<Rent>> rentList = rentRepository.findRentByVendor(vendor);

        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().contains(rent));
        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().doesNotContain(rent2));
    }

    @Test
    void findRentByCustomer() {
        //Customer
        Role roleCustomer = new Role();
        roleCustomer.setName(RoleName.CUSTOMER.name());
        User customer = new User();
        customer.setName("customer");
        customer.setEmail("customer@gmail.com");
        customer.setPhone("7485963215");
        customer.setPassword("pass");
        customer.setRole(roleCustomer);

        //customer2
        User customer2 = new User();
        customer2.setName("customer2");
        customer2.setEmail("customer2@gmail.com");
        customer2.setPhone("7485965215");
        customer2.setPassword("pass");
        customer2.setRole(roleCustomer);

        //Vendor
        Role roleVendor = new Role();
        roleVendor.setName(RoleName.VENDOR.name());
        User vendor = new User();
        vendor.setName("vendor");
        vendor.setEmail("vendor@gmail.com");
        vendor.setPhone("8596324488");
        vendor.setPassword("pass");
        vendor.setRole(roleVendor);

        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);
        rent.setVendor(vendor);
        rent.setCustomer(customer);
        rentRepository.save(rent);

        Rent rent2 = new Rent();
        rent2.setStatus(Status.PROCESSING);
        rent2.setCreatedAt(new Date());
        rent2.setPaymentStatus(null);
        rent2.setCustomer(customer2);
        rentRepository.save(rent2);

        Optional<List<Rent>> rentList = rentRepository.findRentByCustomer(customer);

        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().contains(rent));
        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().doesNotContain(rent2));
    }

    @Test
    void findRentByStatus() {

        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(null);
        rentRepository.save(rent);

        Rent rent2 = new Rent();
        rent2.setStatus(Status.ACCEPTED);
        rent2.setCreatedAt(new Date());
        rent2.setPaymentStatus(null);
        rentRepository.save(rent2);

        Optional<List<Rent>> rentList = rentRepository.findRentByStatus(Status.PROCESSING);

        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().contains(rent));
        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().doesNotContain(rent2));
    }

    @Test
    void findRentByPaymentStatus() {

        Rent rent = new Rent();
        rent.setStatus(Status.PROCESSING);
        rent.setCreatedAt(new Date());
        rent.setPaymentStatus(PaymentStatus.PAID);
        rentRepository.save(rent);

        Rent rent2 = new Rent();
        rent2.setStatus(Status.PROCESSING);
        rent2.setCreatedAt(new Date());
        rent2.setPaymentStatus(PaymentStatus.OVERDUE);
        rentRepository.save(rent2);

        Optional<List<Rent>> rentList = rentRepository.findRentByPaymentStatus(PaymentStatus.PAID);

        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().contains(rent));
        assertThat(rentList)
                .isPresent()
                .hasValueSatisfying(rents -> assertThat(rents).asList().doesNotContain(rent2));
    }
}
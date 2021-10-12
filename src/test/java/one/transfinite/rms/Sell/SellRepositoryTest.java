package one.transfinite.rms.Sell;

import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
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
class SellRepositoryTest {

    @Autowired
    private SellRepository sellRepository;

    @AfterEach
    void tearDown() {
        sellRepository.deleteAll();
    }

    @Test
    void saveSellTest() {
        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);

        Sell savedSell = sellRepository.save(sell);

        assertThat(savedSell.getSellId()).isGreaterThan(0);
    }

    @Test
    void findSellByVendor() {
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

        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);
        sell.setVendor(vendor);
        sell.setCustomer(customer);
        sellRepository.save(sell);

        Sell sell2 = new Sell();
        sell2.setStatus(Status.PROCESSING);
        sell2.setCreatedAt(new Date());
        sell2.setPaymentStatus(null);
        sell2.setVendor(vendor2);
        sellRepository.save(sell2);

        Optional<List<Sell>> sellList = sellRepository.findSellByVendor(vendor);

        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().contains(sell));
        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().doesNotContain(sell2));
    }

    @Test
    void findSellByCustomer() {
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
        customer2.setPhone("7485975215");
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

        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);
        sell.setVendor(vendor);
        sell.setCustomer(customer);
        Sell savedSell1 = sellRepository.save(sell);

        Sell sell2 = new Sell();
        sell2.setStatus(Status.PROCESSING);
        sell2.setCreatedAt(new Date());
        sell2.setPaymentStatus(null);
        sell2.setVendor(vendor);
        sell.setCustomer(customer2);
        Sell savedSell2 = sellRepository.save(sell2);

        Optional<List<Sell>> sellList = sellRepository.findSellByCustomer(customer);

//        assertThat(sellList)
//                .isPresent()
//                .hasValueSatisfying(sells -> assertThat(sells).asList().contains(savedSell1));
        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().doesNotContain(savedSell2));
    }

    @Test
    void findSellByStatus() {

        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);
        sellRepository.save(sell);

        Sell sell2 = new Sell();
        sell2.setStatus(Status.ACCEPTED);
        sell2.setCreatedAt(new Date());
        sell2.setPaymentStatus(null);
        sellRepository.save(sell2);

        Optional<List<Sell>> sellList = sellRepository.findSellByStatus(Status.PROCESSING);

        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().contains(sell));
        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().doesNotContain(sell2));
    }

    @Test
    void findSellByPaymentStatus() {

        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(PaymentStatus.PAID);
        sellRepository.save(sell);

        Sell sell2 = new Sell();
        sell2.setStatus(Status.ACCEPTED);
        sell2.setCreatedAt(new Date());
        sell2.setPaymentStatus(PaymentStatus.OVERDUE);
        sellRepository.save(sell);

        Optional<List<Sell>> sellList = sellRepository.findSellByPaymentStatus(PaymentStatus.PAID);

        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().contains(sell));
        assertThat(sellList)
                .isPresent()
                .hasValueSatisfying(sells -> assertThat(sells).asList().doesNotContain(sell2));
    }
}
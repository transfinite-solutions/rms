package one.transfinite.rms.Sell;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.category.Category;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.order.Order;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;
import one.transfinite.rms.utility.PaymentStatus;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {SellService.class})
@ExtendWith(MockitoExtension.class)
class SellServiceTest {

    @Mock
    private SellRepository sellRepository;

    @InjectMocks
    private SellService sellService;

    @Test
    void testGetAllSells() {
        ArrayList<Sell> sellList = new ArrayList<Sell>();
        when(this.sellRepository.findAll()).thenReturn(sellList);
        List<Sell> actualAllSells = this.sellService.getAllSells();
        assertSame(sellList, actualAllSells);
        assertTrue(actualAllSells.isEmpty());
        verify(this.sellRepository).findAll();
    }

    @Test
    void saveSellTest() {
        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);

        ArgumentCaptor<Sell> sellArgumentCaptor = ArgumentCaptor.forClass(Sell.class);
        sellService.addSell(sell);
        verify(sellRepository).save(sellArgumentCaptor.capture());
        Sell captorValue = sellArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(sell);
    }

    @Test
    void getAllSellTest() {
        List<Sell> sellList = new ArrayList<>();
        Sell sell = new Sell();
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);
        sellList.add(sell);

        when(sellRepository.findAll()).thenReturn(sellList);
        List<Sell> allSells = sellService.getAllSells();

        assertThat(allSells).isEqualTo(sellList);
    }

    @Test
    void itShouldGetSellById() {
        long sellId = 1L;
        Sell sell = new Sell();
        sell.setSellId(sellId);
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);

        when(sellRepository.findById(sellId)).thenReturn(Optional.of(sell));
        Sell sellById = sellService.getSellById(sellId);

        assertThat(sellById).isEqualTo(sell);
    }

    @Test
    void itShouldNotGetSellById() {
        long sellId = 1L;
        Sell sell = new Sell();
        sell.setSellId(sellId);
        sell.setStatus(Status.PROCESSING);
        sell.setCreatedAt(new Date());
        sell.setPaymentStatus(null);

        when(sellRepository.findById(sellId)).thenReturn(Optional.ofNullable(null));
        try {
            Sell sellById = sellService.getSellById(sellId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Sell order does not exists");
        }

    }

    @Test
    void testGetSellById() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        Optional<Sell> ofResult = Optional.<Sell>of(sell);
        when(this.sellRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(sell, this.sellService.getSellById(123L));
        verify(this.sellRepository).findById((Long) any());
    }

    @Test
    void testGetSellById2() {
        when(this.sellRepository.findById((Long) any())).thenReturn(Optional.<Sell>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.sellService.getSellById(123L));
        verify(this.sellRepository).findById((Long) any());
    }

    @Test
    void testAddSell() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        when(this.sellRepository.save((Sell) any())).thenReturn(sell);

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Role role4 = new Role();
        role4.setName("Name");
        role4.setRoleId(123L);

        User user4 = new User();
        user4.setRole(role4);
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setName("Name");
        user4.setAddresses(new ArrayList<Address>());
        user4.setPhone("4105551212");
        user4.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user4);
        address1.setTag("Tag");
        address1.setState("MD");

        Role role5 = new Role();
        role5.setName("Name");
        role5.setRoleId(123L);

        User user5 = new User();
        user5.setRole(role5);
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setName("Name");
        user5.setAddresses(new ArrayList<Address>());
        user5.setPhone("4105551212");
        user5.setUserId(123L);

        Sell sell1 = new Sell();
        sell1.setSellId(123L);
        sell1.setStatus(Status.DELIVERED);
        sell1.setOrderStocks(new ArrayList<Stock>());
        sell1.setCustomer(user3);
        sell1.setPaymentStatus(PaymentStatus.PAID);
        sell1.setAddress(address1);
        sell1.setVendor(user5);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        sell1.setTotalPrice(10.0);
        this.sellService.addSell(sell1);
        verify(this.sellRepository).save((Sell) any());
        assertEquals(0.0, sell1.getTotalPrice().doubleValue());
    }

    @Test
    void testAddSell2() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        when(this.sellRepository.save((Sell) any())).thenReturn(sell);

        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Product product = new Product();
        product.setProductId(123L);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setCategory(category);
        product.setUser(user3);
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");

        Stock stock = new Stock();
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(0.0);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setOrders(new ArrayList<Order>());
        stock.setDurationTime(0L);
        stock.setProduct(product);
        stock.setStockId(123L);

        ArrayList<Stock> stockList = new ArrayList<Stock>();
        stockList.add(stock);

        Role role4 = new Role();
        role4.setName("Name");
        role4.setRoleId(123L);

        User user4 = new User();
        user4.setRole(role4);
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setName("Name");
        user4.setAddresses(new ArrayList<Address>());
        user4.setPhone("4105551212");
        user4.setUserId(123L);

        Role role5 = new Role();
        role5.setName("Name");
        role5.setRoleId(123L);

        User user5 = new User();
        user5.setRole(role5);
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setName("Name");
        user5.setAddresses(new ArrayList<Address>());
        user5.setPhone("4105551212");
        user5.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user5);
        address1.setTag("Tag");
        address1.setState("MD");

        Role role6 = new Role();
        role6.setName("Name");
        role6.setRoleId(123L);

        User user6 = new User();
        user6.setRole(role6);
        user6.setEmail("jane.doe@example.org");
        user6.setPassword("iloveyou");
        user6.setName("Name");
        user6.setAddresses(new ArrayList<Address>());
        user6.setPhone("4105551212");
        user6.setUserId(123L);

        Sell sell1 = new Sell();
        sell1.setSellId(123L);
        sell1.setStatus(Status.DELIVERED);
        sell1.setOrderStocks(stockList);
        sell1.setCustomer(user4);
        sell1.setPaymentStatus(PaymentStatus.PAID);
        sell1.setAddress(address1);
        sell1.setVendor(user6);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        sell1.setTotalPrice(10.0);
        this.sellService.addSell(sell1);
        verify(this.sellRepository).save((Sell) any());
        assertEquals(0.0, sell1.getTotalPrice().doubleValue());
    }

    @Test
    void testAddSell3() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        when(this.sellRepository.save((Sell) any())).thenReturn(sell);

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Role role4 = new Role();
        role4.setName("Name");
        role4.setRoleId(123L);

        User user4 = new User();
        user4.setRole(role4);
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setName("Name");
        user4.setAddresses(new ArrayList<Address>());
        user4.setPhone("4105551212");
        user4.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user4);
        address1.setTag("Tag");
        address1.setState("MD");

        Role role5 = new Role();
        role5.setName("Name");
        role5.setRoleId(123L);

        User user5 = new User();
        user5.setRole(role5);
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setName("Name");
        user5.setAddresses(new ArrayList<Address>());
        user5.setPhone("4105551212");
        user5.setUserId(123L);

        Sell sell1 = new Sell();
        sell1.setSellId(123L);
        sell1.setStatus(Status.DELIVERED);
        sell1.setOrderStocks(new ArrayList<Stock>());
        sell1.setCustomer(user3);
        sell1.setPaymentStatus(PaymentStatus.PAID);
        sell1.setAddress(address1);
        sell1.setVendor(user5);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        sell1.setTotalPrice(10.0);
        this.sellService.addSell(sell1);
        verify(this.sellRepository).save((Sell) any());
        assertEquals(0.0, sell1.getTotalPrice().doubleValue());
    }

    @Test
    void testAddSell4() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        when(this.sellRepository.save((Sell) any())).thenReturn(sell);

        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Product product = new Product();
        product.setProductId(123L);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setCategory(category);
        product.setUser(user3);
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");

        Stock stock = new Stock();
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(0.0);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setOrders(new ArrayList<Order>());
        stock.setDurationTime(0L);
        stock.setProduct(product);
        stock.setStockId(123L);

        ArrayList<Stock> stockList = new ArrayList<Stock>();
        stockList.add(stock);

        Role role4 = new Role();
        role4.setName("Name");
        role4.setRoleId(123L);

        User user4 = new User();
        user4.setRole(role4);
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setName("Name");
        user4.setAddresses(new ArrayList<Address>());
        user4.setPhone("4105551212");
        user4.setUserId(123L);

        Role role5 = new Role();
        role5.setName("Name");
        role5.setRoleId(123L);

        User user5 = new User();
        user5.setRole(role5);
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setName("Name");
        user5.setAddresses(new ArrayList<Address>());
        user5.setPhone("4105551212");
        user5.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user5);
        address1.setTag("Tag");
        address1.setState("MD");

        Role role6 = new Role();
        role6.setName("Name");
        role6.setRoleId(123L);

        User user6 = new User();
        user6.setRole(role6);
        user6.setEmail("jane.doe@example.org");
        user6.setPassword("iloveyou");
        user6.setName("Name");
        user6.setAddresses(new ArrayList<Address>());
        user6.setPhone("4105551212");
        user6.setUserId(123L);

        Sell sell1 = new Sell();
        sell1.setSellId(123L);
        sell1.setStatus(Status.DELIVERED);
        sell1.setOrderStocks(stockList);
        sell1.setCustomer(user4);
        sell1.setPaymentStatus(PaymentStatus.PAID);
        sell1.setAddress(address1);
        sell1.setVendor(user6);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        sell1.setTotalPrice(10.0);
        this.sellService.addSell(sell1);
        verify(this.sellRepository).save((Sell) any());
        assertEquals(0.0, sell1.getTotalPrice().doubleValue());
    }

    @Test
    void testUpdateSell() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        Optional<Sell> ofResult = Optional.<Sell>of(sell);

        Role role3 = new Role();
        role3.setName("Name");
        role3.setRoleId(123L);

        User user3 = new User();
        user3.setRole(role3);
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setName("Name");
        user3.setAddresses(new ArrayList<Address>());
        user3.setPhone("4105551212");
        user3.setUserId(123L);

        Role role4 = new Role();
        role4.setName("Name");
        role4.setRoleId(123L);

        User user4 = new User();
        user4.setRole(role4);
        user4.setEmail("jane.doe@example.org");
        user4.setPassword("iloveyou");
        user4.setName("Name");
        user4.setAddresses(new ArrayList<Address>());
        user4.setPhone("4105551212");
        user4.setUserId(123L);

        Address address1 = new Address();
        address1.setLine1("Line1");
        address1.setLandmark("Landmark");
        address1.setPincode("Pincode");
        address1.setCountry("GB");
        address1.setCity("Oxford");
        address1.setAddressId(123L);
        address1.setUser(user4);
        address1.setTag("Tag");
        address1.setState("MD");

        Role role5 = new Role();
        role5.setName("Name");
        role5.setRoleId(123L);

        User user5 = new User();
        user5.setRole(role5);
        user5.setEmail("jane.doe@example.org");
        user5.setPassword("iloveyou");
        user5.setName("Name");
        user5.setAddresses(new ArrayList<Address>());
        user5.setPhone("4105551212");
        user5.setUserId(123L);

        Sell sell1 = new Sell();
        sell1.setSellId(123L);
        sell1.setStatus(Status.DELIVERED);
        sell1.setOrderStocks(new ArrayList<Stock>());
        sell1.setCustomer(user3);
        sell1.setPaymentStatus(PaymentStatus.PAID);
        sell1.setAddress(address1);
        sell1.setVendor(user5);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell1.setCreatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        sell1.setTotalPrice(10.0);
        when(this.sellRepository.save((Sell) any())).thenReturn(sell1);
        when(this.sellRepository.findById((Long) any())).thenReturn(ofResult);

        Role role6 = new Role();
        role6.setName("Name");
        role6.setRoleId(123L);

        User user6 = new User();
        user6.setRole(role6);
        user6.setEmail("jane.doe@example.org");
        user6.setPassword("iloveyou");
        user6.setName("Name");
        user6.setAddresses(new ArrayList<Address>());
        user6.setPhone("4105551212");
        user6.setUserId(123L);

        Role role7 = new Role();
        role7.setName("Name");
        role7.setRoleId(123L);

        User user7 = new User();
        user7.setRole(role7);
        user7.setEmail("jane.doe@example.org");
        user7.setPassword("iloveyou");
        user7.setName("Name");
        user7.setAddresses(new ArrayList<Address>());
        user7.setPhone("4105551212");
        user7.setUserId(123L);

        Address address2 = new Address();
        address2.setLine1("Line1");
        address2.setLandmark("Landmark");
        address2.setPincode("Pincode");
        address2.setCountry("GB");
        address2.setCity("Oxford");
        address2.setAddressId(123L);
        address2.setUser(user7);
        address2.setTag("Tag");
        address2.setState("MD");

        Role role8 = new Role();
        role8.setName("Name");
        role8.setRoleId(123L);

        User user8 = new User();
        user8.setRole(role8);
        user8.setEmail("jane.doe@example.org");
        user8.setPassword("iloveyou");
        user8.setName("Name");
        user8.setAddresses(new ArrayList<Address>());
        user8.setPhone("4105551212");
        user8.setUserId(123L);

        Sell sell2 = new Sell();
        sell2.setSellId(123L);
        sell2.setStatus(Status.DELIVERED);
        sell2.setOrderStocks(new ArrayList<Stock>());
        sell2.setCustomer(user6);
        sell2.setPaymentStatus(PaymentStatus.PAID);
        sell2.setAddress(address2);
        sell2.setVendor(user8);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell2.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        sell2.setTotalPrice(10.0);
        this.sellService.updateSell(sell2);
        verify(this.sellRepository).findById((Long) any());
        verify(this.sellRepository).save((Sell) any());
        assertEquals(0.0, sell2.getTotalPrice().doubleValue());
    }

    @Test
    void testDeleteSell() {
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        User user = new User();
        user.setRole(role);
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setName("Name");
        user.setAddresses(new ArrayList<Address>());
        user.setPhone("4105551212");
        user.setUserId(123L);

        Role role1 = new Role();
        role1.setName("Name");
        role1.setRoleId(123L);

        User user1 = new User();
        user1.setRole(role1);
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setName("Name");
        user1.setAddresses(new ArrayList<Address>());
        user1.setPhone("4105551212");
        user1.setUserId(123L);

        Address address = new Address();
        address.setLine1("Line1");
        address.setLandmark("Landmark");
        address.setPincode("Pincode");
        address.setCountry("GB");
        address.setCity("Oxford");
        address.setAddressId(123L);
        address.setUser(user1);
        address.setTag("Tag");
        address.setState("MD");

        Role role2 = new Role();
        role2.setName("Name");
        role2.setRoleId(123L);

        User user2 = new User();
        user2.setRole(role2);
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setName("Name");
        user2.setAddresses(new ArrayList<Address>());
        user2.setPhone("4105551212");
        user2.setUserId(123L);

        Sell sell = new Sell();
        sell.setSellId(123L);
        sell.setStatus(Status.DELIVERED);
        sell.setOrderStocks(new ArrayList<Stock>());
        sell.setCustomer(user);
        sell.setPaymentStatus(PaymentStatus.PAID);
        sell.setAddress(address);
        sell.setVendor(user2);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        sell.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        sell.setTotalPrice(10.0);
        Optional<Sell> ofResult = Optional.<Sell>of(sell);
        doNothing().when(this.sellRepository).deleteById((Long) any());
        when(this.sellRepository.findById((Long) any())).thenReturn(ofResult);
        this.sellService.deleteSell(123L);
        verify(this.sellRepository).deleteById((Long) any());
        verify(this.sellRepository).findById((Long) any());
        assertTrue(this.sellService.getAllSells().isEmpty());
    }

    @Test
    void testDeleteSell2() {
//        doNothing().when(this.sellRepository).deleteById((Long) any());
        when(this.sellRepository.findById((Long) any())).thenReturn(Optional.<Sell>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.sellService.deleteSell(123L));
        verify(this.sellRepository).findById((Long) any());
    }
}
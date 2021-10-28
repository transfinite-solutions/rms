package one.transfinite.rms.stock;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.category.Category;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.order.Order;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.role.Role;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {StockService.class})
@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @Test
    void saveStockTest() {
        Stock stock = new Stock();
        stock.setAvailability(Availability.AVAILABLE);
        stock.setDurationTime(5L);
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(100.23);

        ArgumentCaptor<Stock> stockArgumentCaptor = ArgumentCaptor.forClass(Stock.class);
        stockService.addStock(stock);
        verify(stockRepository).save(stockArgumentCaptor.capture());
        Stock captorValue = stockArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(stock);
    }

    @Test
    void getAllStockTest() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock = new Stock();
        stock.setAvailability(Availability.AVAILABLE);
        stock.setDurationTime(5L);
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(100.23);

        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> allStocks = stockService.getAllStocks();

        assertThat(allStocks).isEqualTo(stockList);
    }

    @Test
    void itShouldGetStockById() {
        long stockId = 1L;
        Stock stock = new Stock();
        stock.setStockId(stockId);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setDurationTime(5L);
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(100.23);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        Stock stockById = stockService.getStockById(stockId);

        assertThat(stockById).isEqualTo(stock);
    }

    @Test
    void itShouldNotGetStockById() {
        long stockId = 1L;

        when(stockRepository.findById(stockId)).thenReturn(Optional.ofNullable(null));
        try {
            Stock stockById = stockService.getStockById(stockId);
        } catch (ResourceNotFoundException e) {
            assertThat(e.getMessage()).isEqualTo("Stock does not exists");
        }
    }

    @Test
    void testDeleteStock() {
        Category category = new Category();
        category.setName("Name");
        category.setCategoryId(123L);
        category.setImage("Image");

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

        Product product = new Product();
        product.setProductId(123L);
        product.setImageUrl("https://example.org/example");
        product.setName("Name");
        product.setCategory(category);
        product.setUser(user);
        product.setDescription("The characteristics of someone or something");
        product.setType("Type");
        product.setRegistrationNumber("42");

        Stock stock = new Stock();
        stock.setDurationType(DurationType.HOUR);
        stock.setRate(10.0);
        stock.setAvailability(Availability.AVAILABLE);
        stock.setOrders(new ArrayList<Order>());
        stock.setDurationTime(1L);
        stock.setProduct(product);
        stock.setStockId(123L);
        Optional<Stock> ofResult = Optional.<Stock>of(stock);
        doNothing().when(this.stockRepository).deleteById((Long) any());
        when(this.stockRepository.findById((Long) any())).thenReturn(ofResult);
        this.stockService.deleteStock(123L);
        verify(this.stockRepository).deleteById((Long) any());
        verify(this.stockRepository).findById((Long) any());
        assertTrue(this.stockService.getAllStocks().isEmpty());
    }

    @Test
    void testDeleteStock2() {
//        doNothing().when(this.stockRepository).deleteById((Long) any());
        when(this.stockRepository.findById((Long) any())).thenReturn(Optional.<Stock>empty());
        assertThrows(ResourceNotFoundException.class, () -> this.stockService.deleteStock(123L));
        verify(this.stockRepository).findById((Long) any());
    }
}
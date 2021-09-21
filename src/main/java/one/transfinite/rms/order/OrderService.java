package one.transfinite.rms.order;

import one.transfinite.rms.execption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order does not exists"));
    }

    public void addOrder(Order order) {
        this.orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order does not exists"));
        this.orderRepository.deleteById(orderId);
    }
}

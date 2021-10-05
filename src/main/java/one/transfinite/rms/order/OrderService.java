package one.transfinite.rms.order;

import one.transfinite.rms.execption.ApiBadRequestException;
import one.transfinite.rms.execption.ResourceNotFoundException;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;
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
//        String status = getStatus(order.getStatus());
//        String paymentStatus = getPaymentStatus(order.getPaymentStatus());
//
//        if (status !=null){
//            order.setStatus(status);
//        }else {
//            throw new ApiBadRequestException("Wrong status");
//        }
//        if (paymentStatus !=null){
//            order.setPaymentStatus(paymentStatus);
//        }else {
//            throw new ApiBadRequestException("Wrong payment status");
//        }

        this.orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order does not exists"));
        this.orderRepository.deleteById(orderId);
    }

    public String getStatus(String text) {
        for(Status status : Status.values()){
            if (status.name().equalsIgnoreCase(text)){
                return status.name();
            }
        }
        return null;
    }

    public String getPaymentStatus(String text) {
        for(PaymentStatus paymentStatus : PaymentStatus.values()){
            if (paymentStatus.name().equalsIgnoreCase(text)){
                return paymentStatus.name();
            }
        }
        return null;
    }
}

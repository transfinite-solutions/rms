package one.transfinite.rms.order;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long orderId;

    @NotBlank
    @Column(nullable = false)
    private Date from;

    @NotBlank
    @Column(nullable = false)
    private Date to;

    @ManyToOne(fetch = FetchType.EAGER)
    private User vendor;

    @ManyToOne(fetch = FetchType.EAGER)
    private User customer;

    @NotBlank
    @Column(nullable = false)
    private Status status;

    private Date createdAt;

    private Double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    private PaymentStatus paymentStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stock> stocks = new ArrayList<>();

    public Order() {
    }

    public Order(Long orderId,
                 Date from,
                 Date to,
                 User vendor,
                 User customer,
                 Status status,
                 Date createdAt,
                 Double totalPrice,
                 Address address,
                 PaymentStatus paymentStatus,
                 List<Stock> stocks) {
        this.orderId = orderId;
        this.from = from;
        this.to = to;
        this.vendor = vendor;
        this.customer = customer;
        this.status = status;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.address = address;
        this.paymentStatus = paymentStatus;
        this.stocks = stocks;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public User getVendor() {
        return vendor;
    }

    public void setVendor(User vendor) {
        this.vendor = vendor;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", from=" + from +
                ", to=" + to +
                ", vendor=" + vendor +
                ", customer=" + customer +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", totalPrice=" + totalPrice +
                ", address=" + address +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}

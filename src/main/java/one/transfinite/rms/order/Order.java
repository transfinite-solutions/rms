package one.transfinite.rms.order;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotBlank
    @Column(nullable = false)
    private Date fromDate;

    @NotBlank
    @Column(nullable = false)
    private Date toDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User vendor;

    @ManyToOne(fetch = FetchType.EAGER)
    private User customer;

    @NotBlank
    @Column(nullable = false)
    private Status status;

    private Date createdAt;

    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    private PaymentStatus paymentStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stock> stocks = new ArrayList<>();

    public Order() {
    }

    public Order(Long orderId,
                 Date fromDate,
                 Date toDate,
                 User vendor,
                 User customer,
                 Status status,
                 Date createdAt,
                 Double totalPrice,
                 Address address,
                 PaymentStatus paymentStatus,
                 List<Stock> stocks) {
        this.orderId = orderId;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
                ", from=" + fromDate +
                ", to=" + toDate +
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

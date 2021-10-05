package one.transfinite.rms.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import one.transfinite.rms.address.Address;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.OrderType;
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
    @Column(name = "order_id")
    private Long orderId;

    private Date fromDate;

    private Date toDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User vendor;

    @ManyToOne(fetch = FetchType.EAGER)
    private User customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private Date createdAt;

    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
//    @Fetch(value = FetchMode.SUBSELECT)
//    private List<OrderStock> orderStocks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "order_stock",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> orderStocks = new ArrayList<>();

    public Order() {
    }

//    public Order(Long orderId, Date fromDate, Date toDate, User vendor, User customer, Status status, Date createdAt, Double totalPrice, Address address, PaymentStatus paymentStatus, List<OrderStock> orderStocks) {
//        this.orderId = orderId;
//        this.fromDate = fromDate;
//        this.toDate = toDate;
//        this.vendor = vendor;
//        this.customer = customer;
//        this.status = status;
//        this.createdAt = createdAt;
//        this.totalPrice = totalPrice;
//        this.address = address;
//        this.paymentStatus = paymentStatus;
//        this.orderStocks = orderStocks;
//    }


    public Order(Long orderId, Date fromDate, Date toDate, User vendor, User customer, Status status, Date createdAt, Double totalPrice, Address address, PaymentStatus paymentStatus, List<Stock> orderStocks) {
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
        this.orderStocks = orderStocks;
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

    //    public List<OrderStock> getOrderStocks() {
//        return orderStocks;
//    }
//
//    public void setOrderStocks(List<OrderStock> orderStocks) {
//        this.orderStocks = orderStocks;
//    }


    public List<Stock> getOrderStocks() {
        return orderStocks;
    }

    public void setOrderStocks(List<Stock> orderStocks) {
        this.orderStocks = orderStocks;
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

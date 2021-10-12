package one.transfinite.rms.Sell;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.PaymentStatus;
import one.transfinite.rms.utility.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sell")
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_id")
    private Long sellId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User vendor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User customer;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "address_id")
    private Address address;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToMany
    @JoinTable(
            name = "order_stock",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> orderStocks = new ArrayList<>();

    public Sell() {
    }

    public Sell(Long sellId, User vendor, User customer, Date createdAt, Status status, Address address, Double totalPrice, PaymentStatus paymentStatus, List<Stock> orderStocks) {
        this.sellId = sellId;
        this.vendor = vendor;
        this.customer = customer;
        this.createdAt = createdAt;
        this.status = status;
        this.address = address;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.orderStocks = orderStocks;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<Stock> getOrderStocks() {
        return orderStocks;
    }

    public void setOrderStocks(List<Stock> orderStocks) {
        this.orderStocks = orderStocks;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "sellId=" + sellId +
                ", vendor=" + vendor +
                ", customer=" + customer +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", address=" + address +
                ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", orderStocks=" + orderStocks +
                '}';
    }
}

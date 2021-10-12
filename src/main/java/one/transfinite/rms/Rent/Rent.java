package one.transfinite.rms.Rent;

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
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Long rentId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User vendor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User customer;

    private Date pickupDate;

    private Date dropDate;

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

    public Rent() {
    }

    public Rent(Long rentId, User vendor, User customer, Date pickupDate, Date dropDate, Date createdAt, Status status, Address address, Double totalPrice, PaymentStatus paymentStatus, List<Stock> orderStocks) {
        this.rentId = rentId;
        this.vendor = vendor;
        this.customer = customer;
        this.pickupDate = pickupDate;
        this.dropDate = dropDate;
        this.createdAt = createdAt;
        this.status = status;
        this.address = address;
        this.totalPrice = totalPrice;
        this.paymentStatus = paymentStatus;
        this.orderStocks = orderStocks;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
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

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getDropDate() {
        return dropDate;
    }

    public void setDropDate(Date dropDate) {
        this.dropDate = dropDate;
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
}

package one.transfinite.rms.stock;

import one.transfinite.rms.order.Order;
//import one.transfinite.rms.order.OrderStock;
import one.transfinite.rms.product.Product;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "product_id")
    private Product product;

    private Double rate;

    @Enumerated(EnumType.STRING)
    private DurationType durationType;

    private Long durationTime;

    @Enumerated(EnumType.STRING)
    private Availability availability;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stock")
//    private List<OrderStock> orderStocks = new ArrayList<>();

    @ManyToMany
    private List<Order> orders = new ArrayList<>();

    public Stock() {
    }

    public Stock(Long stockId, Product product, Double rate, DurationType durationType, Long durationTime, Availability availability) {
        this.stockId = stockId;
        this.product = product;
        this.rate = rate;
        this.durationType = durationType;
        this.durationTime = durationTime;
        this.availability = availability;
    }

    public Stock(Long stockId, Product product, Double rate, DurationType durationType, Long durationTime, Availability availability, List<Order> orders) {
        this.stockId = stockId;
        this.product = product;
        this.rate = rate;
        this.durationType = durationType;
        this.durationTime = durationTime;
        this.availability = availability;
        this.orders = orders;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public DurationType getDurationType() {
        return durationType;
    }

    public void setDurationType(DurationType durationType) {
        this.durationType = durationType;
    }

    public Long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Long durationTime) {
        this.durationTime = durationTime;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", product=" + product +
                ", rate=" + rate +
                ", durationType=" + durationType +
                ", durationTime=" + durationTime +
                ", availability=" + availability +
                ", orders=" + orders +
                '}';
    }
}

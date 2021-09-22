package one.transfinite.rms.stock;

import one.transfinite.rms.product.Product;
import one.transfinite.rms.utility.Availability;
import one.transfinite.rms.utility.DurationType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @NotBlank
    private Double rate;

    @NotNull
    private DurationType durationType;

    @NotBlank
    private Long durationTime;

    private Availability availability;

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
}

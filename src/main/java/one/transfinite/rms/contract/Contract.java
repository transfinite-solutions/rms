package one.transfinite.rms.contract;

import one.transfinite.rms.product.Product;
import one.transfinite.rms.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long contractId;

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    @OneToOne(fetch = FetchType.EAGER)
    private User vendor;

    @OneToOne(fetch = FetchType.EAGER)
    private User customer;

    private Date createdAt;

    @ManyToMany
    @JoinTable(
            name = "contract_product",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    private Double totalPrice;

    public Contract() {
    }

    public Contract(Long contractId, String name, String description, Date startDate, Date endDate, User vendor, User customer, Date createdAt, List<Product> products, Double totalPrice) {
        this.contractId = contractId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vendor = vendor;
        this.customer = customer;
        this.createdAt = createdAt;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vendor=" + vendor +
                ", customer=" + customer +
                ", createdAt=" + createdAt +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

package one.transfinite.rms.product;

import one.transfinite.rms.category.Category;
import one.transfinite.rms.stock.Stock;
import one.transfinite.rms.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id",updatable = false, nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    private String type;

    private String registrationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
//    private List<Stock> stocks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    public Product() {
    }

    public Product(Long productId, String name, String description, String imageUrl, String type, String registrationNumber, User user, Category category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.type = type;
        this.registrationNumber = registrationNumber;
        this.user = user;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public List<Stock> getStocks() {
//        return stocks;
//    }
//
//    public void setStocks(List<Stock> stocks) {
//        this.stocks = stocks;
//    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}

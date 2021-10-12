package one.transfinite.rms.warehouse;

import one.transfinite.rms.address.Address;
import one.transfinite.rms.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long warehouseId;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Date createdAt;

    public Warehouse() {
    }

    public Warehouse(Long warehouseId, String name, Address address, User user, Date createdAt) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.address = address;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

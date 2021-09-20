package one.transfinite.rms.user;

import one.transfinite.rms.address.Address;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "address_id"})
})
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "address_id")
    private Address address;

    public UserAddress() {
    }

    public UserAddress(Long userId, User user, Address address) {
        this.userId = userId;
        this.user = user;
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

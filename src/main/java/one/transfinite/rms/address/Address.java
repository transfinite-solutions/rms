package one.transfinite.rms.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import one.transfinite.rms.Rent.Rent;
import one.transfinite.rms.user.User;
import one.transfinite.rms.user.UserAddress;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", updatable = false, nullable = false)
    private Long addressId;

    @NotBlank
    @Column(nullable = false)
    private String line1;

    private String landmark;

    @NotBlank
    @Column(nullable = false)
    private String city;

    @NotBlank
    @Column(nullable = false)
    private String state;

    @NotBlank
    @Column(nullable = false)
    private String country;

    @NotBlank
    @Column(nullable = false)
    private String pincode;

    private String tag;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
//    private List<UserAddress> userAddresses = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

//    @JsonIgnore
//    @Transient
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Rent> rentList;

    public Address() {
    }

    public Address(Long addressId,
                   String line1,
                   String landmark,
                   String city,
                   String state,
                   String country,
                   String pincode,
                   String tag,
                    User user
//                   List<UserAddress> userAddresses
    ) {
        this.addressId = addressId;
        this.line1 = line1;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.tag = tag;
        this.user = user;
//        this.userAddresses = userAddresses;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

//    public List<UserAddress> getUserAddresses() {
//        return userAddresses;
//    }
//
//    public void setUserAddresses(List<UserAddress> userAddresses) {
//        this.userAddresses = userAddresses;
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", line1='" + line1 + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}

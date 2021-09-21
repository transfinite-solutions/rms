package one.transfinite.rms.vehicle;

import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.Availability;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long vehicleId;

    private String name;

    private String registrationNumber;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Availability availability;

    private Date createdAt;

    public Vehicle() {
    }

    public Vehicle(Long vehicleId, String name, String registrationNumber, String imageUrl, User user, Availability availability, Date createdAt) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.imageUrl = imageUrl;
        this.user = user;
        this.availability = availability;
        this.createdAt = createdAt;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

package one.transfinite.rms.role;

import one.transfinite.rms.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID roleId;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users;

    public Role() {
    }

    public Role(UUID roleId, String name, List<User> users) {
        this.roleId = roleId;
        this.name = name;
        this.users = users;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

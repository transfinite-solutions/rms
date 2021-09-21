package one.transfinite.rms.role;

import one.transfinite.rms.user.User;
import one.transfinite.rms.utility.RoleName;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private RoleName name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users;

    public Role() {
    }

    public Role(Long roleId, RoleName name) {
        this.roleId = roleId;
        this.name = name;
    }

    public Role(Long roleId, RoleName name, List<User> users) {
        this.roleId = roleId;
        this.name = name;
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

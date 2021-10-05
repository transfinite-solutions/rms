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
    @Column(name = "role_id", updatable = false, nullable = false)
    private Long roleId;

    @Column(nullable = false)
    private String name;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<User> users;

    public Role() {
    }

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

//    public Role(Long roleId,
//                String name,
//                List<User> users
//    ) {
//        this.roleId = roleId;
//        this.name = name;
//        this.users = users;
//    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}

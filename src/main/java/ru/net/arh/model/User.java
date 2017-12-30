package ru.net.arh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import ru.net.arh.model.mapped.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.EnumSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@SQLDelete(sql = "UPDATE User SET deleted=true WHERE id=? ")
//@Loader(namedQuery = Restaurant.FIND_BY_ID)
//@Where(clause = "deleted=false")
@Table
        (name = "users"
//                , uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_email_idx")}
        )
public class User extends AbstractBaseEntity implements UserDetails {

    @Column(name = "username", nullable = false)
    @NotBlank
    @Size(min = 2, max = 64)
    private String username;


    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), foreignKey = @ForeignKey(name = "fk_user_user_role"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> authorities;

    public User(Integer id, String name, String password, Role role, Role... roles) {
        this(id, name, password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String password, EnumSet<Role> roles) {
        super(id);
        this.username = name;
        this.password = password;
        this.authorities = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

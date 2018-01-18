package ru.net.arh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import ru.net.arh.model.mapped.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.EnumSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity implements UserDetails {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Size(min = 2, max = 64)
    private String username;

    @NotNull
    @Size(min = 5, max = 64)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), foreignKey = @ForeignKey(name = "fk_user_user_role"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @JsonIgnore
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

    public User(String username, String password, Role roleUser) {
        this(null, username, password, roleUser);
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}

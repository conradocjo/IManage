package br.imanage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "TB_USERS")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CL_NAME", unique = true)
    private String name;

    @Column(name = "CL_USER_NAME", unique = true)
    private String nameuser;

    @Column(name = "CL_PASSWORD")
    private String password;

    @Column(name = "CL_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @Column(name = "CL_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(name = "CL_ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String nameuser, String password, UserRole role) {
        this.nameuser = nameuser;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return nameuser;
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

    public enum UserRole {
        ADMIN("admin"),
        USER("user");

        private String role;

        UserRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

}
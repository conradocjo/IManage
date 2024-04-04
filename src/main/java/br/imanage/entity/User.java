package br.imanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_USERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CL_USER_NAME", unique = true)
    private String nameuser;

    @Column(name = "CL_NAME", unique = true)
    private String name;

    @Column(name = "CL_EMAIL", unique = true)
    private String email;

    @Column(name = "CL_CELPHONE", unique = true)
    private String celphone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CL_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @Column(name = "CL_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

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
package com.crash.crash.model.entity;

import com.crash.crash.model.user.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name = "\"user\""
)
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 16)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 24)
    private String email;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column
    private ZonedDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    private UserEntity(String username, String password, String name, String email, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static UserEntity of(String username, String password, String name, String email, Role role) {
        return new UserEntity(username, password, name, email, role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && role == that.role
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId,
                username,
                password,
                name,
                email,
                role,
                createdAt);
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }

}

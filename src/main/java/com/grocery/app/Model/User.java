package com.grocery.app.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.mapping.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1001, allocationSize = 1)
    private int id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password_hash;

    @Column
    private String role;

    @Column
    private String created_at;
}

package br.imanage.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_VAULT")
@AllArgsConstructor
@NoArgsConstructor
public class Vault {

    public Vault(String system, String password) {
        this.system = system;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CL_SYSTEM")
    private String system;

    @Column(name = "CL_PASSWORD")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CL_CREATED_DATE")
    private LocalDateTime createdAt;

}

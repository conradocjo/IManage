package br.imanage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_VAULT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vault {

    public Vault(String system, String password) {
        this.system = system;
        this.password = password;
        this.creationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID")
    private User user;

    @Column(name = "CL_SYSTEM", unique = true)
    private String system;

    @Column(name = "CL_PASSWORD")
    private String password;

    @Column(name = "CL_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creationDate;

    @Column(name = "CL_UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;
}

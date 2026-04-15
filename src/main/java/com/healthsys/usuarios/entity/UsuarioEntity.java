package com.healthsys.usuarios.entity;

import com.healthsys.usuarios.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_USU")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Long id;

    @Column(name = "USU_NM")
    private String name;

    @Column(name = "USU_EML")
    private String email;

    @Column(name = "USU_PSWRD")
    private String password;

    @Column(name = "USU_ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;
}

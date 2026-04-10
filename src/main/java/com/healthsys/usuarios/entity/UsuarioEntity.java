package com.healthsys.usuarios.entity;

import com.healthsys.usuarios.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_USU")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USU")
    private Long id;

    @Column(name = "NM_USU")
    private String nome;

    @Column(name = "PRFL_USU")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @Column(name = "EML_USU")
    private String email;

    @Column(name = "SEN_USU")
    private String senha;
}

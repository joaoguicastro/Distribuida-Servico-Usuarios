package com.healthsys.usuarios.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthsys.usuarios.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "Nome e obrigatorio")
    @Column(name = "NM_USU")
    private String nome;

    @NotNull(message = "Perfil e obrigatorio")
    @Column(name = "PRFL_USU")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    @Column(name = "EML_USU", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "SEN_USU", nullable = false)
    private String senha;
}

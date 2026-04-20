package com.healthsys.usuarios.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthsys.usuarios.enums.Role;
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
    @Column(name = "ID_USU")
    private Long id;

    @NotBlank(message = "Nome e obrigatorio")
    @JsonProperty("nome")
    @JsonAlias("name")
    @Column(name = "NM_USU", nullable = false)
    private String name;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    @Column(name = "EML_USU", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @JsonProperty(value = "senha", access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias("password")
    @Column(name = "SEN_USU", nullable = false)
    private String password;

    @NotNull(message = "Perfil e obrigatorio")
    @JsonProperty("perfil")
    @JsonAlias("role")
    @Column(name = "PRFL_USU", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}

package com.nadic.desafiobackend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userusername"})
        })
@Data
@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "O nome não pode ser vazio")
        private String username;

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        private String password;


        public User(String username,String password) {
            this.username = username;
            this.password = password;
        }
}

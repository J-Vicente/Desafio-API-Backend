package com.nadic.desafiobackend.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userusername"})
        })
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "O nome não pode ser vazio")
        private String username;

        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        private String password;

        public User() {
        }

        public User(String username,String password) {
            this.username = username;
            this.password = password;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return username;
        }

        public void setName(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}

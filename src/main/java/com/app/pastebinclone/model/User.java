package com.app.pastebinclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email should not be blank")
    private String email;
    @Column(unique = true)
    @NotBlank(message = "Username should not be blank")
    private String username;
    @Column(unique = true)
    @NotBlank(message = "Password should not be blank")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Paste> pasteList = new ArrayList<>();

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}

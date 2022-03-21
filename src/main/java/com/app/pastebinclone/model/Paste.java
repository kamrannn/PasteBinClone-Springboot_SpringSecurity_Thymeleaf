package com.app.pastebinclone.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "paste")
public class Paste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Description should not be blank")
    @Column(length = 65535, columnDefinition = "Text")
    private String description;
    private GrantType authorizationType;
}

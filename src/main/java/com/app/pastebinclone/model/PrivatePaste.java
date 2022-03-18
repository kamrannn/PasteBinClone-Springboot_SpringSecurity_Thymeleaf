package com.app.pastebinclone.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "private_paste")
public class PrivatePaste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String privatePasteName;
    private String privatePasteDescription;

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<User> userList = new ArrayList<>();
}

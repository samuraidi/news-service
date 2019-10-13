package com.newsservice.newsservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "date_Created")
    private LocalDate dateCreated;

    @Column(name = "picture_link")
    private String pictureLink;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @ManyToMany
    @JoinTable(
            name = "news_role",
            joinColumns = { @JoinColumn(name = "news_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    Set<Role> allowedRoles = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "news_id")
    private List<ReadStatus> readStatus;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bild_id")
    private Bild bild;
}

package com.newsservice.newsservice.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Table(name="bild")
public class Bild{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bildId;

    @Column(name = "pictureName")
    private String pictureName;

    @Column(name = "picData")
    private String picData;

    @Column(name = "metadata")
    private byte[] metadata;

    public Bild(String originalFilename, String contentType, byte[] bytes) {

    }
}

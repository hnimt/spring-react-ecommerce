package com.minh.Backend.entity.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minh.Backend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "filename")
    private String fileName;

    @Column(name = "original_filename")
    private String originalFileName;

    @Column(name = "file_size")
    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public ProductImage(String originalFileName, Long fileSize, Product product) {
        this.originalFileName = originalFileName;
        this.fileSize = fileSize;
        this.product = product;
    }
}

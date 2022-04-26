package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product extends BaseExtendedModel {

    private String name;
    private BigDecimal price;
    private UUID barcode;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Category category;


}

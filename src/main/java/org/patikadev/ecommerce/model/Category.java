package org.patikadev.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Category extends BaseExtendedModel {

    @OneToOne( /*orphanRemoval = true,*/ cascade = CascadeType.ALL)
    private Category parent;

    private String name;

}

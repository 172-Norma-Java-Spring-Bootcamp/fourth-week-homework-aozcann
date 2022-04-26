package org.patikadev.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends BaseExtendedModel {

    @OneToOne( /*orphanRemoval = true,*/ cascade = CascadeType.ALL)
    private Category parent;

    private String name;
//    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST)
//    private Set<Product> product = new HashSet<>();
}

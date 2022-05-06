package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.patikadev.ecommerce.model.enums.Gender;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer extends BaseExtendedModel {

    private String username;
    private String email;
    private Long identity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToMany(mappedBy = "customer")
    private Set<Basket> basketList = new HashSet<>();
}

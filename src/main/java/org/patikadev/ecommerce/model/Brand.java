package org.patikadev.ecommerce.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Brand extends BaseExtendedModel {

    private String name;

}

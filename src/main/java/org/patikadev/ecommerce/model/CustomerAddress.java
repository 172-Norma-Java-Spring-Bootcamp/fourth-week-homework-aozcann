package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CustomerAddress extends BaseExtendedModel {
    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;
}

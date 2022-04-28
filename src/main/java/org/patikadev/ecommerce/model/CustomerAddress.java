package org.patikadev.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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

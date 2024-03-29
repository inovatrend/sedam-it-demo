package hr.sedamit.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Address {

    private String country;
    private String city;
    private String street;
    private String streetNumber;

}

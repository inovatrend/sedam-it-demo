package hr.sedamit.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class AddressDTO {
    private String country;
    private String city;
    private String street;
    private String streetNumber;
}

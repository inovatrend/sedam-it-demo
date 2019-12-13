package hr.sedamit.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("MEMBER")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Member extends User {

    private String memberId;

    @Embedded
    private Address address;

}

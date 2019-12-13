package hr.sedamit.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_permissions",
            joinColumns=@JoinColumn(name="ROLE_ID") )
    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;
}

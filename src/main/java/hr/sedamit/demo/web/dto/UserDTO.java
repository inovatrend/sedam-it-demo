package hr.sedamit.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String fullName;

    private int age;

    private boolean active;

    private RoleDTO role;

}

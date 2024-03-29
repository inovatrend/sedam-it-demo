package hr.sedamit.demo.web.dto;

import hr.sedamit.demo.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RoleDTO {

    private Long id;

    private String name;

    private List<Permission> permissions;
}

package hr.sedamit.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class MemberDTO extends UserDTO {

    private String memberId;

    private AddressDTO address;

    public MemberDTO(Long id,
                     String username,
                     String fullName,
                     int age,
                     boolean active,
                     String memberId,
                     AddressDTO address,
                     RoleDTO roleDTO) {
        super(id, username, fullName, age, active, roleDTO);
        this.memberId = memberId;
        this.address = address;
    }
}

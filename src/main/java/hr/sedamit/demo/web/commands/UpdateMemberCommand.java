package hr.sedamit.demo.web.commands;

import hr.sedamit.demo.web.dto.AddressDTO;
import lombok.Getter;

@Getter
public class UpdateMemberCommand extends UpdateUserCommand {

    private String memberId;

    private AddressDTO address;

    public UpdateMemberCommand(String username,
                               String password,
                               String fullName,
                               int age,
                               boolean active,
                               Long roleId,
                               String memberId,
                               AddressDTO address) {
        super(username, password, fullName, age, active, roleId);
        this.memberId = memberId;
        this.address = address;
    }
}

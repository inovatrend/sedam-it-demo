package hr.sedamit.demo.web.commands;

import hr.sedamit.demo.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @AllArgsConstructor @NoArgsConstructor @Setter
public class UpdateAuthorCommand {

    @NotEmpty
    @Size(min = 2, max = 200, message = "Ime mora biti dužine između 2 i 200 znakova")
    private String firstName;

    private String lastName;

    private String nationality;

    private int yearOfBirth;

    public UpdateAuthorCommand(Author autohr) {
        this.firstName = autohr.getFirstName();
        this.lastName = autohr.getLastName();
        this.nationality = autohr.getNationality();
        this.yearOfBirth = autohr.getYearOfBirth();
    }
}

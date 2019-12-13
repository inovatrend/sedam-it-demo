package hr.sedamit.demo.web.dto;

import hr.sedamit.demo.model.*;

public class DTOFactory {

    public static UserDTO toUserDTO(User user) {
        if (user == null)
            return null;

        return new UserDTO (
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getAge(),
                user.isActive(),
                DTOFactory.toRoleDTO(user.getRole())
        );
    }

    public static MemberDTO toMemberDTO(Member member) {
        if (member == null)
            return null;

        return new MemberDTO (
                member.getId(),
                member.getUsername(),
                member.getFullName(),
                member.getAge(),
                member.isActive(),
                member.getMemberId(),
                DTOFactory.toAddressDTO(member.getAddress()),
                DTOFactory.toRoleDTO(member.getRole())
        );
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        if (author == null)
            return null;

        return new AuthorDTO (
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getNationality(),
                author.getYearOfBirth()
        );
    }

    public static BookDTO toBookDTO(Book book) {
        return new BookDTO (
                book.getId(),
                book.getTitle(),
                DTOFactory.toAuthorDTO(book.getAuthor())
        );
    }

    public static AddressDTO toAddressDTO(Address address) {
        return  new AddressDTO(
                address.getCountry(),
                address.getCity(),
                address.getStreet(),
                address.getStreetNumber()
        );
    }

    public static RoleDTO toRoleDTO(Role role){
        return new RoleDTO(
                role.getId(),
                role.getName(),
                role.getPermissions()
        );
    }
}

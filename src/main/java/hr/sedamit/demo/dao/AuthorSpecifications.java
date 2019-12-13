package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.Author;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecifications {

    public static Specification<Author> byYearOfBirth(Integer yearOfBirth) {
        return (Specification<Author>) (root, criteriaQuery, cb) -> {
            return cb.equal(root.get("yearOfBirth"), yearOfBirth);
        };
    }
}

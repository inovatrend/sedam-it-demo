package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> activeUsers() {
        return (root, criteriaQuery, cb) -> cb.equal(root.get("active"), Boolean.TRUE);
    }

    public static Specification<User> olderThenUsers(Integer years) {
        return (root, criteriaQuery, cb) -> cb.greaterThanOrEqualTo(root.get("age"), years);
    }

    public static Specification<User> findUsers(UserSearchFilter filter) {

        Specification<User> spec = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(new Predicate[0]);
        };

        if (filter.getActive() != null)
            spec = spec.and(activeUsers());

        if (filter.getOlderThenAge() != null)
            spec = spec.and(olderThenUsers(filter.getOlderThenAge()));

        return  spec;


//        return (Specification<User>) (root, criteriaQuery, cb) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//            if (filter.getActive() != null)
//                predicates.add(cb.equal(root.get("active"), filter.getActive()));
//
//            if (filter.getOlderThenAge() != null)
//                predicates.add(cb.greaterThanOrEqualTo(root.get("age"), filter.getOlderThenAge()));
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
    }

}

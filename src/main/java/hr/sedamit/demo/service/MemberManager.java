package hr.sedamit.demo.service;

import hr.sedamit.demo.model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberManager {

    List<Member> getAllMembers();

    Optional<Member> getMember(Long userId);

    Member save(Member user);

    void delete(Long userId);

}

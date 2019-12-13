package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

}
